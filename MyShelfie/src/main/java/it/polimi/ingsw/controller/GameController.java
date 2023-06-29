package it.polimi.ingsw.controller;

import it.polimi.ingsw.View.VirtualView;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.Events.*;
import it.polimi.ingsw.Observer.Observer;
import it.polimi.ingsw.network.Server.ClientHandler;
import java.io.Serializable;
import java.util.*;

/**
 * This class controls the evolution of the game
 * Messages are read and responses are elaborated
 */
public class GameController implements Observer, Serializable {

    private List<Player> players;
    private List<String> nicknames;
    public Map<String, VirtualView> virtualViewMap;
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

    private int choosedTileNumber;
    private int choosedTileRow[];
    private int choosedTileColumn[];
    private int choosedColumn;
    private int choosedChoice;
    private int choosedArrayIndex;
    private boolean flag;

    ArrayList<ArrayList<String>> messagesBuffer = new ArrayList();


    private Random r = new Random();

    /**
     * Default constructor
     * */
    public GameController(){
        initController();
    }

    /**
     * Initializes the controller
     * */
    public void initController(){
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
        this.maxPlayers = 0;
    }

    public Board getBoard() {
        return board;
    }

    public InputController getInputController() { return inputController;}

    public Map<String, VirtualView> getVirtualViewMap() {
        return virtualViewMap;
    }

    public TurnController getTurnController() {
        return currentTurn;
    }

    public List<CommonGoal> getCommonGoals(){return commonGoals;}

    public List<Integer> getFirstGoalPoints(){return firstGoalPoints;}

    public List<Integer> getSecondGoalPoints(){return secondGoalPoints;}

    public ArrayList<ArrayList<String>> getMessagesBuffer() {return messagesBuffer;}

    /**
     * Initializes the list that contains common goal's points.
     * */
    public void initGoalPoints(List<Integer> list){
        list.add(8);
        list.add(6);
        list.add(4);
        list.add(2);
    }

    /**
     * Remove the first element from the array that contains common goal's points
     * */
    public int popGoalPoints(List<Integer> points){
        int i = points.get(0);
        points.remove(0);
        return i;
    }

    /**
     * When a new client join the game, this method create a new player, it adds it to the list of players and
     * creates a new virtual view associated to that client. If the client is not the first to join, check if the
     * username chosen is valid.
     *
     * @param username the username of the player that has just connected
     * @param handler the client handler associated to that client
     * */
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

    /**
     Starts the game by initializing the necessary components and setting the game state to "in-game".
     @throws Exception if an error occurs during game initialization.
     */
     public void startGame() throws Exception {
        inGame = true;

        //bag init
        bag.setBag();
        bag.shuffle();

        //board init
        board.fillTheBoard(players.size()-2);
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
        //Personal goals
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

        //manda un messaggio in broadcast che segnala l'inizio del gioco

        currentTurn = new TurnController(this, virtualViewMap);

    }

    /**
     *Ends the game and determines the winner
     After determining the winner and notifying the virtual views, the method initializes the controller for a new game
     */
    public void endGame(){
        Player winner;
        if(players.size() != maxPlayers) {
            winner = players.get(0);
            for (Player p : players) {
                if (p.getPoints() > winner.getPoints()) {
                    winner = p;
                }
            }
            for(VirtualView v : virtualViewMap.values()){
                winnerNotify(v, winner.getUsername());
            }
        }
        initController();
    }



