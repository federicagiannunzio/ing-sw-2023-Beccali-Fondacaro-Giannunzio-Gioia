package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.util.*;

public class GameController {

    List<Player> players;
    int currentPlayer;

    List<CommonGoal> commonGoals;
    int[] personalGoal;
    Bag bag;
    Board board;

    boolean inGame;
    TurnController turn = new TurnController();


    Random r = new Random();

    public GameController(){
        inGame = false;
        players = new ArrayList<>();
        commonGoals = new ArrayList<>();
        personalGoal = new int[]{0,1,2,3,4,5,6,7,8,9,10,11};
        bag = new Bag();
        board = new Board(bag);
    }

    public boolean playerJoin(String username){
        if(players.size()+1 <= 4){
            Player newPlayer = new Player(username, players.size(), false);
            players.add(newPlayer);
            return true;
        } else{
            return false;
        }
    }

    public void startGame() throws Exception {

        inGame = true;

        //bag init
        bag.shuffle();

        //board init
        board.fillTheBoard(players.size());

        //Common goals
        commonGoals.add(pickCommonGoal());
        commonGoals.add(pickCommonGoal());

        //Persona goals
        for(Player p: players){
            int random = r.nextInt(11);
            while(!commonGoals.contains(random)){
                random = r.nextInt(11);
            }
            commonGoals.remove(random);
            personalGoal goal = new personalGoal(random);
            goal.createTheGoal(random);
            p.setPersonalGoal(goal);
        }

        //select first player
        int first = r.nextInt(players.size()-1);
        players.get(first).setFirst(true);
        currentPlayer = first;

        //game loop
        while(inGame){

           Player turnPlayer = players.get(currentPlayer);
           turn.startTurn(turnPlayer);

            //point
            // ...

            if(currentPlayer+1 < players.size()){
                currentPlayer++;
            }else{
                currentPlayer = 0;
            }
        }

    }

    //problema: possono uscire due commonGoal uguali
    private CommonGoal pickCommonGoal(){

        int random = r.nextInt(11);

        switch (random){
            case 0:
                return new CornersGoal();
            case 1:
                return new CouplesGoal();
            case 2:
                return new CrossGoal();
            case 3:
                return new DiagonalGoal();
            case 4:
                return new EightGoal();
            case 5:
                return new FourGroupsGoal();
            case 6:
                return new FourRowsGoal();
            case 7:
                return new SquaresGoal();
            case 8:
                return new StairsGoal();
            case 9:
                return new ThreeColumnsGoal();
            case 10:
                return new TwoColumnsGoal();
            case 11:
                return new TwoRowsGoal();
            default:
                return new CornersGoal();
        }

    }
}
