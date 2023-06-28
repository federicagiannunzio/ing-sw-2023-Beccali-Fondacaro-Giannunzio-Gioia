package it.polimi.ingsw.network.Events;


public class TilesNumberRequestEvent extends Event{

    public TilesNumberRequestEvent(String user) {
        super(user, EventType.TILES_NUMBER_REQUEST);
    }

    public String toString(){
        return "Requested the number of tiles to the user.";
    }


}

