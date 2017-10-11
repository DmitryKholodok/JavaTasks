package by.kholodok.task1.action;

import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.exception.NotQuadrException;
import by.kholodok.task1.entity.Quadrilateral;

public interface Action {

    double calcArea(Entity entity) throws NotQuadrException;
    double calcPerimeter(Entity entity) throws NotQuadrException;
    boolean isConvex(Entity entity) throws NotQuadrException;
    boolean isQuadr(Entity entity);
    boolean isSquare(Entity entity) throws NotQuadrException;

}
