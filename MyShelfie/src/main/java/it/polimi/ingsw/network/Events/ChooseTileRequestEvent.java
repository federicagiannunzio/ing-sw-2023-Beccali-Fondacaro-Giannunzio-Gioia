package it.polimi.ingsw.network.Events;

/**
 * Message that contains the cell selected by the player.
 */

public class ChooseTileRequestEvent extends Event{

    /**
     * Default constructor.
     *
     * @param row index for rows
     * @param column index for columns
     */
    public ChooseTileRequestEvent(int row, int column) {
        super(user, EventType.CHOOSE_TILE_REQUEST);
        this.row = row;
        this.column = column;
    }

    public final int row;

    public final int column;

    /**
     * Returns the cell that the player has selected.
     *
     * @return the row and the column of the tile selected..
     */
    public int getrow(){
        return row;
    }

    public int getcolumn() {
        return column;
    }


    public String toString() {
        return getUser() + "chose the Tile in the position" + "[row:" + getrow() + " ; column:"
                + getcolumn() + "]";
    }


}
