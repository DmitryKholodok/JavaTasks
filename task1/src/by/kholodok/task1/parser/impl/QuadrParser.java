package by.kholodok.task1.parser.impl;

import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.validator.handler.LineHandler;
import by.kholodok.task1.validator.handler.impl.QuadrLineHandler;
import by.kholodok.task1.parser.AbstractParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuadrParser implements AbstractParser {

    private final static Logger LOGGER = LogManager.getLogger(PointParser.class);
    private final AbstractParser pointParser = new PointParser();

    @Override
    public Entity parse(String str) {
        LOGGER.log(Level.DEBUG, "Parsing - " + str);
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
        return str.split(LineHandler.SPACE_REGEX);
    }

}
