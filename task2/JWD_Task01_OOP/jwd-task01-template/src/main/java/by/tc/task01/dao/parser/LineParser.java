package by.tc.task01.dao.parser;

import by.tc.task01.dao.creator.AppCreator;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;

import java.util.HashMap;
import java.util.Map;

public class LineParser {

    public  Map<String, String> parse(String line, String appType) {
        String[] lineParts = line.split(":");
        if (!lineParts[0].trim().equals(appType)) return null;
        lineParts[1] = lineParts[1].substring(0, lineParts[1].length() - 1);
        Map<String, String> dataMapFromFile = createDataMap(lineParts[1]);
        return dataMapFromFile;
    }

    private Map<String, String> createDataMap(String dataLine) {
        Map<String, String> dataMap = new HashMap<>();
        String[] vars = dataLine.split(",");
        for (String var : vars) {
            String[] dataVar = var.split("=");
            dataMap.put(dataVar[0].trim(), dataVar[1].trim());
        }
        return dataMap;
    }

}
