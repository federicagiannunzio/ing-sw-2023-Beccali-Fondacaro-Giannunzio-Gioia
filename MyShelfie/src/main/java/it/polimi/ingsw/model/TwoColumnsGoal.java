package it.polimi.ingsw.model;
public class TwoColumnsGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];
    public boolean commonGoalAlg(Shelfie s) {
        int i, j, k, counter = 0;
        boolean flag = true;
        structure = s.getStructure();
        for(i = 0; i < 5; i++){
            for(j = 0; j < 6 && flag; j++){
                for(k = j; k < 6 && flag; k++){
                    if(structure[j][i].getColor() == structure[k][i].getColor())
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
