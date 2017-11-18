package by.kholodok.task1.collection;

import by.kholodok.task1.observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dmitrykholodok on 11/9/17
 */

public class ObserverCollection {

    private Map<Long, List<Observer>> quadrObserverMap = new HashMap<>();

    private static ObserverCollection instance;

    private ObserverCollection() {}

    public static ObserverCollection getInstance() {
        if (instance == null) {
            instance = new ObserverCollection();
        }
        return instance;
    }

    public List<Observer> observers(long quadrId) {
        return quadrObserverMap.get(quadrId);
    }

    public void addObserver(long quadrId, Observer observer) {
        List<Observer> observerList = quadrObserverMap.get(quadrId);
        if (observerList == null) {
            observerList = new ArrayList<>();
            quadrObserverMap.put(quadrId, observerList);
        }
        observerList.add(observer);
    }

    public void removeObserver(long quadrId, Observer observer) {
        List<Observer> observerList = quadrObserverMap.get(quadrId);
        if (observerList != null) {
            observerList.remove(observer);
        }
    }
}
