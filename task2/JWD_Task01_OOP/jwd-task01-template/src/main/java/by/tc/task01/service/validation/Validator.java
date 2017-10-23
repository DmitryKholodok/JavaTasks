package by.tc.task01.service.validation;

import by.tc.task01.entity.criteria.Criteria;
import com.sun.tools.javac.code.Attribute;

import java.util.Set;

public class Validator {

	public static <E> boolean criteriaValidator(Criteria<E> criteria) {

		if (criteria == null) return false;

		Set<E> criteriaSet = criteria.getAllCriteria();
		if (criteriaSet.isEmpty()) return false;

		for (E crit: criteriaSet) {
			if (!isValidValueType(criteria.getCriteriaValue(crit)))
				return false;
		}

		return true;
	}

	private static boolean isValidValueType(Object valueType) {
		if (valueType.getClass() == Integer.class || valueType.getClass() == String.class || valueType.getClass() == Double.class) {
			return true;
		}
		return false;
	}

}
