package tests.observer;

import by.kholodok.task1.collection.QuadrCollection;
import by.kholodok.task1.creator.impl.QuadrCreator;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.parser.impl.QuadrParser;
import by.kholodok.task1.validator.impl.QuadrValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by dmitrykholodok on 11/14/17
 */

public class QuadrCollectionTest {

    Quadrilateral quadrExpected;

    @BeforeClass
    private void init() {
        Point[] points = new Point[4];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 3);
        points[2] = new Point(3, 3);
        points[3] = new Point(3, 1);
        quadrExpected = new Quadrilateral(points);
    }

    @Test
    public void testAddQuadr() throws NoSuchFieldException, IllegalAccessException {

        String quadrStr = "[(1.0, 1.0) (1.0, 3.0) (3.0, 3.0) (3.0, 1.0)]";
        new QuadrCreator(new QuadrValidator(), new QuadrParser()).create(quadrStr);

        QuadrCollection quadrCollection = QuadrCollection.getInstance();
        Field field = quadrCollection.getClass().getDeclaredField("quadrList");
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        List<Quadrilateral> list = (List<Quadrilateral>)field.get(quadrCollection);
        Quadrilateral quadrCurr = list.get(0);
        Assert.assertEquals(quadrCurr, quadrExpected);
    }

}
