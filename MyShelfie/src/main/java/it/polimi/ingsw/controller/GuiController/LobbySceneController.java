package it.polimi.ingsw.controller.GuiController;

import it.polimi.ingsw.View.Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LobbySceneController {

    private Gui gui;

    private int playerCounter;
    private int maxPlayer;
    private String usernameList;

    @FXML
    private Label matchID;
    @FXML
    private Label playersListID;
    @FXML
    private Label playersNumberID;


    public void setGUI(Gui gui){
        this.gui = gui;
    }

    public void setMax(int max){
        maxPlayer = max;
    }

    public void playerJoin(String username){
        playerCounter++;
        usernameList += ", " + username;

        playersListID.setText("players: " + usernameList);
        playersNumberID.setText(playerCounter + "/" +maxPlayer);
    }

    public void playerQuit(String username){

        playerCounter--;
        usernameList.replace(", " + username, "");

        playersListID.setText("players: " + usernameList);
        playersNumberID.setText(playerCounter + "/" +maxPlayer);

    }

    public void goToGame(ActionEvent event){
        gui.showGameScreen();
    }

}


