import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

public class XmlStatement extends Statement{
    private final Element xmlRoot;
    private final Document xmlDocument;

    public XmlStatement(Transaction Transaction) {
        super(Transaction);
        this.xmlRoot = XmlUtils.getDocumentRoot("statement");
        this.xmlDocument = this.xmlRoot.getOwnerDocument();
    }

    @Override
    public void addStatementHeader() {
        this.xmlRoot.appendChild(this.customer.getXmlElement(xmlDocument));
    }

    @Override
    public void addRentalSummaryByType(List<Item> items) {
        if (items.isEmpty()) {
            return;
        }

        // Create XML list by type
        Element typeList = items.get(0).getXmlList(this.xmlDocument);
        this.xmlRoot.appendChild(typeList);

        // Itemized list of items by type
        for (Item item : items) {
            typeList.appendChild(item.getXmlElement(this.xmlDocument));
        }
    }

    @Override
    public void addStatementFooter() {
        // Add footer data to xml document
        Element frequentRenterPoints =
                (Element)this.xmlDocument.getElementsByTagName("frequentRenterPointsEarned").item(0);

        XmlUtils.addChild(
                this.xmlDocument,
                frequentRenterPoints,
                "earned",
                String.valueOf(this.getEarnedFrequentRenterPoints())
        );

        if (this.getSpentFrequentRenterPoints() > 0) {
            XmlUtils.addChild(
                    this.xmlDocument,
                    frequentRenterPoints,
                    "redeemed",
                    String.valueOf(this.getSpentFrequentRenterPoints())
            );
        }

        XmlUtils.addChild(
                this.xmlDocument,
                frequentRenterPoints,
                "total",
                String.valueOf(this.getTotalFrequentRenterPoints())
        );

        if (this.transaction instanceof PercentDiscountTransaction) {
            XmlUtils.addChild(this.xmlDocument, this.xmlRoot, "subtotal", String.valueOf(this.getSubtotal()));
            XmlUtils.addChild(
                    this.xmlDocument,
                    this.xmlRoot,
                    "discount",
                    this.transaction.toString()
            );
        }
        XmlUtils.addChild(this.xmlDocument, this.xmlRoot, "total", String.valueOf(this.getTotalPrice()));
    }

    @Override
    public String toString() {
        return XmlUtils.getDocumentString(this.xmlDocument);
    }
}
