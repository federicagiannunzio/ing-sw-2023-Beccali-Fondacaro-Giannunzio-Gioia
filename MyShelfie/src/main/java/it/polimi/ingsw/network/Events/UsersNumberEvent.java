package it.polimi.ingsw.network.Events;

public class UsersNumberEvent extends Event{
    private final int playerNumber;
    public UsersNumberEvent(String user, int playerNumber) {
        super(user, EventType.NUMBER_OF_USERS);
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public String toString(){
        return "user " + getUser() + " selected a number of " + playerNumber + " players.";
    }


}
