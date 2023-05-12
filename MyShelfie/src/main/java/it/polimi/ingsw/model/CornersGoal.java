package it.polimi.ingsw.model;

import java.io.Serializable;

import static it.polimi.ingsw.model.CommonGoalsName.CORNERS;

/**
 * Class cornersGoal
 * Four tiles of the same type at the four corners of the Library.
 * @author andreafondacaro
 */
public class CornersGoal implements CommonGoal, Serializable {
    Tile[][] structure = new Tile[6][5];

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        if(structure[0][0] != null && structure[0][4] != null && structure[5][0] != null && structure[5][4] != null &&
                structure[0][0].getColor() == structure[0][4].getColor() && structure[0][4].getColor() == structure[5][0].getColor() && structure[5][0].getColor() == structure[5][4].getColor())
            return true;
        else
            return false;
    }

    public CommonGoalsName getGoalsName(){ return CORNERS;}
}

