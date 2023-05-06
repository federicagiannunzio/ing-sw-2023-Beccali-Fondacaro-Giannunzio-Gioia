package it.polimi.ingsw.controller;

import it.polimi.ingsw.Observer.Observer;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Shelfie;
import it.polimi.ingsw.network.Client.ClientSocket;
import it.polimi.ingsw.network.Events.Event;
import it.polimi.ingsw.network.Events.LoginRequestEvent;
import it.polimi.ingsw.network.Events.UsersNumberEvent;

import java.io.IOException;

public class ClientController implements Observer {
    private String username;
    private ClientSocket client;

    private View view;

    /**
     * Sends a message to the server that contains the username that he has picked
     *
     * @param username username that will be sent
     * */
    public void sendUsername(String username) throws IOException {
        this.username = username;
        client.sendMessage(new LoginRequestEvent(username));
    }

    /**
     * Sends a message to the server with the player number chosen by the user
     */
    public void sendPlayersNumber(int playersNum) throws IOException {
        client.sendMessage(new UsersNumberEvent(username, playersNum));
    }

    /**
     * Sends a message to the server that contains the username of the first player.
     *
     * @param usernameFirst the username of the first player
     */
    public void sendFirstPlayerChosen(String usernameFirst) {

    }

    /**
     * Sends a message to the server that contains the number of tiles
     */
    public void sendTilesNumberChosen(int numberOfTiles) {
        client.sendMessage(new tilesNumberRequestEvent());
    }

    /**
     *
     */
    public void sendTilePosition(String use) {

    }

    /**
     * Sends the server the column in which the player wants to put the tiles that he has just picked.
     *
     * @param column the column in which he wants to put the tiles.
     */
    public void sendShelfieColumn(int column) {
        client.sendMessage(new PlaceTileRequest());
    }


    /**
     * Message from the server
     */
    @Override
    public void update(Event event) {

        switch(event.getEventType()) {
            case BOARD:
                BoardEvent boardEvent = (BoardEvent) Event;
                view.showBoard();
                break;
            case MYSHELFIE:
                MyShelfieEvent myShelfieEvent = (MyShelfieEvent) Event;
                view.showMyShelfie();
                break;
            case OTHERSHELFIES:
                OtherhelfiesEvent otherhelfiesEvent = (otherShelfiesEvent) Event;
                view.showMyShelfie();
                break;
            case PERSONALGOAL_REPLY:
                PersonalGoalReply personalGoalReply = (personalGoalReply) Event;
                view.showMyPersonalGoal();
                break;
            case COMMONGOAL_REPLY:
                CommonGoalReply commonGoalReply = (CommonGoalReply) Event;
                view.showCommonGoals();
                break;
            case COMMONGOAL_COMPLETED:
                CommonGoalCompleted commonGoalCompleted = (CommonGoalCompleted) Event;
                view.showCommonGoalCompletion();



        }

    }
}
