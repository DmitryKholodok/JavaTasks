package by.kholodok.task1.entity;

import by.kholodok.task1.observer.Observer;

/**
 * Created by dmitrykholodok on 11/9/17
 */
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
