package it.polimi.ingsw.network.Events;

/**
 * Message that notifies the winner of the game.
 */

public class NotifyWinnerEvent extends Event{

    /**
     * Default constructor.
     *
     * @param user player that won the match.
     */
    public NotifyWinnerEvent(String user, EventType eventType) {
        super(user, EventType.WINNER_NOTIFY);
    }

    public String toString() {
        return getUser() + "is the winner of the game";
    }

}
