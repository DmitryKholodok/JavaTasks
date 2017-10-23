package by.tc.task01.creator.factory.impl;

import by.tc.task01.creator.factory.Creator;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.Oven;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.util.Map;

public class OvenCreator implements Creator {

    @Override
    public Appliance create(Object appData) {
        Map<String, String> dataMap = (Map<String, String>)appData;
        int powerConsumption = Integer.valueOf(dataMap.get(SearchCriteria.Oven.POWER_CONSUMPTION.name()));
        int weight = Integer.valueOf(dataMap.get(SearchCriteria.Oven.WEIGHT.name()));
        int capacity = Integer.valueOf(dataMap.get(SearchCriteria.Oven.CAPACITY.name()));
        int depth = Integer.valueOf(dataMap.get(SearchCriteria.Oven.DEPTH.name()));
        double height = Double.valueOf(dataMap.get(SearchCriteria.Oven.HEIGHT.name()));
        double width = Double.valueOf(dataMap.get(SearchCriteria.Oven.WIDTH.name()));
        return new Oven(powerConsumption, weight, capacity, depth, height, width);
    }
}
