package by.kholodok.task1.action;

import by.kholodok.task1.entity.Quadrilateral;

import java.io.IOException;
import java.util.List;

public interface DataReader {
    List<Quadrilateral> read(String fileName) throws IOException;
}
