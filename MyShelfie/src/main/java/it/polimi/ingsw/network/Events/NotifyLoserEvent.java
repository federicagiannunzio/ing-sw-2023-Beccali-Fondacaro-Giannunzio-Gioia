package it.polimi.ingsw.network.Events;

/**
 * Message that notifies the a loser of the match.
 */
public class NotifyLoserEvent extends Event{

    /**
     * Default constructor.
     *
     * @param user player that lost the match.
     */
    public NotifyLoserEvent(String user, EventType eventType) {
        super(user, EventType.LOSER_NOTIFY);
    }

    public String toString() {
        return getUser() + "lost the match";
    }
}
