package by.tc.task01.creator.factory.impl;

import by.tc.task01.creator.factory.Creator;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.Refrigerator;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.util.Map;

public class RefrigeratorCreator implements Creator {
    
    @Override
    public Appliance create(Object appData) {
        Map<String, String> dataMap = (Map<String, String>)appData;
        int powerConsumption = Integer.valueOf(dataMap.get(SearchCriteria.Refrigerator.POWER_CONSUMPTION.name()));
        int weight = Integer.valueOf(dataMap.get(SearchCriteria.Refrigerator.WEIGHT.name()));
        int freezerCapacity = Integer.valueOf(dataMap.get(SearchCriteria.Refrigerator.FREEZER_CAPACITY.name()));
        double overallCapacity = Double.valueOf(dataMap.get(SearchCriteria.Refrigerator.OVERALL_CAPACITY.name()));
        int height = Integer.valueOf(dataMap.get(SearchCriteria.Refrigerator.HEIGHT.name()));
        int width = Integer.valueOf(dataMap.get(SearchCriteria.Refrigerator.WEIGHT.name()));
        return new Refrigerator(powerConsumption, weight, freezerCapacity, overallCapacity, height, width);
    }
}
