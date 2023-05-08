package it.polimi.ingsw.network.Events;

public class PlaceTileReplyEvent extends Event{
    public PlaceTileReplyEvent(String user, EventType eventType) {
        super(user, EventType.PLACE_TILE_REPLY);
    }
}
