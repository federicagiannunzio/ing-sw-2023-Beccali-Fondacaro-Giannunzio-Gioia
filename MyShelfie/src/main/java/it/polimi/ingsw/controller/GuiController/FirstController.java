package it.polimi.ingsw.controller.GuiController;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstController {

    private Scene scene;
    private Stage stage;
    private Parent root;


    public void switchToSceneSecond(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Connection.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    public void switchToSceneSecond2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Second.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}


