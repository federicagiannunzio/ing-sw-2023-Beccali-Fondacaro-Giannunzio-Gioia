package it.polimi.ingsw.controller;

import it.polimi.ingsw.Observer.Observer;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.Observer.ViewObserver;
import it.polimi.ingsw.network.Client.Client;
import it.polimi.ingsw.network.Client.ClientSocket;
import it.polimi.ingsw.network.Events.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is part of the client side
 * It is an interpreter between the network and a generic view
 * It receives the messages, wraps/unwraps and pass them to the network/view
 */
public class ClientController implements ViewObserver, Observer {
    private String username;
    private Client client;
    private final ExecutorService taskQueue;

    private View view;

    /**
     * Default constructor
     *
     * @param view the view to be controlled.
     */
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
     * @param address the IP address
     * @param port the port
     */
    public void sendServerInfo(String address, int port) {
        try {
            client = new ClientSocket(address, port);
            client.addObserver(this);
            client.receiveMessage();
            taskQueue.execute(view::askUsername);
        } catch (IOException e) {
            System.out.println("Can't connect to the server");
            System.exit(0);
        }
    }

    /**
     * Sends a message to the server with the number of players chosen by the user
     *
     * @param playersNum is the player's number
     */
    public void sendPlayersNumber(int playersNum) {
        client.sendMessage(new NumberOfUsersReplyEvent(this.username, playersNum));
    }

    /**
     * Sends a message to the server that contains the number of tiles that the player wants to pick
     *
     * @param numberOfTiles is the number of tiles chosen
     */
    public void sendTilesNumberChosen(int numberOfTiles) {
        client.sendMessage(new TilesNumberReplyEvent(this.username, numberOfTiles));
    }


    /**
     * Sends the server the position of the tiles that the player has picked
     *
     * @param row contains, for each tile picked, the row number on the board
     * @param col contains, for each tile picked, the column number on the board
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
     * Sends the server the choice that the player did at the beginning of his turn: he can choose between 7 actions.
     *
     * @param choice is the number that correspond to the choice
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
     * Sends the server the messages that a player wants to send to the others player
     *
     * @param recipient the recipient (if present) of the messages
     * @param messages list of all the messages that will be sent
     * */
    public void sendChatMessage(String recipient, List<String> messages) {
        client.sendMessage(new ChatMessageEvent(this.username, recipient, messages));
    }


    /**
     * Message from the server
     *
     * @param event is the specific message that the server will send
     */
    @Override
    public void update(Event event) {

        switch(event.getEventType()) {
            case LOGIN_REQUEST:
                LoginRequestEvent loginRequestEvent = (LoginRequestEvent) event;
                taskQueue.execute(()->view.askUsername());
                break;
            case NUMBER_OF_USERS_REQUEST:
                NumberOfUsersRequestEvent numberOfUsersRequest = (NumberOfUsersRequestEvent)  event;
                taskQueue.execute(()->view.askPlayersNumber());
                break;
            case TILES_NUMBER_REQUEST:
                TilesNumberRequestEvent tilesNumberRequest = (TilesNumberRequestEvent) event;
                taskQueue.execute(()->view.askTilesNumber());
                break;
            case CHOOSE_TILE_REQUEST:
                ChooseTileRequestEvent chooseTileRequest = (ChooseTileRequestEvent) event;
                taskQueue.execute(()->view.askTilePositionOnBoard(chooseTileRequest.getTilesNumber()));
                break;
            case PLACE_TILE_REQUEST:
                PlaceTileRequestEvent placeTileRequest = (PlaceTileRequestEvent) event;
                taskQueue.execute(()->view.askShelfieColumn());
                break;
            case BOARD:
                BoardEvent boardEvent = (BoardEvent) event;
                taskQueue.execute(()->view.showBoard(boardEvent.getBoard()));
                break;
            case SHOWSHELFIE:
                ShowShelfiesEvent myShelfieEvent = (ShowShelfiesEvent) event;
                taskQueue.execute(()->view.showMyShelfie(myShelfieEvent.getBoard()));
                break;
            case SHOWCOMMONGOALS:
                ShowCommonGoalsEvent commonGoalReply = (ShowCommonGoalsEvent) event;
                taskQueue.execute(()->view.showCommonGoals(commonGoalReply.getName()));
                break;
            case COMMONGOAL_COMPLETED:
                CommonGoalCompletedEvent commonGoalCompleted = (CommonGoalCompletedEvent) event;
                taskQueue.execute(()->view.showCommonGoalCompletion(commonGoalCompleted.getUser(),commonGoalCompleted.getCommonGoal(), commonGoalCompleted.getPointsAchieved()));
                break;
            case CURRENT_POINTS:
                CurrentPointsEvent showCurrentPoints = (CurrentPointsEvent) event;
                taskQueue.execute(()->view.showMyPoints(showCurrentPoints.getPoints()));
                break;
            case TURN_STATE:
                TurnStateEvent showTurnState = (TurnStateEvent) event;
                taskQueue.execute(()->view.showTurnState(showTurnState.getUser()));
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
                taskQueue.execute(()->view.showConnectionMessage(connectionMessage.getConnectedUser()));
                break;
            case USERDISCONNECTED:
                DisconnectionMessageEvent disconnectionMex = (DisconnectionMessageEvent) event;
                taskQueue.execute(()->view.showDisconnectionMessage(disconnectionMex.getNicknameDisconnected()));
                break;
            case ERRORMESSAGE:
                ErrorEvent errorEvent = (ErrorEvent) event;
                taskQueue.execute(()->view.showErrorMessage(errorEvent.getError()));
                break;
            case GENERICMESSAGE:
                GenericMessageEvent genericMessage = (GenericMessageEvent) event;
                taskQueue.execute(()->view.showGenericMessage(genericMessage.getMessage()));
                break;
            case LOBBY:
                LobbyEvent lobby = (LobbyEvent) event;
                taskQueue.execute(()->view.showLobby(lobby.getUsersList(), lobby.getMaxPlayers()));
                break;
            case WINNER_NOTIFY:
                NotifyWinnerEvent winMessage = (NotifyWinnerEvent) event;
                taskQueue.execute(()->view.showWinMessage(winMessage.getWinnerUsername()));
                break;
            case LOGIN_REPLY:
                LoginReplyEvent loginReplyEvent = (LoginReplyEvent) event;
                taskQueue.execute(()->view.showLoginResult(loginReplyEvent.isUsernameAccepted(), loginReplyEvent.isConnectionSuccessful(), loginReplyEvent.getUser()));
                break;
            case CHATINIT:
                ChatRequestEvent chat = (ChatRequestEvent) event;
                taskQueue.execute(()->view.groupChat(chat.getBuffer()));
                break;
            case PRIVATECHATINIT:
                PrivateChatRequestEvent privateChat = (PrivateChatRequestEvent) event;
                taskQueue.execute(()->view.privateChat(privateChat.getUsers(), privateChat.getBuffer(), privateChat.getSender()));
                break;



        }
    }
}
