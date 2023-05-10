package it.polimi.ingsw.network.Events;

public class ChoiceBeginTurnEvent extends Event {
    int choice;

    public ChoiceBeginTurnEvent(String user, int choice) {
        super(user, EventType.CHOICEBEGINTURN_REQUEST);
    }
}
