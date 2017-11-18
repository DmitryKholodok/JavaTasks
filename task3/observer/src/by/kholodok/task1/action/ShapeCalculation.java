package by.kholodok.task1.action;

import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.exception.ShapeException;

public interface ShapeCalculation {

    double calcArea(Entity entity) throws ShapeException;
    double calcPerimeter(Entity entity) throws ShapeException;

}
