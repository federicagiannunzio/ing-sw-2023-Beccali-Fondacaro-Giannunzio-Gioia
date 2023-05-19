package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.VirtualView;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.Events.*;
import it.polimi.ingsw.controller.Observer.Observer;
import it.polimi.ingsw.network.Server.ClientHandler;


import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class GameController implements Observer, Serializable {

    private List<Player> players;
    private List<String> nicknames;
    private Map<String, VirtualView> virtualViewMap;
    private int currentPlayer;
    private int first;

    private int maxPlayers;

    private List<CommonGoal> commonGoals;
    private int[] personalGoal;
    private Bag bag;
    private Board board;
    private List<Integer> firstGoalPoints;
    private List<Integer> secondGoalPoints;

    private boolean inGame;
    private TurnController currentTurn;
    private InputController inputController;

    //per il turn controller
    private int choosedTileNumber;
    private int choosedTileRow;
    private int choosedTileColumn;
    private int choosedColumn;
    private int choosedChoice;
    private int choosedArrayIndex;
    private boolean flag;

    private Random r = new Random();

    public GameController(){
        inputController = new InputController(this);
        inGame = false;
        players = new ArrayList<>();
        nicknames = new ArrayList<>();
        virtualViewMap = new HashMap<>();
        commonGoals = new ArrayList<>();
        personalGoal = new int[]{0,1,2,3,4,5,6,7,8,9,10,11};
        bag = new Bag();
        board = new Board(bag);
        this.firstGoalPoints = new ArrayList<>();
        this.secondGoalPoints = new ArrayList<>();
    }

    public Board getBoard() {
        return board;
    }

    public TurnController getTurnController() {
        return currentTurn;
    }

    public List<CommonGoal> getCommonGoals(){return commonGoals;}

    public List<Integer> getFirstGoalPoints(){return firstGoalPoints;}

    public List<Integer> getSecondGoalPoints(){return secondGoalPoints;}

    private void initGoalPoints(List<Integer> list){
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

    public void playerJoin(String username, ClientHandler handler){
        VirtualView VView = new VirtualView(handler);
        if(maxPlayers == 0) {
            Player newPlayer = new Player(username, players.size(), false);
            players.add(newPlayer);
            nicknames.add(newPlayer.getUsername());
            virtualViewMap.put(username, VView);
            VView.showLoginResult(true, true, username);
            VView.askPlayersNumber();
        }
        else if(players.size()+1 <= maxPlayers && inputController.checkUsernameValidity(username, VView)){
            Player newPlayer = new Player(username, players.size(), false);
            players.add(newPlayer);
            nicknames.add(newPlayer.getUsername());
            virtualViewMap.put(username, VView);
            VView.showLoginResult(true, true, username);
            if(players.size() == maxPlayers){
                try {
                    startGame();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else{
            VView.showLoginResult(false, true, username);
        }
    }

    public void startGame() throws Exception {

        inGame = true;

        //bag init
        bag.setBag();
        bag.shuffle();

        //board init
        board.fillTheBoard(players.size());

        //Common goals
        CommonGoal firstGoal = pickCommonGoal();
        CommonGoal secondGoal = pickCommonGoal();
        while (secondGoal.equals(firstGoal)){
            secondGoal = pickCommonGoal();
        }
        commonGoals.add(firstGoal);
        commonGoals.add(secondGoal);
        initGoalPoints(firstGoalPoints);
        initGoalPoints(secondGoalPoints);

        //Persona goals
        for(Player p: players){
            int random = r.nextInt(11);
            while(personalGoal[random] == -1){
                random = r.nextInt(11);
            }
            personalGoal[random] = -1;
            personalGoal goal = new personalGoal(random);
            goal.createTheGoal(random);
            p.setPersonalGoal(goal);
        }

        currentTurn = new TurnController(this, virtualViewMap);

    }

    public void endGame(){
        Player winner = players.get(0);
        for(Player p: players){
            if(p.getPoints() > winner.getPoints()){
                winner = p;
            }
        }

        for(VirtualView v : virtualViewMap.values()){
            winnerNotify(v, winner.getUsername());
        }
    }
    @Override
    public void update(Event event) {

        switch (event.getEventType()){
            /*case LOGIN_REQUEST:
                LoginRequestEvent login = (LoginRequestEvent) event;
                inputController.checkUsernameValidity(login.getUser(), )
                if(playerJoin(login.getUser())){
                    //send to client
                }else{
                    //send to client
                }
                break;*/
            case NUMBER_OF_USERS_REPLY:
                NumberOfUsersReplyEvent nOfUser = (NumberOfUsersReplyEvent) event;
                maxPlayers = nOfUser.getNumPlayers();
                break;
            case FIRSTPLAYER_REPLY:
                FirstPlayerReplyEvent firstEvent = (FirstPlayerReplyEvent) event;
                first = players.indexOf(getPlayerByUsername(firstEvent.getFirstPlayer()));
                break;
            case TILES_NUMBER_REPLY:
                TilesNumberReplyEvent chooseNumber = (TilesNumberReplyEvent) event;
                choosedTileNumber = chooseNumber.getNtiles();
                currentTurn.tilesPicking();
                break;
            case CHOOSE_TILE_REPLY:
                ChooseTileReplyEvent chooseTiles = (ChooseTileReplyEvent) event;
                if(inputController.pickedTileCheck(chooseTiles, virtualViewMap.get(chooseTiles.getUser()))){
                    choosedTileRow = chooseTiles.getrow();
                    choosedTileColumn = chooseTiles.getcolumn();
                }
                break;
            case PLACE_TILE_REPLY:
                PlaceTileReplyEvent placedTiles = (PlaceTileReplyEvent) event;
                if(inputController.insertedTileCheck(placedTiles, virtualViewMap.get(placedTiles.getUser()))){
                    choosedColumn = placedTiles.getColumn();
                }
                break;
            case CHOICEBEGINTURN_REPLY:
                ChoiceBeginTurnEvent choiceBegin = (ChoiceBeginTurnEvent) event;
                choosedChoice = choiceBegin.getChoice();
                currentTurn.beginTurnChoice(players.get(currentPlayer));
                break;
            case TILE_ARRAY_REPLY:
                TileArrayReplyEvent tileArray = (TileArrayReplyEvent) event;
                choosedArrayIndex = tileArray.getNtiles();
                break;
        }

    }

    public void sendLogInRequest(VirtualView view, String username) {
        view.showLoginResult(true, true, username);
    }

    public void broadcastGenericMessage(String string){
        for(Player player : players){
            getVirutalViewByUser(player.getUsername()).showGenericMessage(string);
        }
    }

    public void sendNumberOfUserRequest(VirtualView view) {
        view.askPlayersNumber();
    }

    public void sendFirstPlayerRequest(VirtualView view) {
        view.askFirstPLayer(players.stream().map( (p) -> p.getUsername()).collect(Collectors.toList()));
    }

    public void sendTilesNumberRequest(VirtualView view) {
        view.askTilesNumber();
    }

    public void ChooseTilesRequest(VirtualView view) {
        view.askTilePositionOnBoard();
    }

    public void placeTilesRequest(VirtualView view, List<Tile> tilesList) {
        view.askPlacingTile(tilesList);
    }

    public void tilesArrayRequest(VirtualView view, List<Tile> tilesList) {
        view.showTilesArray(tilesList);
    }

    public void showBoardCommand(VirtualView view) {
        view.showBoard(board.getBoard());
    }

    public void showShelfieCommand(VirtualView view, Shelfie playerShelfie) {
        view.showMyShelfie(playerShelfie.getStructure());
    }

    public void sendCommonGoalCommand(VirtualView view, CommonGoal goal) {
        view.showCommonGoals(goal.getGoalsName());
    }

    public void showCurrentPointsCommand(VirtualView view, Player player) {
        view.showMyPoints(player.getPoints());
    }

    public void sendTurnStateRequest(VirtualView view, Player p) {
        view.showTurnState(p.getUsername());
    }

    public void CommonGoalNotify(VirtualView view, CommonGoal goal, Player p, int points) {
        view.showCommonGoalCompletion(p.getUsername(), goal.getGoalsName().name(), points);
    }

    public void winnerNotify(VirtualView view, String user){
        view.showWinMessage(user);
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

    public int getChoosedTileNumber(){
        return choosedTileNumber;
    }

    public int getChoosedTileRow(){
        return choosedTileRow;
    }

    public int getChoosedTileColumn(){
        return choosedTileColumn;
    }

    public int getChoosedColumn(){
        return choosedColumn;
    }

    public int getChoosedChoise(){
        return choosedChoice;
    }

    public int getArrayIndex(){
        return choosedArrayIndex;
    }

    public boolean isInGame() {
        return inGame;
    }

    public String getActivePlayer(){
        return players.get(currentPlayer).getUsername();
    }
    public VirtualView getVirutalViewByUser(String user){
        return virtualViewMap.get(user);
    }

    public List<String> getNicknames(){
        return this.nicknames;
    }

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
