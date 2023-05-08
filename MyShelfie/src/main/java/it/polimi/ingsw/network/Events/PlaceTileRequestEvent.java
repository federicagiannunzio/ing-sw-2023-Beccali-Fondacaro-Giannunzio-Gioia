package it.polimi.ingsw.network.Events;

public class PlaceTileRequestEvent extends Event{
    public PlaceTileRequestEvent(String user, EventType eventType, int row, int column) {
        super(user, EventType.PLACE_TILE_REQUEST);
        this.row = row;
        this.column = column;
    }

    public final int row;

    public final int column;

    public int getrow(){
        return row;
    }

    public int getcolumn() {
        return column;
    }



}
