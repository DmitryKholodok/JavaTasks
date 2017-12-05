package by.kholodok.composite.entity;

import java.util.List;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public interface Component {
    void addComponent(Component component);
    boolean removeComponent(Component component);
    List<Component> receiveComponentList(ComponentType componentType);
    void updateComponent(ComponentType componentType, String newComponentValue);
}
