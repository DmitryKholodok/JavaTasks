package by.kholodok.task1.validator.handler.impl;

import by.kholodok.task1.validator.handler.LineHandler;

public class PointLineHandler implements LineHandler {

    @Override
    public String deleteWasteInfo(String str) {
        str = str.trim();
        str = str.replace(OPEN_BRACKET, VOID);
        str = str.replace(CLOSE_BRACKET, VOID);
        return str;
    }
}
