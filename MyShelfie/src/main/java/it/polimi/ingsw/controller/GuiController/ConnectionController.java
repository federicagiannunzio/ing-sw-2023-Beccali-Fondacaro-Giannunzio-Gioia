package it.polimi.ingsw.controller.GuiController;


import it.polimi.ingsw.View.Gui;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.Observer.ViewObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnectionController{

    private Scene scene;
    private Stage stage;
    private Parent root;

    private Gui gui;

    @FXML
    private TextField addrTF;

    @FXML
    private TextField portTF;


    public void setGUI(Gui gui){
        this.gui = gui;
    }

    public void connectToServer(ActionEvent event) throws IOException {

        final int port;
        int tempPort;
        try {
            tempPort = Integer.parseInt(portTF.getText().trim());
        }
        catch (NumberFormatException e) {
            tempPort = 0;
        }
        port = tempPort;

        System.out.println(addrTF.getText().trim());
        System.out.println(port);

        gui.sendServerInfo(addrTF.getText().trim(), port);
        gui.showLoginScreen();
    }

}

