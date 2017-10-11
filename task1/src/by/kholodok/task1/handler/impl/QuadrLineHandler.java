package by.kholodok.task1.handler.impl;

import by.kholodok.task1.handler.LineHandler;

public class QuadrLineHandler implements LineHandler {

    @Override
    public String deleteWasteInfo(String str) {
        str = str.trim();
        str = str.replace(RECT_OPEN_BRACKET, VOID);
        str = str.replace(RECT_CLOSE_BRACKET, VOID);
        str = str.replace(COMMA + SPACE, COMMA);
        return str;
    }
}
