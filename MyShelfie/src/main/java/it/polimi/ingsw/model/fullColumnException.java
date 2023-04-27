package src.main.java.it.polimi.ingsw.model;

public class fullColumnException extends Exception {

    public void message() {
        System.out.println("This column is already full!");
    }

}
