package it.polimi.ingsw.Observer;

import it.polimi.ingsw.network.Events.Event;

public interface Observer {
    void update(Event event);

}
