package it.polimi.ingsw.network.Events;

import it.polimi.ingsw.model.Tile;

import java.util.Arrays;

public class ShowPersonalGoalEvent extends Event{
    private final Tile[][] shelfie;
    public ShowPersonalGoalEvent(String user, Tile[][] shelfie) {
        super(user, EventType.SHOWPERSONALGOAL);
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
