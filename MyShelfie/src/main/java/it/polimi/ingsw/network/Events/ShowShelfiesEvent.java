package it.polimi.ingsw.network.Events;

import it.polimi.ingsw.model.Tile;

import java.util.Arrays;

public class ShowShelvesEvent extends Event{
    private final Tile[][] shelfie;
    public ShowShelvesEvent(String user, Tile[][] shelfie) {
        super(user, EventType.SHOWSHELFIE;
        this.shelfie = shelfie;
    }

    public Tile[][] getBoard() {
        return shelfie;
    }

    @Override
    public String toString() {
        return "MyshelfieEvent{" +
                "board=" + Arrays.deepToString(shelfie) +
                '}';
    }
}
