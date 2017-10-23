package by.tc.task01.creator.factory.impl;

import by.tc.task01.creator.factory.Creator;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.Speakers;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.util.Map;

public class SpeakersCreator implements Creator {

    @Override
    public Appliance create(Object appData) {
        Map<String, String> dataMap = (Map<String, String>)appData;
        int powerConsumption = Integer.valueOf(dataMap.get(SearchCriteria.Speakers.POWER_CONSUMPTION.name()));
        int numberOfSpeakers = Integer.valueOf(dataMap.get(SearchCriteria.Speakers.NUMBER_OF_SPEAKERS.name()));
        String frequencyRange = String.valueOf(dataMap.get(SearchCriteria.Speakers.FREQUENCY_RANGE.name()));
        int cordLength = Integer.valueOf(dataMap.get(SearchCriteria.Speakers.CORD_LENGTH.name()));
        return new Speakers(powerConsumption, numberOfSpeakers, frequencyRange, cordLength);
    }
}
