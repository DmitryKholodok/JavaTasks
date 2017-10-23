package by.tc.task01.creator;

import by.tc.task01.creator.factory.Creator;
import by.tc.task01.creator.factory.impl.*;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.Oven;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.util.Map;

public class AppCreator {

    public Appliance createApp(String type, Map<String, String> dataMap) {
        Creator creator = identifyCreator(type);
        return creator.create(dataMap);
    }

    private Creator identifyCreator(String type) {
        Creator creator;
        switch (type) {
            case "Oven" :
                creator = new OvenCreator();
                break;
            case "Laptop" :
                creator = new LaptopCreator();
                break;
            case "Refrigerator" :
                creator = new RefrigeratorCreator();
                break;
            case "VacuumCleaner" :
                creator = new VacuumCleanerCreator();
                break;
            case "TabletPC" :
                creator  = new TabletPCCreator();
                break;
            default:
                creator = new SpeakersCreator();
        }
        return creator;
    }

}