    /**
     * Updates the state of the game based on the provided event
     *
     * @param event the event to process and update the game state
     */
    @Override
    public void update(Event event) {

        switch (event.getEventType()){
            case NUMBER_OF_USERS_REPLY:
                NumberOfUsersReplyEvent nOfUser = (NumberOfUsersReplyEvent) event;
                maxPlayers = nOfUser.getNumPlayers();
                break;
            case TILES_NUMBER_REPLY:
                TilesNumberReplyEvent chooseNumber = (TilesNumberReplyEvent) event;
                choosedTileNumber = chooseNumber.getNtiles();
                if(inputController.numberOfTilesCheck(chooseNumber, virtualViewMap.get(chooseNumber.getUser())))
                    currentTurn.tilesPicking();
                break;
            case CHOOSE_TILE_REPLY:
                ChooseTileReplyEvent chooseTiles = (ChooseTileReplyEvent) event;
                if(inputController.pickedTileCheck(chooseTiles, virtualViewMap.get(chooseTiles.getUser()))){
                    choosedTileRow = chooseTiles.getRow();
                    choosedTileColumn = chooseTiles.getCol();
                    for(int i = 0; i < choosedTileRow.length; i++) {
                        getPlayerByUser(chooseTiles.getUser()).myTiles.add(board.getTile(choosedTileRow[i], choosedTileColumn[i]));
                        board.setTile(choosedTileRow[i], choosedTileColumn[i], Color.BLACK);
                    }
                    currentTurn.tilesPlacing();
                }
                else{
                    virtualViewMap.get(chooseTiles.getUser()).showGenericMessage("You can't retrieve those tiles from the board, choose some new ones.");
                    currentTurn.tilesPicking();
                }
                break;
            case PLACE_TILE_REPLY:
                PlaceTileReplyEvent placedTiles = (PlaceTileReplyEvent) event;
                if(inputController.insertedTileCheck(placedTiles, virtualViewMap.get(placedTiles.getUser()))){
                    choosedColumn = placedTiles.getColumn();
                }
                virtualViewMap.get(placedTiles.getUser()).askPlacingTile(getPlayerByUser(placedTiles.getUser()).getMyTiles());
                break;
            case CHOICEBEGINTURN_REPLY:
                ChoiceBeginTurnEvent choiceBegin = (ChoiceBeginTurnEvent) event;
                choosedChoice = choiceBegin.getChoice();
                currentTurn.beginTurnChoice();
                break;
            case TILE_ARRAY_REPLY:
                TileArrayReplyEvent tileArray = (TileArrayReplyEvent) event;
                choosedArrayIndex = tileArray.getNtiles();
                getPlayerByUser(tileArray.getUser()).getMyShelfie().addTile(getPlayerByUser(tileArray.getUser()).popTile(choosedArrayIndex), choosedColumn);

                if(getPlayerByUser(tileArray.getUser()).getMyTiles().isEmpty())
                    currentTurn.pointsUpdating();
                else
                    virtualViewMap.get(tileArray.getUser()).askPlacingTile(getPlayerByUser(tileArray.getUser()).getMyTiles());
                break;
            case CHATMESSAGE:
                ChatMessageEvent chatMessageEvent = (ChatMessageEvent) event;
                String recipient = chatMessageEvent.getRecipient();
                String sender = chatMessageEvent.getUser();

                if(recipient!=null) {
                    for(String message : chatMessageEvent.getMessages()) {
                        ArrayList<String> a = new ArrayList<>();
                        a.add(sender);
                        a.add(recipient);
                        a.add(sender + ": " + message);
                        messagesBuffer.add(a);
                    }
                } else {
                    for(String message : chatMessageEvent.getMessages()) {
                        ArrayList<String> a = new ArrayList<>();

                        a.add(sender);
                        a.add("all");
                        a.add(sender + ": " + message);
                        messagesBuffer.add(a);
                    }
                }
                break;
        }

    }

    /**
     * The server sends a message to all the players
     *
     * @param string the message from the server
     * */
    public void broadcastGenericMessage(String string){
        for(Player player : players){
            getVirutalViewByUser(player.getUsername()).showGenericMessage(string);
        }
    }

    /**
     * Removes a player from the list of players
     *
     * @param user the player that will be removed
     * */
    public void removePlayer(String user){
        players.remove(getPlayerByUser(user));
    }



    /**
     *Notifies the specified {@link VirtualView} about the winner of the game.
     *
     * @param view The {@link VirtualView} to notify.
     * @param user The username of the winner.
     */
    public void winnerNotify(VirtualView view, String user){
        view.showWinMessage(user);
    }


    public List<Player> getPlayers() {
        return players;
    }

    public int getChoosedTileNumber(){
        return choosedTileNumber;
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

    /**
     * Returns the {@link Player} that corresponds to the username specified
     *
     * @param username the username of the Player
     * */
    public Player getPlayerByUser(String username){
        for(Player p : players)
            if(p.getUsername().equals(username))
                return p;
        return null;
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

    /**
     * This method choose randomly a common goal to set for the game
     * */
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
                return null;
        }
    }

    /***
     * Adds a {@link Player} to the list of players
     *
     * @param p the player that will be added
     */
    public void addPlayer(Player p) {
        players.add(p);
    }

}
