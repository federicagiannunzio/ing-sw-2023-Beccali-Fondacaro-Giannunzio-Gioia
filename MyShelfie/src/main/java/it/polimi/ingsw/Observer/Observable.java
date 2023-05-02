package it.polimi.ingsw.Observer;

import it.polimi.ingsw.network.Events.Event;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param observer the observer that will be added
     * */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer.
     *
     * @param observer the observer that will be removed
     * */
    public void removeObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObserver(Event event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }


}
