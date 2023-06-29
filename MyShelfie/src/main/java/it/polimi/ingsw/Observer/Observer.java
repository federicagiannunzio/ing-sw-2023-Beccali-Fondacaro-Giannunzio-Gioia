package it.polimi.ingsw.Observer;

import it.polimi.ingsw.network.Events.Event;
/**
 * Observer interface. It supports a generic method of update.
 */
public interface Observer {
    void update(Event event);

}
