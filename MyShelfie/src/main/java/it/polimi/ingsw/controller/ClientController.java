package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.Observer.Observer;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.network.Client.Client;

import it.polimi.ingsw.network.Events.*;

import java.io.IOException;

public class ClientController implements Observer {
    private String username;
    private Client client;

    private View view;

    /**
     * Sends a message to the server that contains the username that he has picked
     *
     * @param username username that will be sent
     * */
    public void sendUsername(String username) throws IOException {
        this.username = username;
        client.sendMessage(new LoginRequestEvent(this.username));
    }

    /**
     * Sends a message to the server with the player number chosen by the user
     */
    public void sendPlayersNumber(int playersNum) throws IOException {
        client.sendMessage(new UsersNumberEvent(this.username, playersNum));
    }

    /**
     * Sends a message to the server that contains the username of the first player.
     *
     * @param usernameFirst the username of the first player
     */
    public void sendFirstPlayerChosen(String usernameFirst) throws IOException {
        client.sendMessage( new FirstPlayerRequestEvent(this.username, EventType.FIRSTPLAYER_REQUEST));
    }

    /**
     * Sends a message to the server that contains the number of tiles
     */
    public void sendTilesNumberChosen(int numberOfTiles) {
        client.sendMessage(new TilesNumberRequestEvent());
    }


    /**
     * Sends the server the position of the tile that the player wants to pick.
     */
    public void sendTilePosition(String use) {

    }

    /**
     * Sends the server the column chosen by the player in which he wants to insert the tile.
     */
    public void sendShelfieColumn() {

    }


    /**
     * Sends the server the column in which the player wants to put the tiles that he has just picked.
     *
     * @param column the column in which he wants to put the tiles.
     */
    public void sendShelfieColumn(int column) {
        client.sendMessage(new PlaceTileRequestEvent());
    }


    /**
     * Message from the server
     */
    @Override
    public void update(Event event) {

        switch(event.getEventType()) {
            case SERVERINFO_REQUEST:
                InfoServerEvent serverInforRequest = (InfoServerEvent) event;
                view.askServerInfo();
                break;
            //case LOBBY:
            //turno di altri

            case LOGIN_REQUEST:
                LoginRequestEvent loginRequestEvent = (LoginRequestEvent) event;
                view.askUsername();
                break;
            case NUMBER_OF_USERS_REQUEST:
                UsersNumberEvent numberOfUsersRequest = (UsersNumberEvent)  event;
                view.askPlayersNumber();
                break;
            case FIRSTPLAYER_REQUEST:
                FirstPlayerRequestEvent firstPlayerRequest = (FirstPlayerRequestEvent) event;
                view.askFirstPLayer();
                break;
            case TILES_NUMBER_REQUEST:
                TilesNumberRequestEvent tilesNumberRequest = (TilesNumberRequestEvent) event;
                view.askTilesNumber();
                break;
            case CHOOSE_TILE_REQUEST:
                ChooseTileRequestEvent chooseTileRequest = (ChooseTileRequestEvent) event;
                view.askTilePositionOnBoard();
                break;
            case PLACE_TILE_REQUEST:
                PlaceTileRequestEvent placeTileRequest = (PlaceTileRequestEvent) event;
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
                OtherShelfiesEvent otherhelfiesEvent = (OtherShelfiesEvent) event;
                view.showOtherPlayersShelfie();
                break;
            case SHOWPERSONALGOAL:
                ShowPersonalGoalEvent personalGoalReply = (ShowPersonalGoalEvent) event;
                view.showMyPersonalGoal();
                break;
            case SHOWCOMMONGOALS:
                ShowCommonGoalsEvent commonGoalReply = (ShowCommonGoalsEvent) event;
                view.showCommonGoals();
                break;
            case COMMONGOAL_COMPLETED:
                CommonGoalCompletedEvent commonGoalCompleted = (CommonGoalCompletedEvent) event;
                view.showCommonGoalCompletion();
                break;
            case CURRENT_POINTS:
                FinalPointsEvent showCurrentPoints = (FinalPointsEvent) event;
                view.showMyPoints();
                break;
            case TURN_STATE:
                TurnStateEvent showTurnState = (TurnStateEvent) event;
                //metodo della view

        }
    }
}
