package it.polimi.ingsw.network.Events;

public class PlaceTileRequestEvent extends Event{


    public PlaceTileRequestEvent(String user, EventType eventType) {
        super(user, EventType.PLACE_TILE_REPLY);
    }



}
