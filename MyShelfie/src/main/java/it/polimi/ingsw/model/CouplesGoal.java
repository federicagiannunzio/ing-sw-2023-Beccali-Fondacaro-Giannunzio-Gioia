package src.main.java.it.polimi.ingsw.model;

import src.main.java.it.polimi.ingsw.model.Shelfie;
import src.main.java.it.polimi.ingsw.model.Tile;

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
        if(arrayPoints[1] + arrayPoints[2] + arrayPoints[3] + arrayPoints[0] + arrayPoints[5] >= 6)
            return true;
        else
            return false;
    }
}
