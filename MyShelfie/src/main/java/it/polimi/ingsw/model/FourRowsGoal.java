package it.polimi.ingsw.model;
public class FourRowsGoal implements CommonGoal{
    Tile[][] structure = new Tile[6][5];

    /**
     * checks if there are four rows made up of six tiles of one, two, three different types
     *
     * @param s is the Shelf of the player that will be checked
     */
    public boolean commonGoalAlg(Shelfie s) {
        structure = s.getStructure();
        int i, j, k, colorCounter, rowsCounter = 0;
        boolean flag = true, flag2 = true;
        for(i = 0; i < 6; i++){
            colorCounter = 0;
            for(j = 0; j < 5;j++){
                if(structure[i][j] == null) {
                    flag = false;
                    flag2 = false;
                }
                for(k = 0; k < j && flag;k++){
                    if(structure[i][k] == null)
                        flag = false;
                    if(structure[i][j].getColor() == structure[i][k].getColor())
                        flag = false;
                }
                if(flag){
                    System.out.println(i + " "+ j);
                    colorCounter++;
                }
                else
                    flag = true;
            }
            if(colorCounter <= 3 && flag2){
                System.out.println(i);
                rowsCounter++;}
            else
                flag2 = true;
        }
        if(rowsCounter == 4)
            return true;
        else
            return false;
    }
}
