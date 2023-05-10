package it.polimi.ingsw.network.Events;

public class ChooseTileReplyEvent extends Event{
    public ChooseTileReplyEvent(String user, int row, int column) {
        super(user, EventType.CHOOSE_TILE_REPLY);
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



