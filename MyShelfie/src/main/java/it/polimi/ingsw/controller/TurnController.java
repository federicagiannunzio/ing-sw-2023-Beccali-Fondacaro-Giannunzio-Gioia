package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.FullColumnException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.Server.ServerSckt;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TurnController implements Serializable {
    private static final long serialVersionUID = 1L;
    private String currentUser;
    private ServerSckt socket;
    private View view;
    private GameController controller;
    private Board board;

    public TurnController(GameController GameController){
        this.controller = GameController;
        this.board = GameController.getBoard();
    }

    public void startTurn(Player p) throws IOException, FullColumnException {
        currentUser = p.getUsername();
        view.showStartTurnMessage(currentUser);
        tilesPicking(p);
        if(p.myTiles != null){
            tilesPlacing(p);
        }
        else
            view.showErrorMessage("No tiles selected, skipping placing phase");
        pointsUpdating(p);
    }

    private void tilesPicking(Player p) throws IOException {
        int[] tilesPos = new int[2];
        int tilesNumber, i;
        view.askTilesNumber();
        tilesNumber = controller.getTilesNumber();
        //inserire errore se l'array di tiles del player non è vuoto
        for(i = 0; i < tilesNumber; i++){
            view.askTilePositionOnBoard(tilesNumber);
            tilesPos = controller.getTilesPosition();
            p.myTiles.add(board.getTile(tilesPos[0], tilesPos[1]));
        }
    }

    private void tilesPlacing(Player p) throws FullColumnException {
        int j;
        Tile t;
        while (p.getMyTiles() != null) {
            view.showTilesArray(currentUser);
            view.askPlacingTile();
            t = controller.getPlacingTile();
            view.askShelfieColumn();
            j = controller.getShelfieColumn();
            p.getMyShelfie().addTile(t, j);
        }
        view.showEndTurnMessage(currentUser);
    }

    public void pointsUpdating(Player p){
        int personalPoints = 0, commonPoints1 = 0, commonPoints2 = 0, previousPoints;
        boolean flag;
        personalPoints = p.getPersonalGoal().checkpoints(p.getMyShelfie());
        if(p.getCommonFlag1()) {
            flag = controller.getCommonGoals().get(0).commonGoalAlg(p.getMyShelfie());
            if (flag) {
                commonPoints1 = controller.popGoalPoints(controller.getFirstGoalPoints());
                p.setCommonFlag1(false);
            }
        }
        if(p.getCommonFlag2()) {
            flag = controller.getCommonGoals().get(1).commonGoalAlg(p.getMyShelfie());
            if (flag) {
                commonPoints2 = controller.popGoalPoints(controller.getSecondGoalPoints());
                p.setCommonFlag2(false);
            }
        }
        previousPoints = p.getPoints();
        p.setPoints(previousPoints + personalPoints + commonPoints2 + commonPoints1);
    }
}
