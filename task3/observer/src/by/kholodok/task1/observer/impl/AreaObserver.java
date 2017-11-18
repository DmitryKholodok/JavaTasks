package by.kholodok.task1.observer.impl;

import by.kholodok.task1.action.impl.QuadrAction;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.exception.ShapeException;
import by.kholodok.task1.observer.Observer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by dmitrykholodok on 11/9/17
 */

public class AreaObserver implements Observer {

    private static final Logger LOGGER = LogManager.getLogger(AreaObserver.class);

    private double quadrArea;

    public double getQuadrArea() {
        return quadrArea;
    }

    @Override
    public void update(Quadrilateral quadr) {
        try {
            quadrArea = new QuadrAction().calcArea(quadr);
            LOGGER.log(Level.INFO, "Quadr id = " + quadr.getQuadrId() + ". New area is " + quadrArea);
        } catch (ShapeException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}
