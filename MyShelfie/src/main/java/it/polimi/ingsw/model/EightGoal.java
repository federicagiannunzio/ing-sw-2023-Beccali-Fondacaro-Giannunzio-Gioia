package it.polimi.ingsw.model;

import static it.polimi.ingsw.model.CommonGoalsName.EIGHT;

/**
 * Class eightGoal
 * Eight tiles of the same type. There are restrictions on the location of these tiles.
 * @author andreafondacaro
 */
public class EightGoal implements CommonGoal {
    Tile[][] structure = new Tile[6][5];
    private int yellow;
    private int white;
    private int green;
    private int blue;
    private int cyan;
    private int purple;

    /**
     * @param s shelfie where to check the goal
     * @return true if the goal is verified, otherwise false
     */
    public boolean commonGoalAlg(Shelfie s) {
        yellow = 0;
        white = 0;
        green = 0;
        blue = 0;
        cyan = 0;
        purple = 0;
        structure = s.getStructure();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++) {
                if (structure[i][j] != null) {
                    if (structure[i][j].getColor() == Color.YELLOW) {
                        yellow++;
                        if (yellow == 8)
                            return true;
                    } else if (structure[i][j].getColor() == Color.WHITE) {
                        white++;
                        if (white == 8)
                            return true;
                    } else if (structure[i][j].getColor() == Color.GREEN) {
                        green++;
                        if (green == 8)
                            return true;
                    } else if (structure[i][j].getColor() == Color.BLUE) {
                        blue++;
                        if (blue == 8)
                            return true;
                    } else if (structure[i][j].getColor() == Color.CYAN) {
                        cyan++;
                        if (cyan == 8)
                            return true;
                    } else if (structure[i][j].getColor() == Color.PURPLE) {
                        purple++;
                        if (purple == 8)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public CommonGoalsName getGoalsName(){ return EIGHT;}
}
