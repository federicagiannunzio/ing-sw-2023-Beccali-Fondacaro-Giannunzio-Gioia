package it.polimi.ingsw.View;

import it.polimi.ingsw.controller.Observer.Observer;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.CommonGoalsName;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.Events.*;
import it.polimi.ingsw.network.Server.ClientHandler;

import java.io.IOException;
import java.util.List;

public class VirtualView implements View, Observer {
    private final ClientHandler clientHandler;
    public static final String SERVER_NAME = "server";

    public VirtualView(ClientHandler clientHandler){
        this.clientHandler = clientHandler;
    }
    @Override
    public void askUsername(){
        clientHandler.sendMessage(new LoginReplyEvent(SERVER_NAME, false, true));
    }

    @Override
    public void askPlayersNumber() {
        clientHandler.sendMessage(new NumberOfUsersRequestEvent(SERVER_NAME));
    }

    @Override
    public void askFirstPLayer(List<String> usersOnGame) {
        clientHandler.sendMessage(new FirstPlayerRequestEvent(SERVER_NAME, usersOnGame));
    }

    @Override
    public void askTilesNumber() {
        clientHandler.sendMessage(new TilesNumberRequestEvent(SERVER_NAME));
    }

    @Override
    public void askTilePositionOnBoard(int tilesNumber) {
        clientHandler.sendMessage(new ChooseTileRequestEvent(SERVER_NAME, tilesNumber));
    }

    @Override
    public void askShelfieColumn() {
        clientHandler.sendMessage(new PlaceTileRequestEvent(SERVER_NAME));
    }

    @Override
    public void askPlacingTile(List<Tile> tilesPicked) {
        clientHandler.sendMessage(new TileArrayRequestEvent(SERVER_NAME, tilesPicked));
    }

    @Override
    public void showBoard(Tile[][] structure) {
        clientHandler.sendMessage(new BoardEvent(SERVER_NAME, structure));

    }

    @Override
    public void showMyShelfie(Tile[][] structure) {
        clientHandler.sendMessage(new ShowShelfiesEvent(SERVER_NAME, structure));
    }

    @Override
    public void showCommonGoals(CommonGoalsName name) {
        clientHandler.sendMessage(new ShowCommonGoalsEvent(SERVER_NAME, name));
    }

    @Override
    public void showMyPoints(int currentPoints) {
        clientHandler.sendMessage(new CurrentPointsEvent(SERVER_NAME, currentPoints));

    }

    @Override
    public void showCommonGoalCompletion(String username, String commonGoal, int pointsAchieved) {
        clientHandler.sendMessage(new CommonGoalCompletedEvent(SERVER_NAME, pointsAchieved, commonGoal));

    }

    @Override
    public void showWinMessage(String winner) {
        clientHandler.sendMessage(new NotifyWinnerEvent(SERVER_NAME, winner));
    }

    @Override
    public void showGenericMessage(String messageToSend) {
        clientHandler.sendMessage(new GenericMessageEvent(messageToSend));
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        clientHandler.sendMessage(new ErrorEvent(SERVER_NAME, errorMessage));
    }

    @Override
    public void showConnectionMessage(String connectedUser) {
        clientHandler.sendMessage(new ConnectionMessageEvent(SERVER_NAME, connectedUser));
    }

    @Override
    public void showDisconnectionMessage(String disconnectedUser) {
        clientHandler.sendMessage(new DisconnectionMessageEvent(SERVER_NAME, disconnectedUser));
    }

    @Override
    public void showTurnState(String user) {
        clientHandler.sendMessage(new TurnStateEvent(SERVER_NAME));

    }

    @Override
    public void showTilesArray(List<Tile> tilesPicked) {
        clientHandler.sendMessage(new TileArrayRequestEvent(SERVER_NAME, tilesPicked));
    }

    @Override
    public void askChoiceBeginTurn() {
        clientHandler.sendMessage(new ChoiceBeginTurnRequestEvent(SERVER_NAME));
    }

    @Override
    public void showLobby(List<String> nicknameList, int numPlayers) {
        clientHandler.sendMessage(new LobbyEvent(SERVER_NAME, nicknameList, numPlayers));
    }

    @Override
    public void showLoginResult(boolean usernameAccepted, boolean connectionSuccessful, String username){
        clientHandler.sendMessage(new LoginReplyEvent(username, usernameAccepted, connectionSuccessful));
    }

    @Override
    public void update(Event event) {
        clientHandler.sendMessage(event);
    }
}
