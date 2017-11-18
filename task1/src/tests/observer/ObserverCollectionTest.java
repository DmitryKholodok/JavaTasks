package tests.observer;

import by.kholodok.task1.collection.ObserverCollection;
import by.kholodok.task1.collection.QuadrCollection;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.observer.Observer;
import by.kholodok.task1.observer.impl.AreaObserver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dmitrykholodok on 11/9/17
 */
public class ObserverCollectionTest {

    private Quadrilateral quadr;
    private Observer areaObserver;

    @BeforeClass
    private void init() {
        Point[] points = new Point[4];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 3);
        points[2] = new Point(3, 3);
        points[3] = new Point(3, 1);
        quadr = new Quadrilateral(points);
    }

    @Test
    public void testAddObserver() throws NoSuchFieldException, IllegalAccessException {
        areaObserver = new AreaObserver();
        List<Observer> observersExpected = new ArrayList<>();
        observersExpected.add(areaObserver);
        quadr.addObserver(areaObserver);

        ObserverCollection observerCollection = ObserverCollection.getInstance();
        Field field = observerCollection.getClass().getDeclaredField("quadrObserverMap");
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        Map<Integer, List<Observer>> map = (HashMap<Integer, List<Observer>>)field.get(observerCollection);
        List<Observer> observers = map.get(quadr.getQuadrId());
        Assert.assertEquals(observers, observersExpected);
    }

    @Test
    public void testRemoveObserver() throws NoSuchFieldException, IllegalAccessException {
        Point[] points = new Point[4];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 3);
        points[2] = new Point(3, 3);
        points[3] = new Point(3, 1);
        quadr.setPoints(points);
        quadr.removeObserver(areaObserver);

        ObserverCollection observerCollection = ObserverCollection.getInstance();
        Field field = observerCollection.getClass().getDeclaredField("quadrObserverMap");
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        Map<Integer, List<Observer>> map = (HashMap<Integer, List<Observer>>)field.get(observerCollection);
        List<Observer> observers = map.get(quadr.getQuadrId());
        Assert.assertEquals(observers.size(), 0);
    }



}
