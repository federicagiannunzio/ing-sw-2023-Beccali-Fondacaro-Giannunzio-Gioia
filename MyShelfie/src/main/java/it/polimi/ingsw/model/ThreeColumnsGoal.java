package it.polimi.ingsw.model;

import static it.polimi.ingsw.model.CommonGoalsName.THREE;

/**
 * Class threeColumnsGoal
 * Three columns each formed by 6 tiles of one, two or three different types. Different column
 * may have different combinations of tile types.
 * @author andreafondacaro
 */
public class ThreeColumnsGoal implements CommonGoal {
    Tile[][] structure = new Tile[6][5];

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        int i, j, k, columnsCounter = 0, colorCounter;
        boolean flag = true, flag2 = true;
        for (i = 0; i < 5; i++) {
            colorCounter = 0;
            for (j = 0; j < 6; j++) {
                if(structure[j][i] == null) {
                    flag = false;
                    flag2 = false;
                }
                for (k = 0; k < j && flag; k++) {
                    if(structure[k][i] == null)
                        flag = false;
                    if(structure[j][i].getColor() == structure[k][i].getColor())
                        flag = false;
                }
                if(flag)
                    colorCounter++;
                else
                    flag = true;
            }
            if(colorCounter <= 3 && flag2)
                columnsCounter++;
            else
                flag2 = true;
        }
        if(columnsCounter == 3)
            return true;
        else
            return false;
    }

    public CommonGoalsName getGoalsName(){ return THREE;}
}
