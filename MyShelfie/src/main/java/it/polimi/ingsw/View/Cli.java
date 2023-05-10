package it.polimi.ingsw.View;

import it.polimi.ingsw.controller.Observer.Observable;
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


    public void start() throws ExecutionException {
        System.out.print("\n" +
                ".___  ___. ____    ____         _______. __    __   _______  __       _______  __   _______ \n" +
                "|   \\/   | \\   \\  /   /        /       ||  |  |  | |   ____||  |     |   ____||  | |   ____|\n" +
                "|  \\  /  |  \\   \\/   /        |   (----`|  |__|  | |  |__   |  |     |  |__   |  | |  |__   \n" +
                "|  |\\/|  |   \\_    _/          \\   \\    |   __   | |   __|  |  |     |   __|  |  | |   __|  \n" +
                "|  |  |  |     |  |        .----)   |   |  |  |  | |  |____ |  `----.|  |     |  | |  |____ \n" +
                "|__|  |__|     |__|        |_______/    |__|  |__| |_______||_______||__|     |__| |_______|\n" +
                "                                                                                            \n");
        System.out.println("Welcome to MyShelfie!");

        try {
            askServerInfo();
        } catch () {

        }
    }

    /**
     * Read line from the standard input.
     */
    /**
     * Reads a line from the standard input.
     *
     * @return the string read from the input.
     * @throws ExecutionException if the input stream thread is interrupted.
     */
    public String readLine() {
        input = new Scanner(System.in);
        input.reset();
        String str = input.nextLine();
        return str;
    }

    public void askServerInfo(){
        boolean valid = false;

        while (!valid) {
            System.out.println("Please enter the server address: ");
            String address = readLine();
            System.out.println("Please enter the port number: ");
            String port = readLine();
            valid = checkValidIP(address);
        }
    }

    public void askUsername() {
        out.print("Please enter your username: ");
        String username = readLine();
        notifyObserver(obs -> obs.sendUsername(username));
    }

    @Override
    public void askPlayersNumber(){
        int playersNumber;

        try {
            System.out.println("Please select the number of players. Choose a number between 2 and 4");
            String playerNumberString = readLine();
            playersNumber = Integer.parseInt(playerNumberString);
            //notifyObserver()
        } catch () {

        }
    }

    @Override
    public void askFirstPLayer(List<String> usersOnGame) {
        System.out.println("Your the first player that has connected. Please choose the player who will starts the game.");
        System.out.println("These are the players online: ");
        for(String users : usersOnGame)
            System.out.println(users + ",");
        try {
            //System.out.println("Type the exact name of the player.");
            String userChosen;
            do {
                System.out.println("Type the exact name of the player.");
                userChosen = readLine();
                if(!usersOnGame.contains(userChosen))
                    System.out.println("This username is not valid. Please type another name.");
            } while(!usersOnGame.contains(userChosen));
            //notifyObserver();
        } catch() {

        }
    }

    @Override
    public void askTilesNumber(){
        int numberOfTiles;
        System.out.println("How many tiles you want to pick?");
        numberOfTiles = Integer.parseInt(readLine());
    }

    @Override
    public void askTilePositionOnBoard(int numberOfTiles) {
        System.out.println("Select the tiles you want to pick.");
        try {
            for(int i=0; i < numberOfTiles; i++) {
                System.out.print("Tile number " + (i+1));
                System.out.println("ROW: ");
                int row = Integer.parseInt(readLine());
                System.out.println("COLUMN: ");
                int col = Integer.parseInt(readLine());
            }
            //notifyobserver

        } catch () {

        }

    }

    @Override
    public void askShelfieColumn() {
        System.out.println("In which column you want to put your tiles?");
        int col;
        try {
            col = Integer.parseInt(readLine());
            //notifyObserver
        } catch () {

        }
    }

    @Override
    public void askPlacingTile(List<Tile> tilesPicked) {
        try {
            do {
                for(int i=0; i < tilesPicked.size(); i++) {
                    System.out.println((i+1) + ") " + tilesPicked.get(i).getColor() + " tile");
                }
                System.out.println("Which tile do you want to insert? Please select a number.");
                int n = Integer.parseInt(readLine());
                //notifyObserver();
                tilesPicked.remove(n-1);
            } while (!tilesPicked.isEmpty());
        } catch() {

        }
    }

    @Override
    public void showBoard(Tile[][] structure) {

        StringBuilder strRowBld = new StringBuilder();
        StringBuilder strColBld = new StringBuilder();

        strRowBld.append(Color.YELLOW_BOLD).append("\n   +-----+-----+-----+-----+-----+\n").append(Color.RESET);
        strColBld.append(Color.YELLOW_BOLD).append("   |").append(Color.RESET);
        //tile.append(Colori.GREEN).append(" x").append(Colori.RESET);

        for (int i = 0; i < 9; i++) {
            System.out.print(strRowBld);
            for (int j = 0; j < 9; j++) {
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
    public void showMyShelfie(Tile[][] structure) {

        StringBuilder strRowBld = new StringBuilder();
        StringBuilder strColBld = new StringBuilder();

        strRowBld.append(Color.YELLOW_BOLD).append("\n   +-----+-----+-----+-----+-----+\n").append(Color.RESET);
        strColBld.append(Color.YELLOW_BOLD).append("   |").append(Color.RESET);
        //tile.append(Colori.GREEN).append(" x").append(Colori.RESET);

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
        //System.exit(0);
    }

    @Override
    public void showLoginResult() {

    }

    public void showGenericMessage(String messageToSend) {
        System.out.println(messageToSend);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void showConnectionMessage(String connectedUser) {

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
            System.out.println(i + "): " + tilesPicked.get(i).getColor() + tilesPicked.get(i).getColor().parseToString(tilesPicked.get(i).getColor()) + " tile" +Color.RESET);
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
        System.out.println("1) View common goals.");
        System.out.println("2) View my personal goal.");
        System.out.println("3) View other players' shelves.");
        System.out.println("4) View common goals.");
    }

    public void askChoiceBeginTurn() {
        showChoicesBeginTurn();
        int choice = Integer.parseInt(readLine());
        //notifyObserver
    }

    public boolean checkValidIP(String IP) {
        boolean notValid = (IP == null || IP.length() < 7 || IP.length() > 15);
        if (notValid) {
            return false;
        } else {
            return IP.matches("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        }
    }


}
