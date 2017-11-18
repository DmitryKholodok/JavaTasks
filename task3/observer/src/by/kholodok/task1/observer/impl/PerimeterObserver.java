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

public class PerimeterObserver implements Observer {

    private static final Logger LOGGER = LogManager.getLogger(PerimeterObserver.class);

    private double quadrPerimeter;

    public double getPerimeter() {
        return quadrPerimeter;
    }

    @Override
    public void update(Quadrilateral quadr) {
        try {
            quadrPerimeter = new QuadrAction().calcPerimeter(quadr);
            LOGGER.log(Level.INFO, "Quadr id = " + quadr.getQuadrId() + ". New perimeter is " + quadrPerimeter);
        } catch (ShapeException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}
