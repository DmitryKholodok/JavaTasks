package by.kholodok.task1.reader;

import java.io.IOException;
import java.util.List;

public interface DataReader {
    List<String> read(String fileName) throws IOException;
}
