package it.polimi.ingsw.View;


import it.polimi.ingsw.controller.GuiController.*;
import it.polimi.ingsw.Observer.*;
import it.polimi.ingsw.model.CommonGoalsName;
import it.polimi.ingsw.model.Tile;
import javafx.application.Platform;
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
    GameSceneController GSController;

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
            System.out.println("exception " + e + " " + e.getMessage() + " " + e.getCause());
        }
    }
    public void showGameScreen(){
        try {
            FXMLLoader GameScreenFXML = new FXMLLoader(getClass().getResource("/GameScene.fxml"));
            Scene GameScreenScene = new Scene(GameScreenFXML.load());
            GameSceneController GameSceneController = GameScreenFXML.getController();
            GSController = GameSceneController;
            GameSceneController.setGUI(this);

            stage.setScene(GameScreenScene);
            stage.show();
        }catch (IOException e){
            System.out.println("exception " + e + " " + e.getCause());
        }
    }


    //CONNECTION
    public void sendServerInfo(String addr, int port){
        notifyObserver(obs -> obs.sendServerInfo(addr, port));
    }

    //LOGIN
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

    //LOBBY

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

    //GAME

    @Override
    public void askChoiceBeginTurn() {
        if(GSController == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Platform.runLater(() -> {
            GSController.turnManager();
        });
        //manda di default 2,4 per aggiornare gli obiettivi personali e comuni, 3 per la shelfie aggiornata
        //così va in loop infinito
    }

    public void sendTurnSelection(int i){
        notifyObserver(obs->obs.sendChoiceBeginTurn(i));
    }

    @Override
    public void showTurnState(String user) {
        if(GSController == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Platform.runLater(() -> {
            GSController.showAlert("", user + "started his turn");
            GSController.showTurnButton(false);
        });
    }

    @Override
    public void showBoard(Tile[][] structure) {
        Platform.runLater(()->{
            GSController.updateBoard(structure);
        });
    }

    @Override
    public void askTilesNumber() {
        //GSController.setTilesPicker();
    }

    @Override
    public void askTilePositionOnBoard(int tilesNumber) {

    }

    @Override
    public void askShelfieColumn() {
        GSController.setShelfieColumnPicker();
    }

    public void sendShelfieColumn(int column){
        notifyObserver(obs -> obs.sendShelfieColumn(column));
    }

    @Override
    public void askPlacingTile(List<Tile> tilesPicked) {

    }

    @Override
    public void showMyShelfie(Tile[][] structure) {
        GSController.updatePlayerShelfie(structure);
    }

    @Override
    public void showCommonGoals(CommonGoalsName name) {
        GSController.updateCommonGoals();
    }

    @Override
    public void showMyPoints(int currentPoints) {
        GSController.updatePoints();
    }

    @Override
    public void showCommonGoalCompletion(String username, String commonGoal, int pointsAchieved) {
        GSController.showAlert(username + " has completed " + commonGoal + " common goal.", username + " has achieved " + pointsAchieved + " points.");
    }

    @Override
    public void showWinMessage(String winner) {
        GSController.showWinner(winner);
    }

    @Override
    public void privateChat(List<String> playersName, ArrayList<ArrayList<String>> bufferMessages, String sender) {

    }

    @Override
    public void groupChat(List<String> bufferMessages) {

    }

    public void sendGroupMessage(List<String> finalMessages){
        notifyObserver(obs->obs.sendChatMessage(null, finalMessages));
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
    public void showTilesArray(List<Tile> tilesPicked) {

    }

}


