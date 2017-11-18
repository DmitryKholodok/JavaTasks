package by.tc.task01.checker;

import by.tc.task01.entity.criteria.Criteria;

import java.util.Map;

public class VarsChecker {

    public <E> boolean isSuitable(Map<String, String> dataMapFromFile, Criteria<E> criteria) {
        for (E key : criteria.getAllCriteria()) {
            String varValueFromSource = dataMapFromFile.get(key.toString());
            String varValueFromCriteria = String.valueOf(criteria.getCriteriaValue(key));
            if (!varValueFromSource.equalsIgnoreCase(varValueFromCriteria)) {
                return false;
            }
        }
        return true;
    }

}
