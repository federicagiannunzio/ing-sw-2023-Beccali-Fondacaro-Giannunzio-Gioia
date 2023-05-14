package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.View;
import it.polimi.ingsw.View.VirtualView;
import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TurnController implements Serializable {
    private static final long serialVersionUID = 1L;
    private String currentUser;
    private VirtualView view;
    private GameController controller;
    private Board board;

    public TurnController(GameController GameController, VirtualView view){
        this.controller = GameController;
        this.board = GameController.getBoard();
        this.view = view;
    }

    public void startTurn(Player p) throws IOException, FullColumnException, notAvailableTileException {
        currentUser = p.getUsername();
        view.showTurnState(currentUser);
        view.askChoiceBeginTurn();
        int choice = controller.getChoosedChoise();
        while(choice != 1){
            if(choice == 2)
                view.showMyShelfie(p.getPersonalGoal().getBody());
            else if(choice == 3)
                view.showMyShelfie(p.getMyShelfie().getStructure());
            else if(choice == 4) {
                view.showCommonGoals(controller.getCommonGoals().get(0).getGoalsName());
                view.showCommonGoals(controller.getCommonGoals().get(1).getGoalsName());
            }
        }
        tilesPicking(p);
        if(p.myTiles != null){
            tilesPlacing(p);
        }
        else
            view.showErrorMessage("No tiles selected, skipping placing phase");
        pointsUpdating(p);
        view.showMyPoints(p.getPoints());
    }

    private void tilesPicking(Player p) throws notAvailableTileException {
        int tilesCol, tilesRow;
        int tilesNumber, i;
        boolean flag = false;
        view.askTilesNumber();
        tilesNumber = controller.getChoosedTileNumber();
        if(p.getMyTiles() == null) {
            for (i = 0; i < tilesNumber; i++) {
                view.askTilePositionOnBoard();
               tilesCol = controller.getChoosedTileColumn();
               tilesRow = controller.getChoosedTileRow();
                p.myTiles.add(board.takeTileFromBoard(tilesCol, tilesRow));
            }
        }else{
            view.showErrorMessage("You still have to place some tiles in your shelf.");
        }
    }

    private void tilesPlacing(Player p) throws FullColumnException {
        int j;
        Tile t;
        while (p.getMyTiles() != null) {
            ArrayList<Tile> playerTiles = p.getMyTiles();
            view.askPlacingTile(playerTiles);
            t = p.getMyTiles().get(controller.getArrayIndex());
            view.askShelfieColumn();
            j = controller.getChoosedColumn();
            p.getMyShelfie().addTile(t, j);
        }
    }

    public void pointsUpdating(Player p){
        int personalPoints, commonPoints1 = 0, commonPoints2 = 0, adjacentPoints;
        int[] pointsArr = new int[5];
        boolean flag;
        personalPoints = p.getPersonalGoal().checkpoints(p.getMyShelfie());
        pointsArr = p.getMyShelfie().adjacentItem();
        adjacentPoints = (pointsArr[1] * 2);
        adjacentPoints = adjacentPoints + (pointsArr[2] * 3);
        adjacentPoints = adjacentPoints + (pointsArr[3] * 5);
        adjacentPoints = adjacentPoints + (pointsArr[4] * 8);
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
        p.setPoints(personalPoints + commonPoints2 + commonPoints1 + adjacentPoints);
    }
}
