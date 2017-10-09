package by.kholodok.task1.action.impl;

import by.kholodok.task1.action.DataReader;
import by.kholodok.task1.entity.Quadrilateral;
import by.kholodok.task1.parser.impl.QuadrParser;
import by.kholodok.task1.validation.impl.QuadrValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuadrDataReader implements DataReader {

    private final Logger logger = LogManager.getLogger(QuadrDataReader.class);
    private final QuadrValidator quadrValidator = new QuadrValidator();
    private final QuadrParser quadrParser = new QuadrParser();

    public List<Quadrilateral> read(String fileName) throws IOException {
        List<Quadrilateral> listQuadr = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = in.readLine()) != null) {
                if (quadrValidator.isValid(str)) {
                    listQuadr.add((Quadrilateral)quadrParser.parse(str));
                    logger.info("Created a quadrilateral");
                } else {
                    logger.error("The wrong string was received");
                }
            }
        }
        return listQuadr;
    }



}
