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
}
