package it.polimi.ingsw.controller.GuiController;


import it.polimi.ingsw.View.Gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;

public class LoginController {

    private Gui gui;

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField numberTF;
    @FXML
    private Button loginBT;
    @FXML
    private Label label;

    public void setGUI(Gui gui){
        this.gui = gui;
    }

    public void submit(ActionEvent event){
        String username = usernameTF.getText();
        if (username.length() == 0){
            showAlert("Insert at least one character!");
        }else{
            System.out.println(username);
            gui.sendUserName(username);
            gui.sendPlayerNumber(Integer.parseInt(numberTF.getText()));
            //gui.showGameScreen();
        }
    }

    public void showAlert(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MyShelfie");
        alert.setHeaderText("Message");
        alert.setContentText(info);
        alert.showAndWait();
    }

    public int getPlayerNumber(){
        try {
            return Integer.parseInt(numberTF.getText().trim());
        }catch (Error e){
            return 0;
        }
    }
}



