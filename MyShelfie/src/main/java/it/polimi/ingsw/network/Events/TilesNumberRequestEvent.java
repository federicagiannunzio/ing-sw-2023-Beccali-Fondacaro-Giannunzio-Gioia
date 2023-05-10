package it.polimi.ingsw.network.Events;


public class TilesNumberRequestEvent extends Event{
    private int numberOfTiles;

    public TilesNumberRequestEvent(String user,int numberOfTiles) {
        super(user, EventType.TILES_NUMBER_REQUEST);
        this.numberOfTiles = numberOfTiles;
    }

    public int getNumberOfTiles(){
        return this.numberOfTiles;
    }



}

