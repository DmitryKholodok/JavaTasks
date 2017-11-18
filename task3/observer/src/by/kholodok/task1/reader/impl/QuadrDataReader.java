package by.kholodok.task1.reader.impl;

import by.kholodok.task1.reader.DataReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuadrDataReader implements DataReader {

    private final static Logger LOGGER = LogManager.getLogger(QuadrDataReader.class);

    public List<String> read(String filename) throws IOException {
        List<String> stringList;
        try(BufferedReader in = new BufferedReader(new FileReader(filename))) {
            stringList = in.lines().collect(Collectors.toList());
        }
        LOGGER.log(Level.INFO, stringList.toString());
        return stringList;
    }
}
