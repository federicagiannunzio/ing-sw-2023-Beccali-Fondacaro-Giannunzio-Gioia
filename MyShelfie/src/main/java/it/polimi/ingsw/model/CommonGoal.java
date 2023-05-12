package it.polimi.ingsw.model;


import java.io.Serializable;

public interface CommonGoal extends Serializable {
    public CommonGoalsName name = null;

    boolean commonGoalAlg(Shelfie s);

    CommonGoalsName getGoalsName();

}
