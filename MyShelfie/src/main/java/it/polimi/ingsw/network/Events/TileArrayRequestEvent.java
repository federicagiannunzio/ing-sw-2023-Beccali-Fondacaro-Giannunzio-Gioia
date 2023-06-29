package it.polimi.ingsw.network.Events;

import it.polimi.ingsw.model.Tile;

import java.util.List;

/**
 * Event used to ask the player which tile of the array wants to place in the shelf
 * */
public class TileArrayRequestEvent extends Event {
    private final List<Tile> tilesList;
    public TileArrayRequestEvent(String user, List<Tile> tilesList) {
        super(user, EventType.TILE_ARRAY_REQUEST);
        this.tilesList = tilesList;
    }

    public List<Tile> getTilesList(){
        return tilesList;
    }

    public String toString() {
        return getUser() + " has to choose which tile to pick from the array;";
    }
}

