package by.tc.task01.dao.creator;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.Oven;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.util.Map;

public class AppCreator {

    public Appliance createApp(String type, Map<String, String> dataMap) {
        Appliance app;
        switch (type) {
            case "Oven":
                app = new Oven(Double.valueOf(dataMap.get(SearchCriteria.Oven.POWER_CONSUMPTION.toString())),
                        Double.valueOf(dataMap.get(SearchCriteria.Oven.WEIGHT.toString())),
                        Double.valueOf(dataMap.get(SearchCriteria.Oven.CAPACITY.toString())),
                        Double.valueOf(dataMap.get(SearchCriteria.Oven.DEPTH.toString())),
                        Double.valueOf(dataMap.get(SearchCriteria.Oven.HEIGHT.toString())),
                        Double.valueOf(dataMap.get(SearchCriteria.Oven.WIDTH.toString())));
                break;
            default:
                app = null;
        }
        return app;
    }

}
