package it.polimi.ingsw.network.Events;

/**
 * Event used to send to the server the number of the column of the shelf
 * in which the player wants to place his tiles
 */
public class PlaceTileReplyEvent extends Event{
    public PlaceTileReplyEvent(String user, int column) {
        super(user, EventType.PLACE_TILE_REPLY);
        this.column = column;
    }

    public final int column;

    public int getColumn() {
        return column;
    }
}
