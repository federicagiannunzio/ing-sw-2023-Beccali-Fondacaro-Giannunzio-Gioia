package it.polimi.ingsw.model;
public class ThreeColumnsGoal implements CommonGoal {
    Tile[][] structure = new Tile[6][5];

    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        int i, j, k, columnsCounter = 0, colorCounter;
        boolean flag = true;
        for (i = 0; i < 5; i++) {
            colorCounter = 0;
            for (j = 0; j < 6; j++) {
                for (k = 0; k < j && flag; k++) {
                    if(structure[j][i].getColor() == structure[k][i].getColor())
                        flag = false;
                }
                if(flag)
                    colorCounter++;
                else
                    flag = true;
            }
            if(colorCounter <= 3)
                columnsCounter++;
        }
        if(columnsCounter == 3)
            return true;
        else
            return false;
    }
}
