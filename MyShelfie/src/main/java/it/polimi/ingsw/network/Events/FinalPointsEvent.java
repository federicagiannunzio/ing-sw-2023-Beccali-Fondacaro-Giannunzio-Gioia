package it.polimi.ingsw.network.Events;

/**
 * Message that contains the points that the player has at the end of the game.
 */
public class FinalPointsEvent extends Event{

    private final int fpoints;

    /**
     * @param fpoints That the player has at the end of the game
     */
    public FinalPointsEvent(String user, int fpoints) {
        super(user, EventType.FINAL_POINTS);
        this.fpoints = fpoints;
    }

    /**
     *
     * @return the final points.
     */
    public int getFpoints(){
        return fpoints;
    }

    @Override
    public String toString() {
        return getUser() + "has" + getFpoints();
    }
}
