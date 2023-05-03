package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.View.GameView;
import it.polimi.ingsw.network.ServerSckt;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TurnController implements Serializable {
    private static final long serialVersionUID = 1L;
    private String currentUser;
    private ServerSckt socket;
    private GameView view;
    private ArrayList<Tile> myTiles;

    public void startTurn(Player p){
        currentUser = p.getUsername();
        view.printStartTurn(p);
        myTiles = tilesPicking();
    }

    private ArrayList<Tile> tilesPicking() throws IOException {
        view.askTilesNumber();

        return null;
    }
}
