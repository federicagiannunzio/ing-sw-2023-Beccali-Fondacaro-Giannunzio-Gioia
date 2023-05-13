package it.polimi.ingsw.network.Events;

public class PlaceTileRequestEvent extends Event{


    public PlaceTileRequestEvent(String user) {
        super(user, EventType.PLACE_TILE_REQUEST);
    }



}
