package it.polimi.ingsw.View;

import it.polimi.ingsw.controller.Observer.Observable;
import it.polimi.ingsw.controller.Observer.ViewObservable;
import it.polimi.ingsw.model.*;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.System.out;

public class Cli extends ViewObservable implements View {

    private Scanner input;
    private Thread inputThread;


    public void start()  {
        System.out.print("\n" +
                ".___  ___. ____    ____         _______. __    __   _______  __       _______  __   _______ \n" +
                "|   \\/   | \\   \\  /   /        /       ||  |  |  | |   ____||  |     |   ____||  | |   ____|\n" +
                "|  \\  /  |  \\   \\/   /        |   (----`|  |__|  | |  |__   |  |     |  |__   |  | |  |__   \n" +
                "|  |\\/|  |   \\_    _/          \\   \\    |   __   | |   __|  |  |     |   __|  |  | |   __|  \n" +
                "|  |  |  |     |  |        .----)   |   |  |  |  | |  |____ |  `----.|  |     |  | |  |____ \n" +
                "|__|  |__|     |__|        |_______/    |__|  |__| |_______||_______||__|     |__| |_______|\n" +
                "                                                                                            \n");
        System.out.println("Welcome to MyShelfie!");

        askServerInfo();
    }

    /**
     * Read line from the standard input.
     */
    /**
     * Reads a line from the standard input.
     *
     * @return the string read from the input.
     */
    public String readLine() {
        input = new Scanner(System.in);
        input.reset();
        String str = input.nextLine();
        return str;
    }

    public void askServerInfo(){
        boolean valid = false;
        String tryAddress = null;
        int tryPort = 0;

        while (!valid) {
            try {
                System.out.println("Please enter the server address: ");
                tryAddress = readLine();
                System.out.println("Please enter the port number: ");
                tryPort = Integer.parseInt(readLine());
                valid = checkValidIP(tryAddress);
            }catch(NumberFormatException e){
                System.out.println("You must insert a number, not a string. Please try again");
            }
        }
        String address = tryAddress;
        int port = tryPort;
        notifyObserver(obs -> obs.sendServerInfo(address, port));
    }

    public void askUsername() {
        System.out.println("Please enter your username: ");
        String username = readLine();
        notifyObserver(obs -> obs.sendUsername(username));
    }

    @Override
    public void askPlayersNumber(){
        int playersNumber, tryPlayersNumber = 0;
        System.out.println("Please select the number of players. Choose a number between 2 and 4");
        do {
            try {
                String playerNumberString = readLine();
                tryPlayersNumber = Integer.parseInt(playerNumberString);
            }catch(NumberFormatException e){
                System.out.println("You must insert a number, not a string. Please try again");
            }
        }while(tryPlayersNumber > 4 || tryPlayersNumber < 2);
        playersNumber = tryPlayersNumber;
        notifyObserver(obs -> obs.sendPlayersNumber(playersNumber));
    }

    @Override
    public void askFirstPLayer(List<String> usersOnGame) {
        System.out.println("Your the first player that has connected. Please choose the player who will starts the game.");
        System.out.println("These are the players online: ");
        for(String users : usersOnGame)
            System.out.println(users + ", ");
        String tryUserChosen;
        do {
            System.out.println("Type the exact name of the player.");
            tryUserChosen = readLine();
            if(!usersOnGame.contains(tryUserChosen))
                System.out.println("This username is not valid. Please try again.");
        } while(!usersOnGame.contains(tryUserChosen));
        String userChosen = tryUserChosen;
        notifyObserver(obs -> obs.sendFirstPlayerChosen(userChosen));
    }

    @Override
    public void askTilesNumber(){
        int tryNumberOfTiles = 0;
        do {
            try {
                System.out.println("How many tiles you want to pick?");
                tryNumberOfTiles = Integer.parseInt(readLine());
                if (tryNumberOfTiles < 1 || tryNumberOfTiles > 3)
                    System.out.println("The number of tiles must be between 1 and 3. Please try again.");
            }catch(NumberFormatException e){
                System.out.println("You must insert a number, not a string. Please try again");
            }
        }while(tryNumberOfTiles < 1 || tryNumberOfTiles > 3);
        int numberOfTiles = tryNumberOfTiles;
        notifyObserver(obs -> obs.sendTilesNumberChosen(numberOfTiles));
    }

