package it.polimi.ingsw.View;

import it.polimi.ingsw.controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;



public class MainGui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Gui view = new Gui();
        ClientController clientController = new ClientController(view);
        view.addObserver(clientController);

        Parent root = FXMLLoader.load(getClass().getResource("/welcomeScreen.fxml"));
        stage.setTitle("MyShelfie board game");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setMinWidth(600);
        stage.setMinHeight(400);


    }

    public static void main(String[] args) throws IOException {
        launch(args);

    }

}

