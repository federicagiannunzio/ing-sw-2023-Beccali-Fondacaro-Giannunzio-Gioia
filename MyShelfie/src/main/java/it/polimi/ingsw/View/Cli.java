package it.polimi.ingsw.View;

import it.polimi.ingsw.Observer.Observable;

import java.util.concurrent.ExecutionException;

import static java.lang.System.out;

public class Cli extends Observable implements View {


    public void start() {
        System.out.println("Welcome to MyShelfie!");

        try {
            askServerInfo();
        } catch (ExecutionException e) {

        }
    }

    public void askServerInfo() {
        boolean valid = false;

        while (!valid) {
            System.out.println("Please enter the server address: ");
            String address;
            //leggi da terminale input utente e salva nella variabile address
        }
    }

    public void askUsername() {
        out.print("Please enter your username: ");
        try {
            String username;
            //notifyObserver();
        } catch() {
        }
    }

    @Override
    public void askPlayersNumber() {

    }

    @Override
    public void askFirstPLayer() {

    }

    @Override
    public void askTilesNumber() {

        out.print("Choose how many tiles you want to take");

    }

    @Override
    public void askTilePositionOnBoard(int numberOfTiles) {

        out.print("Select which tile you want to take");
        try {
            for(int i=0; i < numberOfTiles; i++) {
                out.print("Tile number " + (i+1));
                int row; //salva qui la riga
            }

        } catch {

        }

    }

    @Override
    public void askShelfieColumn() {

    }

    @Override
    public void showBoard() {

    }

    @Override
    public void showMyShelfie() {

    }

    @Override
    public void showOtherPlayersShelfie() {

    }

    @Override
    public void showMyPersonalGoal() {

    }

    @Override
    public void showCommonGoals() {

    }

    @Override
    public void showMyPoints() {

    }

    @Override
    public void showCommonGoalCompletion() {

    }

    @Override
    public void showWinMessage(String winner) {

    }

    @Override
    public void showLoginResult() {

    }


    /**
     * Prints
     */
    public void showGenericMessage(String messageToSend) {
        out.println(messageToSend);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void showConnectionMessage(String connectedUser) {

    }

    @Override
    public void showDisconnectionMessage(String disconnectedUser) {

    }


}
