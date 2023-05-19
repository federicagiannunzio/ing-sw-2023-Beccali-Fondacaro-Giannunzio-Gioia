package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.Observer.Observer;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.controller.Observer.ViewObserver;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.Client.Client;

import it.polimi.ingsw.network.Client.ClientSocket;
import it.polimi.ingsw.network.Events.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientController implements ViewObserver, Observer {
    private String username;
    private Client client;
    private final ExecutorService taskQueue;

    private View view;

    public ClientController(View view) {
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
    }

    /**
     * Sends a message to the server that contains the username that he has picked
     *
     * @param username username that will be sent
     * */
    public void sendUsername(String username) {
        this.username = username;
        client.sendMessage(new LoginRequestEvent(this.username));
    }

    /**
     * Sends a message to the server that contains the address and the port to create a connection.
     *
     * @param address
     * @param port
     */
    public void sendServerInfo(String address, int port) {
        try {
            client = new ClientSocket(address, port);
            client.addObserver(this);
            client.receiveMessage();
            taskQueue.execute(view::askUsername);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //istanza connessione
    }

    /**
     * Sends a message to the server with the player number chosen by the user
     */
    public void sendPlayersNumber(int playersNum) {
        client.sendMessage(new NumberOfUsersReplyEvent(this.username, playersNum));
    }

    /**
     * Sends a message to the server that contains the username of the first player.
     *
     * @param usernameFirst the username of the first player
     */
    public void sendFirstPlayerChosen(String usernameFirst){
        client.sendMessage( new FirstPlayerReplyEvent(this.username, usernameFirst));
    }

    /**
     * Sends a message to the server that contains the number of tiles
     */
    public void sendTilesNumberChosen(int numberOfTiles) {
        client.sendMessage(new TilesNumberReplyEvent(this.username, numberOfTiles));
    }


    /**
     * Sends the server the position of the tile that the player wants to pick.
     */
    public void sendTilePosition(int[] row, int[]col) {
        client.sendMessage(new ChooseTileReplyEvent(this.username, row, col));
    }

    /**
     * Sends the server the column in which the player wants to put the tiles that he has just picked.
     *
     * @param column the column in which he wants to put the tiles.
     */
    public void sendShelfieColumn(int column) {
        client.sendMessage(new PlaceTileReplyEvent(this.username, column));
    }

    /**
     * Sends the server the choice that the player did at the beginning of his turn: He can choose between 4 action.
     *
     * @param choice is the number that correspond to that choice
     */
    public void sendChoiceBeginTurn(int choice) {
        client.sendMessage(new ChoiceBeginTurnEvent(this.username, choice));
    }


    /**
     * Sends the server the number that correspond to the position of the array that contains the tile that the player wants to put in his shelf.
     *
     * @param index the index of the array
     */
    public void sendArrayTileIndex(int index){client.sendMessage(new TileArrayReplyEvent(this.username, index));}


    /**
     * Message from the server
     */
    @Override
    public void update(Event event) {

        switch(event.getEventType()) {
            case LOGIN_REQUEST:
                LoginRequestEvent loginRequestEvent = (LoginRequestEvent) event;
                view.askUsername();
                break;
            case NUMBER_OF_USERS_REQUEST:
                NumberOfUsersRequestEvent numberOfUsersRequest = (NumberOfUsersRequestEvent)  event;
                taskQueue.execute(()->view.askPlayersNumber());
                break;
            case FIRSTPLAYER_REQUEST:
                FirstPlayerRequestEvent firstPlayerRequest = (FirstPlayerRequestEvent) event;
                view.askFirstPLayer(firstPlayerRequest.getUsersOnGame());
                break;
            case TILES_NUMBER_REQUEST:
                TilesNumberRequestEvent tilesNumberRequest = (TilesNumberRequestEvent) event;
                taskQueue.execute(()->view.askTilesNumber());
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
            case SHOWSHELFIE:
                ShowShelfiesEvent myShelfieEvent = (ShowShelfiesEvent) event;
                view.showMyShelfie(myShelfieEvent.getBoard());
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
                break;
            case TILE_ARRAY_REQUEST:
                TileArrayRequestEvent requestArrayIndex = (TileArrayRequestEvent) event;
                taskQueue.execute(()->view.askPlacingTile(requestArrayIndex.getTilesList()));
                break;
            case CHOICEBEGINTURN_REQUEST:
                taskQueue.execute(()->view.askChoiceBeginTurn());
                break;
            case USERCONNECTED:
                ConnectionMessageEvent connectionMessage = ( ConnectionMessageEvent) event;
                view.showConnectionMessage(connectionMessage.getConnectedUser());
                break;
            case USERDISCONNECTED:
                DisconnectionMessageEvent disconnectionMex = (DisconnectionMessageEvent) event;
                view.showDisconnectionMessage(disconnectionMex.getNicknameDisconnected());
                break;
            case ERRORMESSAGE:
                ErrorEvent errorEvent = (ErrorEvent) event;
                view.showErrorMessage(errorEvent.getError());
                break;
            case GENERICMESSAGE:
                GenericMessageEvent genericMessage = (GenericMessageEvent) event;
                view.showGenericMessage(genericMessage.getMessage());
                break;
            case LOBBY:
                LobbyEvent lobby = (LobbyEvent) event;
                view.showLobby(lobby.getUsersList(), lobby.getMaxPlayers());
                break;
            case WINNER_NOTIFY:
                NotifyWinnerEvent winMessage = (NotifyWinnerEvent) event;
                view.showWinMessage(winMessage.getWinnerUsername());
                break;
            case LOGIN_REPLY:
                LoginReplyEvent loginReplyEvent = (LoginReplyEvent) event;
                taskQueue.execute(()->view.showLoginResult(loginReplyEvent.isUsernameAccepted(), loginReplyEvent.isConnectionSuccessful(), loginReplyEvent.getUser()));
                break;
        }
    }
}
