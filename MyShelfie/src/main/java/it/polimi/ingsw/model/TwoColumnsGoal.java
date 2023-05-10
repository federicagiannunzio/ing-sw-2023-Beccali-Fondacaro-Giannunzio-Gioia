package it.polimi.ingsw.model;

import static it.polimi.ingsw.model.CommonGoalsName.TWOCOLUMNS;

/**
 * Class twoColumnsGoal
 * Two columns formed each from 6 different types of tiles.
 * @author andreafondacaro
 */
public class TwoColumnsGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        int i, j, k, counter = 0;
        boolean flag = true;
        structure = s.getStructure();
        for(i = 0; i < 5; i++){
            for(j = 0; j < 6 && flag; j++){
                if(structure[j][i] == null)
                    flag = false;
                for(k = j+1; k < 6 && flag; k++){
                    if(structure[k][i] == null)
                        flag = false;
                    if(flag && structure[j][i].getColor() == structure[k][i].getColor())
                        flag = false;
                }
            }
            if(flag)
                counter++;

            else
                flag = true;
        }
        if(counter == 2)
            return true;
        else
            return false;
    }

    public CommonGoalsName getGoalsName(){ return TWOCOLUMNS;}
}
