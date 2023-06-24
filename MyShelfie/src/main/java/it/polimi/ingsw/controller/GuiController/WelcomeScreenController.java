package it.polimi.ingsw.controller.GuiController;
import it.polimi.ingsw.View.Gui;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.controller.Observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    private Gui gui;


    public void setGUI(Gui gui){
        this.gui = gui;
    }

    public void goToConnection(ActionEvent event) throws IOException {
        gui.showConnectionScreen();
    }

    public void exit(ActionEvent event) throws IOException {
       System.exit(0);
    }
}





