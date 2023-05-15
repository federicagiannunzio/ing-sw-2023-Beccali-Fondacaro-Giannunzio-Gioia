package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.controller.Observer.Observable;
import it.polimi.ingsw.network.Events.ErrorEvent;
import it.polimi.ingsw.network.Events.Event;

import java.io.IOException;

public abstract class Client extends Observable {

    public abstract void receiveMessage();

    public abstract void sendMessage(Event event);

    public abstract void closeConnection();
}
