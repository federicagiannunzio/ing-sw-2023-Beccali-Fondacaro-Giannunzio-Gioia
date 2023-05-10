package it.polimi.ingsw.model;

import static it.polimi.ingsw.model.CommonGoalsName.FOURGROUPS;

/**
 * Class fourGroupGoal
 * Four separate groups formed each from four adjacent tiles of the same color.
 * The tiles of a group can be different from those of another group.
 * @author andreafondacaro
 */
public class FourGroupsGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s){
        int[] arrayPoints = new int[5];
        arrayPoints = s.adjacentItem();
        if(arrayPoints[2] + arrayPoints[3] + arrayPoints[4] >= 4)
            return true;
        else
            return false;

    }

    public CommonGoalsName getGoalsName(){ return FOURGROUPS;}
}

