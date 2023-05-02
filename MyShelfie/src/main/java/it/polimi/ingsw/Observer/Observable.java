package it.polimi.ingsw.Observer;

import it.polimi.ingsw.network.Messages.Message;

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

    public void notifyObserver(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }


}
