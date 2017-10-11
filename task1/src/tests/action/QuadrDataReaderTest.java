package tests.action;

import by.kholodok.task1.creator.Creator;
import by.kholodok.task1.creator.impl.QuadrCreator;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.parser.impl.QuadrParser;
import by.kholodok.task1.reader.DataReader;
import by.kholodok.task1.reader.impl.QuadrDataReader;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.validation.impl.QuadrValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuadrDataReaderTest {

    private final String FILE_NAME = "src/file/quadrs";
    private final String wrongName = "Wrong name";
    private Creator creator;
    private List<Entity> expectedListQuadr = new ArrayList<>();

    @BeforeClass
    public void listInit() throws IOException {
        Point[] points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 1);
        points[3] = new Point(1, 1);
        Quadrilateral quadr = new Quadrilateral(points);
        expectedListQuadr.add(quadr);

        points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 2);
        points[1] = new Point(1, -1);
        points[2] = new Point(1, 1.1);
        points[3] = new Point(1, 1);
        quadr = new Quadrilateral(points);
        expectedListQuadr.add(quadr);

        points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 5);
        points[1] = new Point(1.0231, -1);
        points[2] = new Point(1, 1);
        points[3] = new Point(1, 1);
        quadr = new Quadrilateral(points);
        expectedListQuadr.add(quadr);

        creator = new QuadrCreator(new QuadrValidator(), new QuadrParser());
    }

    @Test
    public void testFileReading() throws IOException {
        QuadrDataReader dataReader = new QuadrDataReader();
        List<String> linesFromFile = dataReader.read(FILE_NAME);
        List<Entity> actualListQuadr = creator.create(linesFromFile);
        Assert.assertEquals(actualListQuadr, expectedListQuadr);
    }

    @Test(expectedExceptions = IOException.class)
    public void testIOExcetion() throws IOException {
        QuadrDataReader dataReader = new QuadrDataReader();
        dataReader.read(wrongName);
    }

}
