package it.polimi.ingsw.View;


import it.polimi.ingsw.controller.GuiController.*;
import it.polimi.ingsw.Observer.*;
import it.polimi.ingsw.model.CommonGoal;
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
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * This class implements all methods of View. Is used for the Graphic User Interface.
 */
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

    /**
     * This method shows the first scene that appears
     * to the player when starting the game.
     */
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

    /**
     * This method shows the second scene, where the player
     * enters the server address and the port.
     */
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

    /**
     * This method shows the third scene, where the player enters
     * his username and the numbers of players of the match.
     */
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

    /**
     * This method shows the fourth scene, where the entire game takes place.
     */
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
        Platform.runLater(()->showLoginScreen());
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
        Platform.runLater(()->LSController.askPlayerNumber());
    }

    public void sendPlayerNumber(int playersNumber){
        System.out.println("Sending player number " + playersNumber + "...");
        notifyObserver(obs -> obs.sendPlayersNumber(playersNumber));
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
            Platform.runLater(()->GSController.turnManager());
        });
        //manda di default 2,4 per aggiornare gli obiettivi personali e comuni, 3 per la shelfie aggiornata
        //così va in loop infinito
    }

    public void sendTurnSelection(int i){
        System.out.println("Sending turn request to server: " + i);
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
        });
    }

    @Override
    public void showBoard(Tile[][] structure) {
        System.out.println("Updating the board...");
        Platform.runLater(()->{
            GSController.updateBoard(structure);
        });
    }

    @Override
    public void askTilesNumber() {
        System.out.println("Ask tiles number");
        Platform.runLater(()->GSController.askTilesNumber());
    }

    public void sendTilesNumber(int numberOfTiles){
        System.out.println("Sending tiles number: " + numberOfTiles);
        notifyObserver(obs -> obs.sendTilesNumberChosen(numberOfTiles));
    }

    @Override
    public void askTilePositionOnBoard(int tilesNumber) {
        System.out.println("Ask tiles position on board");
        Platform.runLater(()->GSController.askTilesPositionOnBoard(tilesNumber));
    }

    public void sendTilesPositionOnBoard(int[] row, int[] col){
        System.out.println("Sending tiles position on board: ");
        Arrays.stream(row).forEach((a)-> System.out.println(a));
        Arrays.stream(col).forEach((a)-> System.out.println(a));
        notifyObserver(obs -> obs.sendTilePosition(row, col));
    }

    @Override
    public void askShelfieColumn() {
        System.out.println("Ask shelfie column");
        Platform.runLater(()->GSController.askShelfieColumn());
    }

    public void sendShelfieColumn(int column){
        System.out.println("Sending column number: " + column);
        notifyObserver(obs -> obs.sendShelfieColumn(column));
    }

    @Override
    public void askPlacingTile(List<Tile> tilesPicked) {
        System.out.println("Ask placingTile column");
        Platform.runLater(() -> GSController.askTilesOrder(tilesPicked));
    }

    public void sendPlacingTile(int index){
        notifyObserver(obs->obs.sendArrayTileIndex(index));
    }

    @Override
    public void showMyShelfie(Tile[][] structure) {
        Platform.runLater(()->GSController.updatePlayerShelfie(structure));
    }

    @Override
    public void showCommonGoals(CommonGoalsName name) {
        System.out.println("Showing common goals...");
        Platform.runLater(()->GSController.updateCommonGoals(name));
    }

    @Override
    public void showCommonGoalCompletion(String username, String commonGoal, int pointsAchieved) {
        GSController.showAlert(username + " has completed " + commonGoal + " common goal.", username + " has achieved " + pointsAchieved + " points.");
    }

    @Override
    public void showMyPoints(int currentPoints) {
        System.out.println("Show points("+currentPoints+")");
        Platform.runLater(()->GSController.showPoints(currentPoints, true));
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
        if(messageToSend.equals("The game is starting!")){
            Platform.runLater(() -> showGameScreen());
        }
        Platform.runLater(() -> GSController.showAlert("", messageToSend));
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void showTilesArray(List<Tile> tilesPicked) {

    }

}


