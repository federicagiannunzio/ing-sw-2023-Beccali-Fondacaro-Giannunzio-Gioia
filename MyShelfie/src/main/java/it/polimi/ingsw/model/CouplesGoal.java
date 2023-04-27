package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Shelfie;
import it.polimi.ingsw.model.Tile;

public class CouplesGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];
    private int counter;

    /**
     * checks if there are three columns made up of six tiles of one, two, three different types
     *
     * @param s is the Shelf of the player that will be checked
     */
    public boolean commonGoalAlg(Shelfie s) {
        int[] arrayPoints = new int[5];
        arrayPoints = s.adjacentItem();
        if(arrayPoints[1] + arrayPoints[2] + arrayPoints[3] + arrayPoints[0] + arrayPoints[5] >= 6)
            return true;
        else
            return false;
    }
}
