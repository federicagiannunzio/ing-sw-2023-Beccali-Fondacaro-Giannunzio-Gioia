package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.Observer.Observable;
import it.polimi.ingsw.network.Events.Event;

public abstract class Client extends Observable {

    public abstract void receiveMessage();

    public abstract void sendMessage(Event event);

    public abstract void closeConnection();
}
