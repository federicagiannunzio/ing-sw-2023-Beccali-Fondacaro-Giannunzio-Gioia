package it.polimi.ingsw.network.Events;

/**
 * Message that contains the cell selected by the player.
 */

public class ChooseTileRequestEvent extends Event{
    public final int tilesNumber;

    /**
     * Default constructor.
     *
     */
    public ChooseTileRequestEvent(String user, int tilesNumber) {
        super(user, EventType.CHOOSE_TILE_REQUEST);
        this.tilesNumber = tilesNumber;
    }

    /**
     * Returns the cell that the player has selected.
     *
     * @return the row and the column of the tile selected..
     */
    public int getTilesNumber(){
        return tilesNumber;
    }



    public String toString() {
        return getUser() + " has to pick " + tilesNumber + " tiles from the board.";
    }


}
