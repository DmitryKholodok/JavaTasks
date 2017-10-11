package by.kholodok.task1.parser.impl;

import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.handler.LineHandler;
import by.kholodok.task1.handler.impl.PointLineHandler;
import by.kholodok.task1.handler.impl.QuadrLineHandler;
import by.kholodok.task1.parser.Parser;

public class PointParser implements Parser {

    @Override
    public Entity parse(String str) {
        str = deleteWasteInfo(str);
        String[] coord = splitLine(str);
        return new Point(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
    }

    private String[] splitLine(String str) {
        return str.split(LineHandler.COMMA);
    }

    private String deleteWasteInfo(String str) {
        return new PointLineHandler().deleteWasteInfo(str);
    }

}
