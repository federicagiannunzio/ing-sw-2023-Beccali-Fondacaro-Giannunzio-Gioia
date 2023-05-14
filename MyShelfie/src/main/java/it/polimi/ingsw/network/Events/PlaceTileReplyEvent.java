package it.polimi.ingsw.network.Events;

public class PlaceTileReplyEvent extends Event{
    public PlaceTileReplyEvent(String user, int column) {
        super(user, EventType.PLACE_TILE_REQUEST);
        this.column = column;
    }

    public final int column;

    public int getColumn() {
        return column;
    }
}
