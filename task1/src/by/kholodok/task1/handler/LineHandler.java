package by.kholodok.task1.handler;

public class LineHandler {

    public static final String SPACE = " ";
    public static final String COMMA = ",";

    public static String deleteQuadrWasteInfo(String str) {
        str = str.trim();
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace(", ", ",");
        return str;
    }

    public static String deletePointWasteInfo(String str) {
        str = str.trim();
        str = str.replace("(", "");
        str = str.replace(")", "");
        return str;
    }

}
