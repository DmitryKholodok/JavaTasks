package by.kholodok.composite.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public class TextContainer implements Component {

    private ComponentType componentType;
    private List<Component> componentList = new ArrayList<Component>();

    public TextContainer(ComponentType componentType) {
        this.componentType = componentType;
    }

    @Override
    public void addComponent(Component component) {
        if (component != null) {
            componentList.add(component);
        }
    }

    @Override
    public boolean removeComponent(Component component) {
        boolean result = true;
        if (!componentList.remove(component)) {
            result = false;
            for (Component componentFromList : componentList) {
                if (componentFromList.removeComponent(component)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public List<Component> receiveComponentList(ComponentType componentType) {
        List<Component> componentListResult = null;
        if (this.componentType.equals(componentType)) {
            componentListResult = componentList;
        }
        else {
            componentListResult = new ArrayList<>();
            for(Component component : componentList) {
                List<Component> componentListTemp = component.receiveComponentList(componentType);
                if (componentListTemp != null) {
                    componentListResult.addAll(componentListTemp);
                }
            }
        }
        return componentListResult;
    }

    @Override
    public void updateComponent(ComponentType componentType, String textValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextContainer that = (TextContainer) o;

        if (componentType != that.componentType) return false;
        return componentList != null ? componentList.equals(that.componentList) : that.componentList == null;
    }

    @Override
    public int hashCode() {
        int result = componentType != null ? componentType.hashCode() : 0;
        result = 31 * result + (componentList != null ? componentList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String text = "";
        for(Component component : componentList) {
            text += component.toString();
        }
        return text;
    }
}
