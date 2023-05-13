package it.polimi.ingsw.network.Events;

public class NumberOfUsersRequestEvent extends Event{

    public NumberOfUsersRequestEvent(String user) {
        super(user, EventType.NUMBER_OF_USERS_REQUEST);
    }

    public String toString(){
        return "Request the number of players to the user " + getUser();
    }


}
