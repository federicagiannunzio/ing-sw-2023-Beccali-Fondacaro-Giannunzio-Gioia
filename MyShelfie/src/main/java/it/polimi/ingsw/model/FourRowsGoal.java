public class FourRowsGoal implements CommonGoal{
    Tile[][] structure = new Tile[5][4];
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        int i, j, k, colorCounter, rowsCounter = 0;
        boolean flag = true;
        for(i = 0; i < 6; i++){
            colorCounter = 0;
            for(j = 0; j < 5;j++){
                for(k = 0; k < 5 && flag;k++){
                    if(structure[i][j].getColor() == structure[i][k].getColor())
                        flag = false;
                }
                if(flag)
                    colorCounter++;
                else
                    flag = true;
            }
            if(colorCounter <= 2)
                rowsCounter++;
        }
        if(rowsCounter == 4)
            return true;
        else
            return false;
    }
}
