package by.kholodok.task1.action;

import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.exception.ShapeException;

public interface PolygonProperty {

    boolean isQuadr(Entity entity);
    boolean isConvex(Entity entity) throws ShapeException;

}
