package it.polimi.ingsw.network.Events;

public class ChoiceBeginTurnEvent extends Event {
    int choice;

    public ChoiceBeginTurnEvent(String user, int choice) {
        super(user, EventType.CHOICEBEGINTURN_REPLY);
        this.choice = choice;
    }

    public int getChoice(){
        return this.choice;
    }
}
