package tests.observer;

import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.observer.impl.AreaObserver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by dmitrykholodok on 11/9/17
 */

public class AreaObserverTest {

    Quadrilateral square;

    @BeforeClass
    public void init() {
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(5, 3);
        points[2] = new Point(4, 3);
        points[3] = new Point(3, 1);
        square = new Quadrilateral(points);
    }

    @Test
    public void testUpdate() {
        AreaObserver areaObserver = new AreaObserver();
        square.addObserver(areaObserver);
        Point[] points = new Point[4];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 3);
        points[2] = new Point(3, 3);
        points[3] = new Point(3, 1);
        square.setPoints(points);
        Assert.assertEquals(areaObserver.getQuadrArea(), 4, 0.00000001);
    }

    @Test
    public void testNoUpdate() {
        AreaObserver areaObserver = new AreaObserver();
        square.addObserver(areaObserver);
        Assert.assertEquals(areaObserver.getQuadrArea(), 0, 0.00000001);
    }

}
