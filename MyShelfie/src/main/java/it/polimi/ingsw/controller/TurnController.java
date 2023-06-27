package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.View;
import it.polimi.ingsw.View.VirtualView;
import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class TurnController implements Serializable {
    private static final long serialVersionUID = 1L;
    private String currentUser;
    private VirtualView view;
    private GameController controller;
    private Board board;
    private static int firstPlayer;
    private int currentPlayerIndex;
    private Map<String, VirtualView> virtualViewMap;
    private List<Player> players;
    private boolean endGame;
    private boolean playerFinished;

    public TurnController(GameController GameController, Map<String, VirtualView> virtualViewMap){
        this.controller = GameController;
        this.board = GameController.getBoard();
        this.virtualViewMap = virtualViewMap;
        initTurn();
    }

    private void initTurn(){
        players = controller.getPlayers();
        Random rand = new Random();
        firstPlayer = rand.nextInt(players.size());
        players.get(firstPlayer).setFirst(true);
        currentUser = players.get(firstPlayer).getUsername();
        currentPlayerIndex = firstPlayer;
        controller.broadcastGenericMessage("The game is starting!");
        startTurn();
    }

    /**
     * Notify all the players which one is starting his turn and ask the current player what he wants to do
     * */
    private void startTurn(){
        controller.broadcastGenericMessage("It's " + currentUser + "'s turn!!");
        view = virtualViewMap.get(currentUser);
        view.askChoiceBeginTurn();
    }

    /**
     * At the end of every turn check if someone has completed his shelf, passes the turn to the next player and refreshes the board if necessary
     * */
    private void endTurn(){
        boolean refresh = false;
        view.showMyPoints(players.get(currentPlayerIndex).getPoints());
        if(!playerFinished)
            playerFinished = players.get(currentPlayerIndex).getMyShelfie().isComplete();
        if(currentPlayerIndex + 1 < players.size())
            currentPlayerIndex++;
        else
            currentPlayerIndex = 0;
        currentUser = players.get(currentPlayerIndex).getUsername();
        if(playerFinished && players.get(currentPlayerIndex).isYellowChair())
            endGame = true;
        if(players.size() == 2 && board.getRemainingTiles() <= 17)
            refresh = board.refreshBoard();
        else if(players.size() == 3 && board.getRemainingTiles() <= 23)
            refresh = board.refreshBoard();
        else if(players.size() == 4 && board.getRemainingTiles() <= 26)
            refresh = board.refreshBoard();
        if(refresh)
            controller.broadcastGenericMessage("Not enough tiles remaining, refreshing the board!");
        if(!endGame)
            startTurn();
        else
            controller.endGame();
    }

    /**s
     * Switch case based on the number that the player types at the beginning of his turn. Each umber correspond to a different action of the game
     * */
    public void beginTurnChoice(){
        if(players.size() != 0) {
            Player p = players.get(currentPlayerIndex);
            int choice = controller.getChoosedChoise();
            switch (choice) {
                case 1:
                    view.showBoard(controller.getBoard().getBoard());
                    view.askTilesNumber();
                    break;
                case 2:
                    view.showMyShelfie(p.getPersonalGoal().getBody());
                    System.out.println(p.getUsername());
                    view.askChoiceBeginTurn();
                    break;
                case 3:
                    view.showMyShelfie(p.getMyShelfie().getStructure());
                    view.askChoiceBeginTurn();
                    break;
                case 4:
                    view.showCommonGoals(controller.getCommonGoals().get(0).getGoalsName());
                    view.showCommonGoals(controller.getCommonGoals().get(1).getGoalsName());
                    view.askChoiceBeginTurn();
                    break;
                case 5:
                    ArrayList<String> messages = new ArrayList<>();
                    for (ArrayList<String> s : controller.getMessagesBuffer()) {
                        if (Objects.equals(s.get(1), "all")) {
                            messages.add(s.get(2));
                        }
                    }
                    view.groupChat(messages);
                    view.askChoiceBeginTurn();
                    break;
                case 6:
                    ArrayList<ArrayList<String>> privateMessages = new ArrayList<>();
                    for (ArrayList<String> s : controller.getMessagesBuffer()) {
                        if (!Objects.equals(s.get(1), "all")) {
                            ArrayList<String> pm = new ArrayList<>();
                            pm.add(s.get(0));
                            pm.add(s.get(1));
                            pm.add(s.get(2));
                            privateMessages.add(pm);
                        }
                    }
                    view.privateChat(controller.getNicknames(), privateMessages, currentUser);
                    view.askChoiceBeginTurn();
                    break;
            }
        }
    }

    /**
     * Sends a message to the current player to pick the tiles from the board
     * */
    public void tilesPicking(){
        int tilesNumber, i;
        Player p = players.get(currentPlayerIndex);
        tilesNumber = controller.getChoosedTileNumber();
        if(p.getMyTiles().isEmpty())
                view.askTilePositionOnBoard(tilesNumber);
        else{
            view.showErrorMessage("You still have to place some tiles in your shelf.");
            tilesPlacing();
        }
    }

    /**
     * Sends a message to the current player to choose the column in which the player will place the tiles
     * */
    public void tilesPlacing(){
        Player p = players.get(currentPlayerIndex);
        if(!p.getMyTiles().isEmpty()) {
            view.askShelfieColumn();
        }
        else
            pointsUpdating();
    }

    /**
     *Update the points of that player
     * */
    public void pointsUpdating(){
        int personalPoints, commonPoints1 = 0, commonPoints2 = 0, adjacentPoints;
        int[] pointsArr = new int[5];
        boolean flag;
        Player p = players.get(currentPlayerIndex);
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
        endTurn();
    }

    /**
     * Remove the virtual view that correspond to the player specified
     *
     * @param user the user which virtual view will be removed
     * */
    public void removeVirtualViewMap(String user){
        virtualViewMap.remove(user);
    }
}
