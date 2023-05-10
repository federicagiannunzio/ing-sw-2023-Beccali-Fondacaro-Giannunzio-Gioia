package it.polimi.ingsw.network.Events;

public class UsersNumberEvent extends Event{
    private final int playerNumber;
    public UsersNumberEvent( String user, int playerNumber) {
        super(user, EventType.NUMBER_OF_USERS_REQUEST);
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public String toString() {
        return getUser() + " selected a number of " + playerNumber + " players.";
    }


}
