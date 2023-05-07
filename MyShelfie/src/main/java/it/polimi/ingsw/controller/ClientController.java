package it.polimi.ingsw.controller;

import it.polimi.ingsw.Observer.Observer;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Shelfie;
import it.polimi.ingsw.network.Client.ClientSocket;
import it.polimi.ingsw.network.Events.Event;
import it.polimi.ingsw.network.Events.LoginReplyEvent;
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
        client.sendMessage(new LoginReplyEvent(username));
    }

    /**
     * Sends a message to the server with the player number chosen by the user
     */
    public void sendPlayersNumber(int playersNum) throws IOException {
        client.sendMessage(new UsersNumberReplyEvent(username, playersNum));
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
            case SERVERINFO_REQUEST:
                ServerInforRequest serverInforRequest = (ServerInforRequest) event;
                view.askServerInfo();
                break;
            //case LOBBY:
            //turno di altri

            case LOGIN_REQUEST:
                LoginRequestEvent loginRequestEvent = (LoginRequestEvent) event;
                view.askUsername();
                break;
            case NUMBER_OF_USERS_REQUEST:
                NumberOfUsersRequest numberOfUsersRequest = (NumberOfUsersRequest)  event;
                view.askPlayersNumber();
                break;
            case FIRSTPLAYER_REQUEST:
                FirstPlayerRequest firstPlayerRequest = (FirstPlayerRequest) event;
                view.askFirstPLayer();
                break;
            case TILES_NUMBER_REQUEST:
                TilesNumberRequest tilesNumberRequest = (TilesNumberRequest) event;
                view.askTilesNumber();
                break;
            case CHOOSE_TILE_REQUEST:
                ChooseTileRequest chooseTileRequest = (ChooseTileRequest) event;
                view.askTilePositionOnBoard();
                break;
            case PLACE_TILE_REQUEST:
                PlaceTileRequest placeTileRequest = (PlaceTileRequest) event;
                view.askShelfieColumn();
            case BOARD:
                BoardEvent boardEvent = (BoardEvent) event;
                view.showBoard();
                break;
            case MYSHELFIE:
                MyShelfieEvent myShelfieEvent = (MyShelfieEvent) event;
                view.showMyShelfie();
                break;
            case OTHERSHELFIES:
                OtherhelfiesEvent otherhelfiesEvent = (otherShelfiesEvent) event;
                view.showOtherPlayersShelfie();
                break;
            case SHOWPERSONALGOAL:
                PersonalGoalReply personalGoalReply = (PersonalGoalReply) event;
                view.showMyPersonalGoal();
                break;
            case SHOWCOMMONGOALS:
                CommonGoalReply commonGoalReply = (CommonGoalReply) event;
                view.showCommonGoals();
                break;
            case COMMONGOAL_COMPLETED:
                CommonGoalCompleted commonGoalCompleted = (CommonGoalCompleted) event;
                view.showCommonGoalCompletion();
                break;
            case CURRENT_POINTS:
                ShowCurrentPoints showCurrentPoints = (ShowCurrentPoints) event;
                view.showMyPoints();
                break;


        }

    }
}
