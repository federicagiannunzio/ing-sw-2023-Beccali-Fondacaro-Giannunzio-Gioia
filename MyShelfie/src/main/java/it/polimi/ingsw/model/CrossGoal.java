package it.polimi.ingsw.model;

import static it.polimi.ingsw.model.CommonGoalsName.CROSS;

/**
 * Class crossgoal
 * Five tiles of the same type forming an X.
 */
public class CrossGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        for(int i = 1; i < 5; i++){
            for(int j = 1; j < 4; j++){
                if(structure[i][j] != null && structure[i+1][j+1] != null && structure[i+1][j-1] != null && structure[i-1][j+1] != null &&
                        structure[i][j].getColor() == structure[i+1][j+1].getColor() && structure[i][j].getColor() == structure[i-1][j-1].getColor() && structure[i][j].getColor() == structure[i+1][j-1].getColor() && structure[i][j].getColor() == structure[i-1][j+1].getColor())
                    return true;
            }
        }
        return false;
    }

    public CommonGoalsName getGoalsName(){ return CROSS;}
}
