package it.polimi.ingsw.network.Events;

/**
 * Message that notifies the winner of the game.
 */

public class NotifyWinnerEvent extends Event{

    private final String winnerUsername;

    /**
     * Default constructor.
     *
     * @param user player that won the match.
     */
    public NotifyWinnerEvent(String user, EventType eventType, String winner) {
        super("server", EventType.WINNER_NOTIFY);
        this.winnerUsername = winner;
    }


    public String getWinnerUsername() {
        return winnerUsername;
    }

    public String toString() {
        return getWinnerUsername() + "is the winner of the game";
    }

}
