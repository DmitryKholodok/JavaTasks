package tests.reader;

import by.kholodok.task1.reader.impl.QuadrDataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuadrDataReaderTest {

    public static final String FILE_NAME = "quadrs";
    private final String wrongName = "Wrong name";
    private final List<String> expectedListString = new ArrayList<>();
    
    @BeforeClass
    public void listInit() {
        expectedListString.add("[(1.0, 1.0) (1.0, 1.0) (1.0, 1.0) (1.0, 1.0)]");
        expectedListString.add("[[(1.0, 2.0) (1.0, -1.0) (1.0, 1.1) (1.0, 1.0)]]]");
        expectedListString.add("[(1.0,/ 3.0) (1.0, 1.0) (1.0, 1.0) (1.0, 1.0)]");
        expectedListString.add("[(1z.0, 4.0) (1.0, 1.0) (1.0, 1.0) (1.0, 1.0)]");
        expectedListString.add("[(1.0, 5.0) (1.0231, -1.0) (1.0, 1.0) (1.0, 1.0)]");
        expectedListString.add("[(1.0, 7.0) (1.0, 1.0) (1.0,  1.0) (1.0, 1.0)]");
    }

    @Test
    public void testFileReading() throws IOException {
        QuadrDataReader dataReader = new QuadrDataReader();
        List<String> actualListString = dataReader.read(FILE_NAME);
        Assert.assertEquals(actualListString, expectedListString);
    }

    @Test(expectedExceptions = IOException.class)
    public void testIOExcetion() throws IOException {
        QuadrDataReader dataReader = new QuadrDataReader();
        dataReader.read(wrongName);
    }

}
