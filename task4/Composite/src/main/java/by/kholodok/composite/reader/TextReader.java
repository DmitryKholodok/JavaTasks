package by.kholodok.composite.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public class TextReader {

    private static final String DEFAULT_FILENAME = "data/text.txt";

    private StringBuilder currStringBuilder;

    public String readAllFile() throws IOException {
        return this.readAllFile(DEFAULT_FILENAME);
    }

    public String readAllFile(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        currStringBuilder = new StringBuilder();
        FileReader fileReader = new FileReader(file);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
             bufferedReader.lines().forEach(x -> currStringBuilder.append(x));
        }
        return currStringBuilder.toString();
    }

}
