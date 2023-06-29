package it.polimi.ingsw.network.Events;


/**
 * Event used to ask the client how many tiles wants to pick
 */
public class TilesNumberRequestEvent extends Event{

    public TilesNumberRequestEvent(String user) {
        super(user, EventType.TILES_NUMBER_REQUEST);
    }

    public String toString(){
        return "Requested the number of tiles to the user.";
    }


}

