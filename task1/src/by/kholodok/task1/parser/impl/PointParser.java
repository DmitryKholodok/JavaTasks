package by.kholodok.task1.parser.impl;

import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Shape;
import by.kholodok.task1.handler.LineHandler;
import by.kholodok.task1.parser.ShapeParser;

public class PointParser implements ShapeParser {

    @Override
    public Shape parse(String str) {
        str = deleteWasteInfo(str);
        String[] coord = splitLine(str);
        return new Point(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
    }

    private String[] splitLine(String str) {
        return str.split(LineHandler.COMMA);
    }

    private String deleteWasteInfo(String str) {
        return LineHandler.deletePointWasteInfo(str);
    }

}
