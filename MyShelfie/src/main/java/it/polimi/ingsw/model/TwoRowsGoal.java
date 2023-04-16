public class TwoRowsGoal implements CommonGoal{
    Tile[][] structure = new Tile[5][4];
    public boolean commonGoalAlg(Shelfie s) {
        int i, j, k, counter = 0;
        boolean flag = true;
        structure = s.getStructure();
        for(i = 0; i < 6; i++){
            for(j = 0; j < 5 && flag; j++){
                for(k = j; k < 5 && flag; k++){
                    if(structure[i][j].getColor() == structure[i][k].getColor())
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
