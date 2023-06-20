package it.polimi.ingsw.controller.GuiController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label myLabel;
    @FXML
    private TextField myTextField;
    @FXML
    private Button myButton;

    String Username;

    public void submit(ActionEvent event){
        try {
            Username = myTextField.getText();
            System.out.println(Username);

            if (Username.length() == 0){
                myLabel.setText("Insert at least one carachter!");
            }
        }
        catch(Exception e){
            myLabel.setText("error");
        }
    }
}
