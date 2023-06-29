package it.polimi.ingsw.network.Events;

/**Event to ask the client which action wants to do in the game*/
public class ChoiceBeginTurnRequestEvent extends Event{
    public ChoiceBeginTurnRequestEvent(String user) {
        super(user, EventType.CHOICEBEGINTURN_REQUEST);
    }
}
