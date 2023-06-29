package it.polimi.ingsw.network.Events;

/**
 * Event used to ask the client to place tiles in his shelf
 */
public class PlaceTileRequestEvent extends Event{


    public PlaceTileRequestEvent(String user) {
        super(user, EventType.PLACE_TILE_REQUEST);
    }



}
