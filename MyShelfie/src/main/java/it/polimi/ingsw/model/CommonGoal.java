package it.polimi.ingsw.model;


public interface CommonGoal {
    public CommonGoalsName name = null;

    boolean commonGoalAlg(Shelfie s);

    CommonGoalsName getGoalsName();

}
