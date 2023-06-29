package it.polimi.ingsw.View;


import it.polimi.ingsw.controller.GuiController.ConnectionController;
import it.polimi.ingsw.controller.GuiController.LobbySceneController;
import it.polimi.ingsw.controller.GuiController.LoginController;
import it.polimi.ingsw.controller.GuiController.WelcomeScreenController;
import it.polimi.ingsw.controller.Observer.ViewObservable;
import it.polimi.ingsw.controller.Observer.ViewObserver;
import it.polimi.ingsw.model.CommonGoalsName;
import it.polimi.ingsw.model.Tile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Gui extends ViewObservable implements View {

    Stage stage;

    WelcomeScreenController WSController;
    ConnectionController CSController;
    LoginController LSController;
    LobbySceneController LoSController;

    public void start(Stage stage) throws IOException {

        this.stage = stage;

        stage.setTitle("MyShelfie");
        //stage.setFullScreen(true);
        //stage.setFullScreenExitHint("");
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        showWelcomeScreen();

    }

    public void showWelcomeScreen(){
        try {
            FXMLLoader welcomeScreenFXML = new FXMLLoader(getClass().getResource("/welcomeScreen.fxml"));
            Scene welcomeScreenScene = new Scene(welcomeScreenFXML.load());
            WelcomeScreenController welcomeScreenController = welcomeScreenFXML.getController();
            WSController = welcomeScreenController;
            welcomeScreenController.setGUI(this);

            stage.setScene(welcomeScreenScene);
            stage.show();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void showConnectionScreen(){
        try {
            FXMLLoader connectionScreenFXML = new FXMLLoader(getClass().getResource("/Connection.fxml"));
            Scene connectionScreenScene = new Scene(connectionScreenFXML.load());
            ConnectionController connectionController = connectionScreenFXML.getController();
            CSController = connectionController;
            connectionController.setGUI(this);

            stage.setScene(connectionScreenScene);
            stage.show();
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void showLoginScreen(){
        try {
            FXMLLoader loginScreenFXML = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Scene loginScreenScene = new Scene(loginScreenFXML.load());
            LoginController loginScreenController = loginScreenFXML.getController();
            LSController = loginScreenController;
            loginScreenController.setGUI(this);

            stage.setScene(loginScreenScene);
            stage.show();
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void showLobbyScreen(){
        try {
            FXMLLoader lobbyScreenFXML = new FXMLLoader(getClass().getResource("/LobbyScene.fxml"));
            Scene lobbyScreenScene = new Scene(lobbyScreenFXML.load());
            LobbySceneController lobbySceneController = lobbyScreenFXML.getController();
            LoSController = lobbySceneController;
            lobbySceneController.setGUI(this);

            stage.setScene(lobbyScreenScene);
            stage.show();
        }catch (IOException e){
            System.out.println(e);
        }
    }


    public void sendServerInfo(String addr, int port){
        notifyObserver(obs -> obs.sendServerInfo(addr, port));
    }

    @Override
    public void askUsername(){
        System.out.println("asking user name");
    }

    public void sendUserName(String username){
        notifyObserver(obs -> obs.sendUsername(username));
    }

    @Override
    public void showLoginResult(boolean usernameAccepted, boolean connectionSuccessful, String username){

        if (usernameAccepted && connectionSuccessful) {
            System.out.println("Hi, " + username + "! You connected to the server.");
        } else if (connectionSuccessful) {
            askUsername();
        } else if (usernameAccepted) {
            System.out.println("Max players reached. Connection refused.");
            System.out.println("EXIT.");
            System.exit(1);
        } else {
            showErrorMessage("Could not contact server.");
            System.exit(1);
        }

        //usa questo per andare nella lobby dal login screen

    }

    @Override
    public void privateChat(List<String> playersName, ArrayList<ArrayList<String>> bufferMessages, String sender) {

    }

    @Override
    public void groupChat(List<String> bufferMessages) {

    }

    @Override
    public void askPlayersNumber() {
        System.out.println("asking player number");
        sendPlayerNumber(LSController.getPlayerNumber());
    }

    public void sendPlayerNumber(int playersNumber){
        notifyObserver(obs -> obs.sendPlayersNumber(playersNumber));
    }

    @Override
    public void askFirstPLayer(List<String> usersOnGame) {
        System.out.println("showLobby");
    }

    @Override
    public void showLobby(List<String> nicknameList, int numPlayers) {
        System.out.println("showLobby");
    }

    @Override
    public void showConnectionMessage(String connectedUser) {
        LoSController.playerJoin(connectedUser);
    }

    @Override
    public void showDisconnectionMessage(String disconnectedUser) {
        LoSController.playerQuit(disconnectedUser);
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
        //usato anche per segnalare l'inizio del gioco (init turn nel turn controller)
        //usato anche per segnalare l'inizio del turno di un giocatore
    }

    @Override
    public void showErrorMessage(String errorMessage) {

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

}


