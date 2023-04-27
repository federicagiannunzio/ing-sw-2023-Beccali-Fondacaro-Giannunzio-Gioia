package src.main.java.it.polimi.ingsw.model;

import src.main.java.it.polimi.ingsw.model.CommonGoal;
import src.main.java.it.polimi.ingsw.model.Shelfie;
import src.main.java.it.polimi.ingsw.model.Tile;

/**
 * Class stairsGoal
 * Five columns of increasing height or descending: starting from the first column
 * left or right, each successive column it must be formed by an extra tile.
 * Tiles can be of any type.
 * @author andreafondacaro
 */
public class StairsGoal implements CommonGoal {
    Tile[][] structure = new Tile[6][5];

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        if(structure[0][0] != null && structure[1][1] != null && structure[2][2] != null && structure[3][3] != null && structure[4][4] != null && structure[0][1] == null && structure[1][2] == null && structure[2][3] == null && structure[3][4] == null)
            return true;
        else if(structure[1][0] != null && structure[2][1] != null && structure[3][2] != null && structure[4][3] != null && structure[5][4] != null && structure[0][0] == null && structure[1][1] == null && structure[2][2] == null && structure[3][3] == null && structure[4][4] == null)
            return true;
        else if(structure[5][0] != null && structure[4][1] != null && structure[3][2] != null && structure[2][3] != null && structure[1][4] != null && structure[4][0] == null && structure[3][1] == null && structure[2][2] == null && structure[1][3] == null && structure[0][4] == null)
            return true;
        else if(structure[4][0] != null && structure[3][1] != null && structure[2][2] != null && structure[1][3] != null && structure[0][4] != null && structure[3][0] == null && structure[2][1] == null && structure[1][2] == null && structure[0][3] == null)
            return true;
        else
            return false;
    }
}
