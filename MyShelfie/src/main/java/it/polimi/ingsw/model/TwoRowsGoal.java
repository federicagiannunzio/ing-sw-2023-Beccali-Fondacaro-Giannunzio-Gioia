

package src.main.java.it.polimi.ingsw.model;

/**
 * Class twoRowsGoal
 * Two lines formed each from 5 different types of tiles.
 * @author andreafondacaro
 */
public class TwoRowsGoal implements CommonGoal {
    Tile[][] structure = new Tile[6][5];

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        int i, j, k, counter = 0;
        boolean flag = true;
        structure = s.getStructure();
        for(i = 0; i < 6; i++){
            for(j = 0; j < 5 && flag; j++){
                if(structure[i][j] == null)
                    flag = false;
                for(k = j+1; k < 5 && flag; k++){
                    if(structure[i][k] == null)
                        flag = false;
                    if(flag && structure[i][j].getColor() == structure[i][k].getColor())
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
}
