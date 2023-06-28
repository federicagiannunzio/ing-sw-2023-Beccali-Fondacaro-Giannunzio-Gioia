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

    //per il turn controller
    private int choosedTileNumber;
    private int choosedTileRow[];
    private int choosedTileColumn[];
    private int choosedColumn;
    private int choosedChoice;
    private int choosedArrayIndex;
    private boolean flag;

    //for the chat
    ArrayList<ArrayList<String>> messagesBuffer = new ArrayList();


    private Random r = new Random();

    public GameController(){
        initController();
    }

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

    /**
     *
     *
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

    /***/
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


        currentTurn = new TurnController(this, virtualViewMap);

    }

    /**
     *
     * */
    public void endGame(){
        Player winner;
        System.out.println("A");
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
                first = players.indexOf(getPlayerByUser(firstEvent.getFirstPlayer()));
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

    public void addPlayer(Player p) {
        players.add(p);
    }

}
