package by.kholodok.task1.action;

import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.exception.ShapeException;

public interface QuadrProperty extends PolygonProperty {

    boolean isSquare(Entity entity) throws ShapeException;

}
