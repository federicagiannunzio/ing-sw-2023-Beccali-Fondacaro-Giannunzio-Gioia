package it.polimi.ingsw.model;
public class FourGroupsGoal implements CommonGoal{
    Tile[][] structure = new Tile[5][4];
    public boolean commonGoalAlg(Shelfie s){
        int[] arrayPoints = new int[5];
        arrayPoints = s.adjacentItem();
        if(arrayPoints[1] + arrayPoints[2] + arrayPoints[3] >= 4)
            return true;
        else
            return false;

    }
}