    @Override
    public void askTilePositionOnBoard(int tilesNumber) {
        int[] row = new int[tilesNumber];
        int[] col = new int[tilesNumber];
        System.out.println("Select the tile you want to pick.");
        for(int i = 0; i < tilesNumber; i++){
            try {
                System.out.println("ROW: ");
                row[i] = Integer.parseInt(readLine());
                System.out.println("COLUMN: ");
                col[i] = Integer.parseInt(readLine());
            }catch(NumberFormatException e){
                System.out.println("You must insert a number, not a string. Please try again");
                i--;
            }
        }
        notifyObserver(obs -> obs.sendTilePosition(row, col));
    }

    @Override
    public void askShelfieColumn() {
        int tryCol = -1;
        System.out.println("In which column you want to put your tiles?");
        do {
            try{
                tryCol = Integer.parseInt(readLine());
                if (tryCol >= 5 || tryCol < 0)
                    System.out.println("The inserted number has to be between 0 and 4. Please try again.");
            }catch(NumberFormatException e){
                System.out.println("You must insert a number, not a string. Please try again");
            }
        }while(tryCol >= 5 || tryCol < 0);
        int col = tryCol;
        notifyObserver(obs -> obs.sendShelfieColumn(col));
    }

    @Override
    public void askPlacingTile(List<Tile> tilesArray){
        int tryN = 0;
        showTilesArray(tilesArray);
        do {
            System.out.println("Which tile do you want to insert? Please select a number.");
            try {
                tryN = Integer.parseInt(readLine());
                if (tryN > 3 || tryN < 1 || tryN > tilesArray.size()){
                    System.out.println("The number chose must be between 1 and 3. Please try again.");
                    showTilesArray(tilesArray);
            }
            }catch(NumberFormatException e){
                System.out.println("You must insert a number, not a string");
            }

        }while(tryN > 3 || tryN < 1 || tryN > tilesArray.size());
        int n = tryN - 1;
        notifyObserver(obs->obs.sendArrayTileIndex(n));
    }


