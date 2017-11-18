package by.tc.task01.dao.connection.impl;

import by.tc.task01.dao.connection.Connection;

import java.io.*;

public class FileConnection implements Connection {

    private static final String DEFAULT_NAME = "src/main/resources/appliances_db.txt";
    private String filename;
    private Reader reader;

    public FileConnection() {
        this(DEFAULT_NAME);
    }

    public FileConnection(String filename) {
        this.filename = filename;
    }

    @Override
    public Reader connect() {
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            return null;
        }
        return reader;
    }

}
