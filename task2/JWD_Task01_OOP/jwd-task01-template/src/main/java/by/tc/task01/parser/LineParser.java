package by.tc.task01.parser;

import java.util.HashMap;
import java.util.Map;

public class LineParser {

    private static final String COLON = ":";
    private static final String COMMA = ",";
    private static final String IS = "=";

    public  Map<String, String> parse(String line, String appType) {
        String[] lineParts = line.split(COLON);
        if (!lineParts[0].trim().equals(appType)) return null;
        lineParts[1] = lineParts[1].substring(0, lineParts[1].length() - 1);
        Map<String, String> dataMapFromFile = createDataMap(lineParts[1]);
        return dataMapFromFile;
    }

    private Map<String, String> createDataMap(String dataLine) {
        Map<String, String> dataMap = new HashMap<>();
        String[] vars = dataLine.split(COMMA);
        for (String var : vars) {
            String[] dataVar = var.split(IS);
            dataMap.put(dataVar[0].trim(), dataVar[1].trim());
        }
        return dataMap;
    }

}
