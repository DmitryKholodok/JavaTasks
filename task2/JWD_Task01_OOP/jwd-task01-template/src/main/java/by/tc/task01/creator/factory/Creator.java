package by.tc.task01.creator.factory;

import by.tc.task01.entity.Appliance;

public interface Creator {
    Appliance create(Object appData);
}
