package it.polimi.ingsw.network.Events;

public class TileArrayReplyEvent extends Event{

    private final int ntiles;
    public TileArrayReplyEvent(String user, int ntiles) {
        super(user, EventType.TILE_ARRAY_REPLY);
        this.ntiles = ntiles;
    }

    public int getNtiles(){
        return ntiles;
    }

    @Override
    public String toString(){
        return getUser() + "wants to take the number " + getNtiles() + "tile from the array";
    }
}
