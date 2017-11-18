package by.tc.task01.creator.factory.impl;

import by.tc.task01.creator.factory.Creator;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.VacuumCleaner;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.util.Map;

public class VacuumCleanerCreator implements Creator {

    @Override
    public Appliance create(Object appData) {
        Map<String, String> dataMap = (Map<String, String>)appData;
        int powerConsumption = Integer.valueOf(dataMap.get(SearchCriteria.VacuumCleaner.POWER_CONSUMPTION.name()));
        String filterType = String.valueOf(dataMap.get(SearchCriteria.VacuumCleaner.FILTER_TYPE.name()));
        String bagType = String.valueOf(dataMap.get(SearchCriteria.VacuumCleaner.BAG_TYPE.name()));
        String wandType = String.valueOf(dataMap.get(SearchCriteria.VacuumCleaner.WAND_TYPE.name()));
        int motorSpeedRegulation = Integer.valueOf(dataMap.get(SearchCriteria.VacuumCleaner.MOTOR_SPEED_REGULATION.name()));
        int cleaningWidth = Integer.valueOf(dataMap.get(SearchCriteria.VacuumCleaner.CLEANING_WIDTH.name()));
        return new VacuumCleaner(powerConsumption, filterType, bagType, wandType, motorSpeedRegulation, cleaningWidth);
    }
}
