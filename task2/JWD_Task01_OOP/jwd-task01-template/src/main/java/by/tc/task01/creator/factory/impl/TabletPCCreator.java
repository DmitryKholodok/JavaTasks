package by.tc.task01.creator.factory.impl;

import by.tc.task01.creator.factory.Creator;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.TabletPC;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.util.Map;

public class TabletPCCreator implements Creator {

    @Override
    public Appliance create(Object appData) {
        Map<String, String> dataMap = (Map<String, String>)appData;
        int batteryCapacity = Integer.valueOf(dataMap.get(SearchCriteria.TabletPC.BATTERY_CAPACITY.name()));
        int displayInches = Integer.valueOf(dataMap.get(SearchCriteria.TabletPC.DISPLAY_INCHES.name()));
        int memoryRom = Integer.valueOf(dataMap.get(SearchCriteria.TabletPC.MEMORY_ROM.name()));
        int flashMemoryCapacity = Integer.valueOf(dataMap.get(SearchCriteria.TabletPC.FLASH_MEMORY_CAPACITY.name()));
        String color = String.valueOf(dataMap.get(SearchCriteria.TabletPC.COLOR.name()));
        return new TabletPC(batteryCapacity, displayInches, memoryRom, flashMemoryCapacity, color);
    }
}
