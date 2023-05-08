package it.polimi.ingsw.View;

import it.polimi.ingsw.controller.Observer.Observable;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Shelfie;
import it.polimi.ingsw.model.Tile;

import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.System.out;

public class Cli extends Observable implements View {


    private Thread inputThread;


    public void start() {
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
    public String readLine() {

    }

    public void askServerInfo() {
        boolean valid = false;

        while (!valid) {
            System.out.println("Please enter the server address: ");
            String address = readLine();
            //leggi da terminale input utente e salva nella variabile address
        }
    }

    public void askUsername() {
        out.print("Please enter your username: ");
        try {
            String username = readLine();
            //notifyObserver();
        } catch() {
        }
    }

    @Override
    public void askPlayersNumber() {
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
    public void askTilesNumber() {
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
                int row; //salva qui la riga
            }

        } catch {

        }

    }

    @Override
    public void askShelfieColumn() {
        System.out.println("In which column you want to put your tiles?");
        int col;
        try {
            col = Integer.parseInt(readLine());
        } catch() {

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
    public void showBoard(Board board) {

        StringBuilder strRowBld = new StringBuilder();
        StringBuilder strColBld = new StringBuilder();

        strRowBld.append(Color.YELLOW_BOLD).append("\n   +-----+-----+-----+-----+-----+\n").append(Color.RESET);
        strColBld.append(Color.YELLOW_BOLD).append("   |").append(Color.RESET);
        //tile.append(Colori.GREEN).append(" x").append(Colori.RESET);

        for (int i = 0; i < 9; i++) {
            System.out.print(strRowBld);
            for (int j = 0; j < 9; j++) {
                System.out.print(strColBld);
                if (board.getTile(i, j) != null) {
                    StringBuilder tile = new StringBuilder();
                    tile.append(board.getTile(i, j).getColor()).append(" x").append(Color.RESET);
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
    public void showMyShelfie(Shelfie shelf) {

        StringBuilder strRowBld = new StringBuilder();
        StringBuilder strColBld = new StringBuilder();

        strRowBld.append(Color.YELLOW_BOLD).append("\n   +-----+-----+-----+-----+-----+\n").append(Color.RESET);
        strColBld.append(Color.YELLOW_BOLD).append("   |").append(Color.RESET);
        //tile.append(Colori.GREEN).append(" x").append(Colori.RESET);

        for (int i = 0; i < 6; i++) {
            System.out.print(strRowBld);
            for (int j = 0; j < 5; j++) {
                System.out.print(strColBld);
                if (shelf.getCell(i, j) != null) {
                    StringBuilder tile = new StringBuilder();
                    tile.append(shelf.getCell(i, j).getColor()).append(" x").append(Color.RESET);
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
    public void showOtherPlayersShelfie(Tile[][] shelf) {

    }

    @Override
    public void showMyPersonalGoal(Tile[][] personalGoalShelf) {

    }

    @Override
    public void showCommonGoals() {
        switch(commonGoalTitle) {

        }
    }

    @Override
    public void showMyPoints(int currentPoints) {
        System.out.println("ACTUAL SCORE: " + currentPoints);

    }

    @Override
    public void showCommonGoalCompletionWinner(String commonGoal, int pointsAchieved) {
        System.out.println("Congratulation! You have completed " + commonGoal + " common goal.");
        System.out.println("You have achieved " + pointsAchieved + " points.");
    }

    @Override
    public void showCommonGoalCompletionOthers(String username, String commonGoal, int pointsAchieved) {
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


    /**
     * Prints
     */
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
    public void showStartTurnMessage(String user) {
        System.out.println(user + " started his turn");
    }

    @Override
    public void showEndTurnMessage(String user) {
        System.out.println(user + " ended his turn");

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


}
