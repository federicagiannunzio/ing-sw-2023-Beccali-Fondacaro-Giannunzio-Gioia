package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.util.*;

public class GameController {

    private List<Player> players;
    private int currentPlayer;

    private List<CommonGoal> commonGoals;
    private int[] personalGoal;
    private Bag bag;
    private Board board;
    private List<Integer> firstGoalPoints;
    private List<Integer> secondGoalPoints;

    private boolean inGame;
    private TurnController turn = new TurnController();

    private Random r = new Random();

    public GameController(){
        inGame = false;
        players = new ArrayList<>();
        commonGoals = new ArrayList<>();
        personalGoal = new int[]{0,1,2,3,4,5,6,7,8,9,10,11};
        bag = new Bag();
        board = new Board(bag);
        initGoalPoints(firstGoalPoints);
        initGoalPoints(secondGoalPoints);
    }

    public Board getBoard() {
        return board;
    }

    public TurnController getTurnController() {
        return turn;
    }

    public List<CommonGoal> getCommonGoals(){return commonGoals;}

    public List<Integer> getFirstGoalPoints(){return firstGoalPoints;}

    public List<Integer> getSecondGoalPoints(){return secondGoalPoints;}

    public void initGoalPoints(List<Integer> list){
        list.add(8);
        list.add(6);
        list.add(4);
        list.add(2);
    }

    public int popGoalPoints(List<Integer> points){
        int i = points.get(0);
        points.remove(0);
        return i;
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

    public List<Player> getPlayers(){
        List<Player> tmp = new ArrayList<>();
        tmp.addAll(players);
        return tmp;
    }

    public Optional<Player> getPlayerByUsername(String username){
        Player tmp = null;
        for(Player p : players){
            if(p.getUsername().equals(username)){
                tmp = p;
            }
        }
        return tmp == null ? Optional.empty() : Optional.of(tmp);
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
