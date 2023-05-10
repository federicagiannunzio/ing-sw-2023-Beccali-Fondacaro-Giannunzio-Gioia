package it.polimi.ingsw.network.Events;

import it.polimi.ingsw.model.Tile;

import java.util.Arrays;

/**
 * Message that contains the current points. Used to show the player the points he has at a certain point of the match.
 */
public class CurrentPointsEvent extends Event{

    private final int points;

    /**
     * Default constructor.
     *
     * @param points That the player has at a certain point of the match.
     */
    public CurrentPointsEvent(String user, int points) {
        super(user, EventType.CURRENT_POINTS);
        this.points = points;
    }

    /**
     *
     * @return the current points.
     */
    public int getPoints() {
        return points;
    }

   @Override
    public String toString(){
        return getUser() + "has" + getPoints() + "points";
}





}
