package it.polimi.ingsw.model;
public class FourGroupsGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];

    /**
     * checks if there are four separated groups in which there are four adjacent tiles of the same type
     *
     * @param s is the Shelf of the player that will be checked
     */
    public boolean commonGoalAlg(Shelfie s){
        int[] arrayPoints = new int[5];
        arrayPoints = s.adjacentItem();
        if(arrayPoints[1] + arrayPoints[2] + arrayPoints[3] >= 4)
            return true;
        else
            return false;

    }
}
