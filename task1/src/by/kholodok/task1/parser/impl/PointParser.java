package by.kholodok.task1.parser.impl;

import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.validator.handler.LineHandler;
import by.kholodok.task1.validator.handler.impl.PointLineHandler;
import by.kholodok.task1.parser.AbstractParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PointParser implements AbstractParser {

    private static final Logger LOGGER = LogManager.getLogger(PointParser.class);

    @Override
    public Entity parse(String str) {
        LOGGER.log(Level.DEBUG, "Parsing - " + str);
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
