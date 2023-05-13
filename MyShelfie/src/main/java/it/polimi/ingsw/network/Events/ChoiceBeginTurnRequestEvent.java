package it.polimi.ingsw.network.Events;

public class ChoiceBeginTurnRequestEvent extends Event{
    public ChoiceBeginTurnRequestEvent(String user) {
        super(user, EventType.CHOICEBEGINTURN_REQUEST);
    }
}
