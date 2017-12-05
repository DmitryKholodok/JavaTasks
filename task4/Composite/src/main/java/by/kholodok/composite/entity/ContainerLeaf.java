package by.kholodok.composite.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrykholodok on 11/16/17
 */

public class ContainerLeaf implements Component {

    private ComponentType componentType;
    private String textValue;

    public ContainerLeaf(ComponentType componentType, String textValue) {
        this.componentType = componentType;
        this.textValue = textValue;
    }

    @Override
    public void addComponent(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeComponent(Component component) {
        return false;
    }

    @Override
    public List<Component> receiveComponentList(ComponentType componentType) {
        List<Component> componentListResult = null;
        if (this.componentType.equals(componentType)) {
            componentListResult = new ArrayList<>();
            componentListResult.add(this);
        }
        return componentListResult;
    }

    @Override
    public void updateComponent(ComponentType componentType, String TextValue) {
        this.componentType = componentType;
        this.textValue = TextValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (getClass() != o.getClass()) {
            return this.toString().trim().equals(o.toString().trim());
        } else {
            ContainerLeaf wordLeaf = (ContainerLeaf) o;
            if (componentType != wordLeaf.componentType) return false;
            return textValue != null ? textValue.equals(wordLeaf.textValue) : wordLeaf.textValue == null;
        }
    }

    @Override
    public int hashCode() {
        int result = componentType != null ? componentType.hashCode() : 0;
        result = 31 * result + (textValue != null ? textValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        switch (componentType) {
            case WORD:
                return " " + textValue;
            default:
                return textValue;
        }
    }
}
