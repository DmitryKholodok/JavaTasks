package by.tc.task01.dao.impl;

import by.tc.task01.dao.ApplianceDAO;
import by.tc.task01.dao.connection.Connection;
import by.tc.task01.dao.connection.ConnectionFactory;
import by.tc.task01.creator.AppCreator;
import by.tc.task01.parser.LineParser;
import by.tc.task01.checker.VarsChecker;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class ApplianceDAOImpl implements ApplianceDAO{

	@Override
	public <E> Appliance find(Criteria<E> criteria) {
		Connection connection = ConnectionFactory.getInstance().getConnection();
		try {
			try (BufferedReader in = (BufferedReader)connection.connect()) {
                return receiveApp(in, criteria);
            }
		} catch (IOException e) {
			return null;
		}
	}

	private <E> Appliance receiveApp(BufferedReader in, Criteria<E> criteria) {
		String line;
		LineParser parser = new LineParser();
		VarsChecker checker = new VarsChecker();
		AppCreator creator = new AppCreator();
		try {
			while ((line = in.readLine()) != null) {
				Map<String, String> dataMapFromFile = parser.parse(line, criteria.getApplianceType());
				if (dataMapFromFile != null) {
					if (checker.isSuitable(dataMapFromFile, criteria)) {
						return creator.createApp(criteria.getApplianceType(), dataMapFromFile);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}


