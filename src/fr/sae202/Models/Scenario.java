package fr.sae202.Models;

import java.util.Map;

public class Scenario {
    private int sId;
    private Map<Integer, Quest> questMap;

    public Scenario(int id, Map<Integer, Quest> quests)
    {
        this.sId = id;
        this.questMap = quests;
    }

    public String toString()
    {
        String tmp = this.sId+" ===================================== \n";

        for(Quest current : questMap.values())
        {
            tmp+= current.toString();
            tmp+="\n";
        }
        tmp+="===================================== \n";

        return tmp;
    }

    public int getScenarioId()
    {
        return this.sId;
    }

    public Map<Integer, Quest> getQuestMap()
    {
        return this.questMap;
    }
}
