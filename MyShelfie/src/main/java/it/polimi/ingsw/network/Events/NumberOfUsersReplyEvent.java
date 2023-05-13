package it.polimi.ingsw.network.Events;


public class NumberOfUsersReplyEvent extends Event{
    int numPlayers;
    public NumberOfUsersReplyEvent(String user, int numPlayers) {
        super(user, EventType.NUMBER_OF_USERS_REPLY);
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public String toString() {
        return getUser() + " selected a number of " + numPlayers + " players.";
    }
}
