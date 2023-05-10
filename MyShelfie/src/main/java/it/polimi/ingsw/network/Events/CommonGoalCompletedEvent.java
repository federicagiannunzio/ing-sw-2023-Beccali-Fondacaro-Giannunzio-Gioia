package it.polimi.ingsw.network.Events;

public class CommonGoalCompletedEvent extends Event{

    private final int pointsAchieved;
    private final String commonGoal;
    public CommonGoalCompletedEvent(String user, int pointsAchieved, String commonGoal) {
        super(user, EventType.COMMONGOAL_COMPLETED);
        this.pointsAchieved = pointsAchieved;
        this.commonGoal = commonGoal;
    }

    public int getPointsAchieved(){
        return pointsAchieved;
    }

    public String getCommonGoal(){
        return commonGoal;
    }
}
