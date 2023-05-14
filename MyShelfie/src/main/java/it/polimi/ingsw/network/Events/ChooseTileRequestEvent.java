package it.polimi.ingsw.network.Events;

/**
 * Message that contains the cell selected by the player.
 */

public class ChooseTileRequestEvent extends Event{


    /**
     * Default constructor.
     *
     */
    public ChooseTileRequestEvent(String user) {
        super(user, EventType.CHOOSE_TILE_REQUEST);
    }

    /**
     * Returns the cell that the player has selected.
     *
     * @return the row and the column of the tile selected..
     */




    public String toString() {
        return getUser() + " has to pick a tile from the board.";
    }


}
