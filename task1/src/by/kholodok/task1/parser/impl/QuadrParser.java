package by.kholodok.task1.parser.impl;

import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.handler.LineHandler;
import by.kholodok.task1.handler.impl.PointLineHandler;
import by.kholodok.task1.handler.impl.QuadrLineHandler;
import by.kholodok.task1.parser.Parser;

public class QuadrParser implements Parser {

    private final PointParser pointParser = new PointParser();

    @Override
    public Entity parse(String str) {
        str = deleteWasteInfo(str);
        String[] strPoints = splitLine(str);
        Point[] points = new Point[Quadrilateral.SIDES_COUNT];
        for(int i = 0; i < strPoints.length; i++)
            points[i] = (Point)pointParser.parse(strPoints[i]);
        return new Quadrilateral(points);
    }

    private String deleteWasteInfo(String str) {
        return new QuadrLineHandler().deleteWasteInfo(str);
    }

    private String[] splitLine(String str) {
        return str.split(LineHandler.SPACE);
    }

}
