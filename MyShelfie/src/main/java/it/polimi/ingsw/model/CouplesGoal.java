package it.polimi.ingsw.model;

import static it.polimi.ingsw.model.CommonGoalsName.COUPLES;

/**
 * Class coupleGaol
 * Six separate groups formed each from two adjacent tiles of the same type.
 * The tiles of a group can be different from those of another group.
 */
public class CouplesGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];
    private int counter;

    /**
     *
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        int[] arrayPoints = new int[5];
        arrayPoints = s.adjacentItem();
        if(arrayPoints[0] + arrayPoints[1] + arrayPoints[2] + arrayPoints[3] + arrayPoints[4] >= 6)
            return true;
        else
            return false;
    }

    public CommonGoalsName getGoalsName(){ return COUPLES;}
}
