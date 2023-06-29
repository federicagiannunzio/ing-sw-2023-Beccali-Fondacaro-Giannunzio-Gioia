package it.polimi.ingsw.network.Events;

/**
 * Event used to ask the player which tiles wants to pick. It that contains the number of tiles chosen by the player
 */

public class ChooseTileRequestEvent extends Event{
    private final int tilesNumber;


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
        return this.tilesNumber;
    }


    public String toString() {
        return getUser() + " has to pick a tile from the board.";
    }


}
