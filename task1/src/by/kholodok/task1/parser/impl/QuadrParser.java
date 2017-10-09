package by.kholodok.task1.parser.impl;

import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.entity.Shape;
import by.kholodok.task1.handler.LineHandler;
import by.kholodok.task1.parser.ShapeParser;

public class QuadrParser implements ShapeParser {

    private final PointParser pointParser = new PointParser();

    @Override
    public Shape parse(String str) {
        str = deleteWasteInfo(str);
        String[] strPoints = splitLine(str);
        Point[] points = new Point[Quadrilateral.SIDES_COUNT];
        for(int i = 0; i < strPoints.length; i++)
            points[i] = (Point)pointParser.parse(strPoints[i]);
        return new Quadrilateral(points);
    }

    private String deleteWasteInfo(String str) {
        return LineHandler.deleteQuadrWasteInfo(str);
    }

    private String[] splitLine(String str) {
        return str.split(LineHandler.SPACE);
    }

}
