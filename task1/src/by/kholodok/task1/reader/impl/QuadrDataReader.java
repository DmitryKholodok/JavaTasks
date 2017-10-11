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

public class QuadrDataReader implements DataReader {

    private static Logger logger = LogManager.getLogger(QuadrDataReader.class);

    public List<String> read(String filename) throws IOException {
        List<String> stringList = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String str;
            while ((str = in.readLine()) != null) {
                stringList.add(str);
            }
        }
        logger.log(Level.INFO, stringList.toString());
        return stringList;
    }



}
