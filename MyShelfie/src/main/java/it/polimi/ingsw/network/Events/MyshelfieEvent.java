package it.polimi.ingsw.network.Events;

import it.polimi.ingsw.model.Tile;

import java.util.Arrays;

/**
 * Message that contains the personal shelfie. Used to send the updated shelfie to the client.
 */
public class MyshelfieEvent extends Event{

    private final Tile[][] shelfie;

    /**
     * Default constructor.
     *
     * @param shelfie
     */
    public MyshelfieEvent(String user, EventType eventType, Tile[][] shelfie) {
        super(user, EventType.MYSHELFIE);
        this.shelfie = shelfie;
    }

    /**
     * Returns the shelfie contained in the message.
     *
     * @return the current shelfie of the player.
     */
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

}
