public class StringUtil {
    public static final String USD = "$%.2f";
    public static final int LEFT_PAD = 10;
    public static final int RIGHT_PAD = 15;

    public static String padLeft(String string, int n) {
        return String.format("%" + n + "s", string);
    }

    public static String padRight(String string, int n) {
        return String.format("%-" + n + "s", string);
    }

    public static String getTableRow(int indentation, int newLines, String... strings) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0 ; i < indentation ; i++) {
            stringBuilder.append("\t");
        }

        for (int i = 0 ; i < strings.length ; i++) {
            if (i == (strings.length - 1)) {
                stringBuilder.append(padLeft(strings[i], LEFT_PAD));

            } else {
                stringBuilder.append(padRight(strings[i], RIGHT_PAD));
            }
        }

        for (int i = 0 ; i < newLines ; i++) {
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
