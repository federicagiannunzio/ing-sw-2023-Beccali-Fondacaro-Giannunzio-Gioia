package it.polimi.ingsw.controller.Observer;

import it.polimi.ingsw.network.Events.Event;

public interface Observer {
    void update(Event event);

}
