package by.kholodok.task1.collection;

import by.kholodok.task1.entity.Quadrilateral;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrykholodok on 11/9/17
 */

public class QuadrCollection {

    private List<Quadrilateral> quadrList = new ArrayList<>();

    private static QuadrCollection instance;

    private QuadrCollection() {}

    public static QuadrCollection getInstance() {
        if (instance == null) {
            instance = new QuadrCollection();
        }
        return instance;
    }

    public void addQuadr(Quadrilateral quadr) {
        quadrList.add(quadr);
    }
    public void removeQuadr(Quadrilateral quadr) {
        quadrList.remove(quadr);
    }
}
