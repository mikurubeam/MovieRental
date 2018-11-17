import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

public class XmlStatement extends Statement{
    private Element xmlRoot;
    private Document xmlDocument;

    public XmlStatement(Transaction transaction) {
        super(transaction);
        this.xmlRoot = XmlUtils.getDocumentRoot("statement");
        this.xmlDocument = this.xmlRoot.getOwnerDocument();
    }

    @Override
    protected void addStatementHeaderData() {
        this.xmlRoot.appendChild(this.customer.getXmlElement(xmlDocument));
    }

    @Override
    protected void addRentalSummaryByType(List<Rental> rentals) {
        if (rentals.isEmpty()) {
            return;
        }

        // Create XML list by type
        Element typeList = rentals.get(0).getXmlList(this.xmlDocument);
        this.xmlRoot.appendChild(typeList);

        // Itemized list of rentals by type
        for (Rental rental : rentals) {
//            if (rental.isFreeRental()) {
//                continue;
//            }
//            this.totalAmount += rental.getRentalPrice();
//            this.getEarnedFrequentRenterPoints() += rental.getFrequentRentalPoints();

            typeList.appendChild(rental.getXmlElement(this.xmlDocument));
        }
    }

    @Override
    protected void addStatementFooterData() {
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

        XmlUtils.addChild(this.xmlDocument, this.xmlRoot, "subtotal", String.valueOf(this.totalAmount));
    }

    @Override
    public String toString() {
        return XmlUtils.getDocumentString(this.xmlDocument);
    }
}
