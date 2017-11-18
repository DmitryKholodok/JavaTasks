package by.kholodok.task1.validator.handler;

public interface LineHandler {

    String OPEN_BRACKET = "(";
    String CLOSE_BRACKET = ")";
    String RECT_OPEN_BRACKET = "[";
    String RECT_CLOSE_BRACKET = "]";
    String COMMA_SPACE = ", ";
    String COMMA = ",";
    String VOID = "";
    String SPACE_REGEX = "\\s";

    String deleteWasteInfo(String str);

}
