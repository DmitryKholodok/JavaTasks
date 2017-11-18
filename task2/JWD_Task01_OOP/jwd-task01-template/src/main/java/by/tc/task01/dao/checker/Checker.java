package by.tc.task01.dao.checker;

import by.tc.task01.entity.criteria.Criteria;

import java.util.Map;

public class Checker {

    public <E> boolean isSuitable(Map<String, String> dataMapFromFile, Criteria<E> criteria) {
        for (E key : criteria.getParams()) {
            String varValueFromSource = dataMapFromFile.get(key.toString());
            Object varValueFromCriteria = criteria.getValue(key);
            if (!varValueFromSource.equals(varValueFromCriteria.toString())) {
                return false;
            }
        }
        return true;
    }

}
