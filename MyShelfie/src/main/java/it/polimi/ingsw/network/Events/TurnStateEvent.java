package it.polimi.ingsw.network.Events;

/**Event to notify the players who is playing now*/
public class TurnStateEvent extends Event{
    public TurnStateEvent(String user) {
        super(user, EventType.TURN_STATE);
    }


}
