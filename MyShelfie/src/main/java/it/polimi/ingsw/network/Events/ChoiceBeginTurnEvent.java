package it.polimi.ingsw.network.Events;

 /**Events to send the server the number that correspond to an action chosen by the player*/
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
