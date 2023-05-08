package it.polimi.ingsw.controller.Observer;

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

    /**
     * Notifies all the current observers through the update method and passes to them a {@link Event}.
     *
     * @param event the message to be passed to the observers.
     */
    public void notifyObserver(Event event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }


}
