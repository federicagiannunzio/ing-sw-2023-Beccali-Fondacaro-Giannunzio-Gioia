package it.polimi.ingsw.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Custom observable class that can be observed by implementing the {@link ViewObserver} interface and registering as listener.
 */
public abstract class ViewObservable {

    protected final List<ViewObserver> observers = new ArrayList<>();

    /**
     * Adds an observer.
     *
     * @param observer the observer to be added.
     */
    public void addObserver(ViewObserver observer){
        observers.add(observer);
    }


    protected void notifyObserver(Consumer<ViewObserver> consumer) {
        for (ViewObserver observer : observers) {
            consumer.accept(observer);
        }
    }


}
