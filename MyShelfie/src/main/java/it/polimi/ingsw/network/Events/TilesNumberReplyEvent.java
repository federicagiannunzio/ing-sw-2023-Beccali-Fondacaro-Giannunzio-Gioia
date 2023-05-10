package it.polimi.ingsw.network.Events;

/**
 * Message that contains the number of tiles that the player wants to take.
 */
public class TilesNumberReplyEvent extends Event{

    private final int ntiles;

    /**
     *
     * @param ntiles number of tiles that the player wants to take from the board
     */
    public TilesNumberReplyEvent(String user, EventType eventType, int ntiles) {
        super(user, EventType.TILES_NUMBER_REPLY);
        this.ntiles = ntiles;
    }

    public int getNtiles(){
        return ntiles;
    }

    @Override
    public String toString(){
        return getUser() + "wants to take" + getNtiles() + "tiles from the board";
    }






}
