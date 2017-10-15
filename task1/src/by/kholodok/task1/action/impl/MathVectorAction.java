package by.kholodok.task1.action.impl;

import by.kholodok.task1.action.MathVector;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Vector;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MathVectorAction implements MathVector {

    private static final Logger LOGGER = LogManager.getLogger(MathVectorAction.class);

    public double calculateVectorsWork(Vector vect1, Vector vect2) {
        LOGGER.log(Level.DEBUG, "calculate vector's work: vector1 - " + vect1 + ", vector2 - " + vect2);
        Point coord1 = calculateVectCoords(vect1);
        Point coord2 = calculateVectCoords(vect2);
        return coord1.getX() * coord2.getY() - coord2.getX() * coord1.getY();
    }

    private Point calculateVectCoords(Vector vect) {
        return new Point(vect.getBeginP().getX() - vect.getEndP().getX(),
                vect.getBeginP().getY() - vect.getEndP().getY());
    }

}
