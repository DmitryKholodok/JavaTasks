package by.kholodok.composite.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitrykholodok on 11/19/17
 */

public class VarsCounter {

    private static Map<String, Integer> varNameToValueMap;
    static {
        varNameToValueMap = new HashMap<>();
    }

    public static void incVar(String varName) {
        int newValue = varNameToValueMap.get(varName);
        varNameToValueMap.put(varName, ++newValue);
    }

    public static void decVar(String varName) {
        int newValue = varNameToValueMap.get(varName);
        varNameToValueMap.put(varName, --newValue);
    }

    public static void addVar(String varName, int value) {
        varNameToValueMap.put(varName, value);
    }

    public static int getValue(String varName) {
        return varNameToValueMap.get(varName);
    }

}
