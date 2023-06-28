package it.polimi.ingsw.controller.Observer;

import it.polimi.ingsw.View.View;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ViewObservable {

    protected final List<ViewObserver> observers = new ArrayList<>();

    public void addObserver(ViewObserver observer){
        observers.add(observer);
    }

    protected void notifyObserver(Consumer<ViewObserver> consumer) {
        for (ViewObserver observer : observers) {
            consumer.accept(observer);
        }
    }


}
