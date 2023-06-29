package it.polimi.ingsw.model;
import java.io.Serializable;

/**
 * Interface for the common goals
 * */
public interface CommonGoal extends Serializable {
    CommonGoalsName name = null;

    boolean commonGoalAlg(Shelfie s);

    CommonGoalsName getGoalsName();

}
