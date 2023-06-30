package it.polimi.ingsw.controller.GuiController;


import it.polimi.ingsw.View.Gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;

import java.util.Objects;

/**
 * This class implements the scene where te player insert his credentials.
 */
public class LoginController {

    private Gui gui;

    @FXML
    private Label label;
    @FXML
    private TextField TF;
    @FXML
    private Button loginBT;

    public void setGUI(Gui gui){
        this.gui = gui;
    }

    /**
     * This method is set to handle the username entered by the player.
     */
    private void sendUsername(){
        String username = TF.getText();
        if (username.length() == 0){
            showAlert("Enter at least one character!");
        }else{
            System.out.println(username);
            gui.sendUserName(username);
        }
    }

    /**
     * This method is set to handle the number of player of the
     * match inserted by the player.
     */
    private void sendPlayerNumber(){
        try {
            int number = Integer.parseInt(TF.getText());
            if(number >= 2 && number <= 4){
                gui.sendPlayerNumber(number);
                loginBT.setVisible(false);
            }else{
                label.setText("Enter a valid number (2-4)");
            }
        }catch (Exception e){
            label.setText("Enter a valid number (2-4)");
        }
    }

    /**
     * This method handles the button that allows the player to enter the game.
     *
     * @param event button clicked.
     */
    public void submit(ActionEvent event){
        if(Objects.equals(TF.getPromptText(), "Username")){
            sendUsername();
        }else if(Objects.equals(TF.getPromptText(), "Number of players")){
            sendPlayerNumber();
        }
    }

    /**
     * This method ask the player the numeber of players he wants.
     */
    public void askPlayerNumber(){
        loginBT.setVisible(true);
        TF.setPromptText("Number of players");
        TF.setText("");
        label.setText("Enter number of players (2-4)");
        loginBT.setText("Send");
    }

    /**
     * GIORGIO
     * @param info
     */
    public void showAlert(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MyShelfie");
        alert.setHeaderText("Message");
        alert.setContentText(info);
        alert.showAndWait();
    }

}



