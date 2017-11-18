package tests.creator;

import by.kholodok.task1.creator.Creator;
import by.kholodok.task1.creator.impl.QuadrCreator;
import by.kholodok.task1.entity.Entity;
import by.kholodok.task1.entity.Point;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.parser.impl.QuadrParser;
import by.kholodok.task1.reader.impl.QuadrDataReader;
import by.kholodok.task1.validator.impl.QuadrValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.reader.QuadrDataReaderTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuadrCreatorTest {

    private final Creator creator = new QuadrCreator(new QuadrValidator(), new QuadrParser());
    private final List<Entity> expectedListEntity = new ArrayList<>();
    private List<String> actualListString;

    @BeforeClass
    public void listInit() throws IOException {
        
        Point[] points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 1);
        points[1] = new Point(1, 1);
        points[2] = new Point(1, 1);
        points[3] = new Point(1, 1);
        Quadrilateral quadr = new Quadrilateral(points);
        expectedListEntity.add(quadr);

        points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 2);
        points[1] = new Point(1, -1);
        points[2] = new Point(1, 1.1);
        points[3] = new Point(1, 1);
        quadr = new Quadrilateral(points);
        expectedListEntity.add(quadr);

        points = new Point[Quadrilateral.SIDES_COUNT];
        points[0] = new Point(1, 5);
        points[1] = new Point(1.0231, -1);
        points[2] = new Point(1, 1);
        points[3] = new Point(1, 1);
        quadr = new Quadrilateral(points);
        expectedListEntity.add(quadr);

        actualListString = new QuadrDataReader().read(QuadrDataReaderTest.FILE_NAME);

    }

    @Test
    public void testQuadrCreating() {
        List<Entity> actualListEntity = creator.create(actualListString);
        Assert.assertEquals(actualListEntity, expectedListEntity);
    }

}
