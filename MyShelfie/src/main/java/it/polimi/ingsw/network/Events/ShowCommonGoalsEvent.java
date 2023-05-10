package it.polimi.ingsw.network.Events;

import it.polimi.ingsw.model.CommonGoalsName;

public class ShowCommonGoalsEvent extends Event{
    private final CommonGoalsName name;
    public ShowCommonGoalsEvent(String user, CommonGoalsName name) {
        super(user, EventType.SHOWCOMMONGOALS);
        this.name = name;
    }

    public CommonGoalsName getName(){
        return name;
    }
}
