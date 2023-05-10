package it.polimi.ingsw.network.Events;

public class PlaceTileRequestEvent extends Event{
    public PlaceTileRequestEvent(String user, int column) {
        super(user, EventType.PLACE_TILE_REQUEST);
        this.column = column;
    }

    public final int column;

    public int getcolumn() {
        return column;
    }



}
