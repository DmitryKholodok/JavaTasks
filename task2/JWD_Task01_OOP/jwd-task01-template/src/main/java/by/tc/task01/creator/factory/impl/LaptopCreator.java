package by.tc.task01.creator.factory.impl;

import by.tc.task01.creator.factory.Creator;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.Laptop;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.util.Map;

public class LaptopCreator implements Creator {

    @Override
    public Appliance create(Object appData) {
        Map<String, String> dataMap = (Map<String, String>)appData;
        double batteryCapacity = Double.valueOf(dataMap.get(SearchCriteria.Laptop.BATTERY_CAPACITY.name()));
        String os = String.valueOf(dataMap.get(SearchCriteria.Laptop.OS.name()));
        int memoryRom = Integer.valueOf(dataMap.get(SearchCriteria.Laptop.MEMORY_ROM.name()));
        int systemMemory = Integer.valueOf(dataMap.get(SearchCriteria.Laptop.SYSTEM_MEMORY.name()));
        double cpu = Double.valueOf(dataMap.get(SearchCriteria.Laptop.CPU.name()));
        int displayInches = Integer.valueOf(dataMap.get(SearchCriteria.Laptop.DISPLAY_INCHES.name()));
        return new Laptop(batteryCapacity, os, memoryRom, systemMemory, cpu, displayInches);
    }
}
