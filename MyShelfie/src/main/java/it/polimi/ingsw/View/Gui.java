package it.polimi.ingsw.View;

import it.polimi.ingsw.controller.Observer.ViewObservable;
import it.polimi.ingsw.model.CommonGoalsName;
import it.polimi.ingsw.model.Tile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Gui extends ViewObservable implements View{

    public void start() {


    }

    @Override
    public void askUsername() {

    }

    @Override
    public void askPlayersNumber() {

    }

    @Override
    public void askFirstPLayer(List<String> usersOnGame) {

    }

    @Override
    public void askTilesNumber() {

    }

    @Override
    public void askTilePositionOnBoard(int tilesNumber) {

    }

    @Override
    public void askShelfieColumn() {

    }

    @Override
    public void askPlacingTile(List<Tile> tilesPicked) {

    }

    @Override
    public void showBoard(Tile[][] structure) {

    }

    @Override
    public void showMyShelfie(Tile[][] structure) {

    }

    @Override
    public void showCommonGoals(CommonGoalsName name) {

    }

    @Override
    public void showMyPoints(int currentPoints) {

    }

    @Override
    public void showCommonGoalCompletion(String username, String commonGoal, int pointsAchieved) {

    }

    @Override
    public void showWinMessage(String winner) {

    }

    @Override
    public void showGenericMessage(String messageToSend) {

    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void showConnectionMessage(String connectedUser) {

    }

    @Override
    public void showDisconnectionMessage(String disconnectedUser) {

    }

    @Override
    public void showTurnState(String user) {

    }

    @Override
    public void showTilesArray(List<Tile> tilesPicked) {

    }

    @Override
    public void askChoiceBeginTurn() {

    }

    @Override
    public void showLobby(List<String> nicknameList, int numPlayers) {

    }

    @Override
    public void showLoginResult(boolean usernameAccepted, boolean connectionSuccessful, String username){

    }
}
