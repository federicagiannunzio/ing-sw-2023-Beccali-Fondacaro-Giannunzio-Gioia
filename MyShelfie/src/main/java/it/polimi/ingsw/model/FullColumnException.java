package it.polimi.ingsw.model;

public class FullColumnException extends Exception {

    public void message() {
        System.out.println("This column is already full!");
    }

}
