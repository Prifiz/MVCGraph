package model;

import view.Observer;

public interface Observable {
    void registerObserver(Observer observer);
    void notifyAllObservers();
}
