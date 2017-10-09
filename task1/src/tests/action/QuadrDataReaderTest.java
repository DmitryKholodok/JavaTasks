package tests.action;

import by.kholodok.task1.action.DataReader;
import by.kholodok.task1.action.impl.QuadrDataReader;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuadrDataReaderTest {

    private static final String FILE_NAME = "src/files/param";
    private final DataReader dataReader = new QuadrDataReader();
    private List<Quadrilateral> listQuadr = new ArrayList<>();

    @BeforeClass
    public void listInit() {
        Point[] points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 1);
        points[3] = new Point(1, 1);
        Quadrilateral quadr = new Quadrilateral(points);
        listQuadr.add(quadr);

        points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 2);
        points[1] = new Point(1, -1);
        points[2] = new Point(1, 1.1);
        points[3] = new Point(1, 1);
        quadr = new Quadrilateral(points);
        listQuadr.add(quadr);

        points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 5);
        points[1] = new Point(1.0231, -1);
        points[2] = new Point(1, 1);
        points[3] = new Point(1, 1);
        quadr = new Quadrilateral(points);
        listQuadr.add(quadr);
    }

    @Test
    public void testFileReading() throws IOException {
        Assert.assertEquals(dataReader.read(FILE_NAME), listQuadr);
    }

    @Test(expectedExceptions = IOException.class)
    public void testIOExcetion() throws IOException {
        final String wrongName = "Wrong name";
        dataReader.read(wrongName);
    }

}