    @Override
    public void showBoard(Tile[][] structure) {

        StringBuilder strRowBld = new StringBuilder();
        StringBuilder strColBld = new StringBuilder();
        StringBuilder strIndxBld = new StringBuilder();

        strIndxBld.append(Color.YELLOW_BOLD).append("       0     1     2     3     4     5     6     7     8").append(Color.RESET);
        strRowBld.append(Color.YELLOW_BOLD).append("\n    +-----+-----+-----+-----+-----+-----+-----+-----+-----+\n").append(Color.RESET);
        strColBld.append(Color.YELLOW_BOLD).append("   |").append(Color.RESET);
        System.out.print(strIndxBld);
        for (int i = 0; i < 9; i++) {
            StringBuilder strColIndxBld = new StringBuilder();
            System.out.print(strRowBld);
            strColIndxBld.append(Color.YELLOW_BOLD).append(i).append(Color.RESET);
            System.out.print(strColIndxBld);
            for (int j = 0; j < 9; j++) {
                System.out.print(strColBld);
                if (structure[i][j] != null && !structure[i][j].getColor().equals(Color.BLACK)) {
                    StringBuilder tile = new StringBuilder();
                    tile.append(structure[i][j].getColor()).append(" x").append(Color.RESET);
                    System.out.print(tile);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print(strColBld);
        }
        System.out.println(strRowBld);
    }


    @Override
    public void showMyShelfie(Tile[][] structure) {

        StringBuilder strRowBld = new StringBuilder();
        StringBuilder strColBld = new StringBuilder();

        strRowBld.append(Color.YELLOW_BOLD).append("\n   +-----+-----+-----+-----+-----+\n").append(Color.RESET);
        strColBld.append(Color.YELLOW_BOLD).append("   |").append(Color.RESET);

        for (int i = 0; i < 6; i++) {
            System.out.print(strRowBld);
            for (int j = 0; j < 5; j++) {
                System.out.print(strColBld);
                if (structure[i][j] != null) {
                    StringBuilder tile = new StringBuilder();
                    tile.append(structure[i][j].getColor()).append(" x").append(Color.RESET);
                    System.out.print(tile);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print(strColBld);
        }
        System.out.println(strRowBld);
    }


    @Override
    public void showCommonGoals(CommonGoalsName name) {
        switch(name) {
            case CORNERS:
                System.out.println("Four tiles of the same type in the four corners of the bookshelf. ");
                break;
            case COUPLES:
                System.out.println("Six groups each containing at least 2 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group.");
                break;
            case CROSS:
                System.out.println("Five tiles of the same type forming an X.");
                break;
            case DIAGONAL:
                System.out.println("Five tiles of the same type forming a diagonal. ");
                break;
            case EIGHT:
                System.out.println("Eight tiles of the same type. There’s no restriction about the position of these tiles.");
                break;
            case FOURGROUPS:
                System.out.println("Four groups each containing at least 4 tiles of the same type (not necessarily in the depicted shape). The tiles of one group can be different from those of another group");
                break;
            case FOURROWS:
                System.out.println("Four lines each formed by 5 tiles of maximum three different types. One line can show the same or a different combination of another line");
                break;
            case SQUARES:
                System.out.println("Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square.");
                break;
            case STAIRS:
                System.out.println("Five columns of increasing or decreasing height. Starting from the first column on the left or on the right, each next column must be made of exactly one more tile. Tiles can be of any type.");
                break;
            case THREE:
                System.out.println("Three columns each formed by 6 tiles Five tiles of the same type forming an X of maximum three different types column can show the same or a different combination of another column");
                break;
            case TWOCOLUMNS:
                System.out.println("Two columns each formed by 6 different types of tiles.");
                break;
            case TWOROWS:
                System.out.println("Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line");
                break;
        }
    }

    @Override
    public void showMyPoints(int currentPoints) {
        System.out.println("ACTUAL SCORE: " + currentPoints);

    }

    @Override
    public void showCommonGoalCompletion(String username, String commonGoal, int pointsAchieved) {
        System.out.println(username + " has completed " + commonGoal + " common goal.");
        System.out.println(username + " has achieved " + pointsAchieved + " points.");
    }

    @Override
    public void showWinMessage(String winner) {
        System.out.println("Game finished: " + winner + " WINS!");
        System.exit(0);
    }

    @Override
    public void showLoginResult(boolean usernameAccepted, boolean connectionSuccessful, String username) {

        if (usernameAccepted && connectionSuccessful) {
            out.println("Hi, " + username + "! You connected to the server.");
        } else if (connectionSuccessful) {
            askUsername();
        } else if (usernameAccepted) {
            out.println("Max players reached. Connection refused.");
            out.println("EXIT.");
            System.exit(1);
        } else {
            showErrorMessage("Could not contact server.");
            System.exit(1);
        }
    }

    public void showGenericMessage(String messageToSend) {
        System.out.println(messageToSend);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        System.out.println("[ERROR]: " + errorMessage);
    }

    @Override
    public void showConnectionMessage(String connectedUser) {
        System.out.println("Hello " + connectedUser + " welcome to the game!");
    }

    @Override
    public void showDisconnectionMessage(String nicknameDisconnected) {
        inputThread.interrupt();
        System.out.println("\n" + nicknameDisconnected + "has just disconnected. GAME ENDED ");

        System.exit(1);
    }

    @Override
    public void showTurnState(String user) {
        System.out.println(user + " started his turn");
    }

    @Override
    public void showTilesArray(List<Tile> tilesPicked) {
        System.out.println("These are the tiles that you have picked. ");
        for(int i=0; i < tilesPicked.size(); i++) {
            System.out.println((i + 1) + "): " + tilesPicked.get(i).getColor() + tilesPicked.get(i).getColor().parseToString(tilesPicked.get(i).getColor()) + " tile" +Color.RESET);
        }
    }

    public void showLobby(List<String> nicknameList, int numPlayers) {
        System.out.println("LOBBY:");
        for (String nick : nicknameList) {
            System.out.println(nick + "\n");
        }

        System.out.println("Current players in lobby: " + nicknameList.size() + " / " + numPlayers);
    }

    public void showChoicesBeginTurn() {
        System.out.println("Please choose what do you want to do.");
        System.out.println("1) Start my turn.");
        System.out.println("2) View my personal goal.");
        System.out.println("3) View my shelfie.");
        System.out.println("4) View common goals.");
    }

    public void askChoiceBeginTurn() {
        showChoicesBeginTurn();
        int choice;
        int tryChoice;
        do {
            tryChoice = Integer.parseInt(readLine());
            if(tryChoice < 1 || tryChoice>4)
                System.out.println("Please insert a valid number. The number should be selected between 1 and 4");
        } while(tryChoice < 1 || tryChoice>4);
        choice = tryChoice;
        notifyObserver(obs->obs.sendChoiceBeginTurn(choice));
    }

    public boolean checkValidIP(String IP) {
        boolean notValid = (IP == null || IP.length() < 7 || IP.length() > 15);
        if (notValid) {
            return false;
        } else {
            return IP.matches("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$") || IP.matches("localhost");
        }
    }


}
