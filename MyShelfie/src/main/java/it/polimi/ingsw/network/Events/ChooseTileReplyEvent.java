package it.polimi.ingsw.network.Events;

/**
 * Event used to send the server the tiles picked by a player
 * */
public class ChooseTileReplyEvent extends Event {
    private final int[] row;
    private final int[] col;

    public ChooseTileReplyEvent(String user, int[] row, int[] col) {
        super(user, EventType.CHOOSE_TILE_REPLY);
        this.row = row;
        this.col = col;
    }

    public int[] getRow(){
        return this.row;
    }
    public int[] getCol(){
        return this.col;
    }
}



