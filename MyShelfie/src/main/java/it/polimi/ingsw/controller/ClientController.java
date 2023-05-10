package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.Observer.Observer;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.network.Client.Client;

import it.polimi.ingsw.network.Events.*;

import java.io.IOException;
import java.util.List;

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
    public void sendFirstPlayerChosen(String usernameFirst, List<String> usersOnGame){
        client.sendMessage( new FirstPlayerRequestEvent(this.username, usersOnGame));
    }

    /**
     * Sends a message to the server that contains the number of tiles
     */
    public void sendTilesNumberChosen(int numberOfTiles) {
        client.sendMessage(new TilesNumberRequestEvent(this.username, numberOfTiles));
    }


    /**
     * Sends the server the position of the tile that the player wants to pick.
     */
    public void sendTilePosition(int row, int column) {
        client.sendMessage(new ChooseTileReplyEvent(this.username, row, column);
    }

    /**
     * Sends the server the column in which the player wants to put the tiles that he has just picked.
     *
     * @param column the column in which he wants to put the tiles.
     */
    public void sendShelfieColumn(int column) {
        client.sendMessage(new PlaceTileRequestEvent(this.username, column));
    }


    /**
     * Message from the server
     */
    @Override
    public void update(Event event) {

        switch(event.getEventType()) {
            case SERVER_INFO:
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
                view.askFirstPLayer(firstPlayerRequest.getUsersOnGame());
                break;
            case TILES_NUMBER_REQUEST:
                TilesNumberRequestEvent tilesNumberRequest = (TilesNumberRequestEvent) event;
                view.askTilesNumber();
                break;
            case CHOOSE_TILE_REQUEST:
                ChooseTileRequestEvent chooseTileRequest = (ChooseTileRequestEvent) event;
                view.askTilePositionOnBoard(chooseTileRequest.getTilesNumber());
                break;
            case PLACE_TILE_REQUEST:
                PlaceTileRequestEvent placeTileRequest = (PlaceTileRequestEvent) event;
                view.askShelfieColumn();
            case BOARD:
                BoardEvent boardEvent = (BoardEvent) event;
                view.showBoard(boardEvent.getBoard());
                break;
            case MYSHELFIE:
                MyShelfieEvent myShelfieEvent = (MyShelfieEvent) event;
                view.showMyShelfie(myShelfieEvent.getBoard());
                break;
            case OTHERSHELFIES:
                OtherShelfiesEvent otherShelfiesEvent = (OtherShelfiesEvent) event;
                view.showMyShelfie(otherShelfiesEvent.getBoard());
                break;
            case SHOWPERSONALGOAL:
                ShowPersonalGoalEvent personalGoalReply = (ShowPersonalGoalEvent) event;
                view.showMyShelfie(personalGoalReply.getBoard());
                break;
            case SHOWCOMMONGOALS:
                ShowCommonGoalsEvent commonGoalReply = (ShowCommonGoalsEvent) event;
                view.showCommonGoals(commonGoalReply.getName());
                break;
            case COMMONGOAL_COMPLETED:
                CommonGoalCompletedEvent commonGoalCompleted = (CommonGoalCompletedEvent) event;
                view.showCommonGoalCompletion(commonGoalCompleted.getUser(),commonGoalCompleted.getCommonGoal(), commonGoalCompleted.getPointsAchieved());
                break;
            case CURRENT_POINTS:
                FinalPointsEvent showCurrentPoints = (FinalPointsEvent) event;
                view.showMyPoints(showCurrentPoints.getFpoints());
                break;
            case TURN_STATE:
                TurnStateEvent showTurnState = (TurnStateEvent) event;
                view.showTurnState(showTurnState.getUser());

        }
    }
}
