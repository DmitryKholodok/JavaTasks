package by.kholodok.task1.action;

import by.kholodok.task1.action.exception.NotQuadrException;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;

public interface Action {

    double calcArea(Quadrilateral quadr) throws NotQuadrException;
    double calcPerimeter(Quadrilateral quadr) throws NotQuadrException;
    boolean isConvex(Quadrilateral quadr) throws NotQuadrException;
    boolean isQuadr(Quadrilateral quadr);
    boolean isSquare(Quadrilateral quadr) throws NotQuadrException;

}
