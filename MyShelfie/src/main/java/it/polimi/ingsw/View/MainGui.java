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
        view.start(stage);
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

}


