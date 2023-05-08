package fr.sae202.Models;

import java.util.Map;

public class Scenario {
    public int id;
    public Map<Integer, Quest> questMap;

    public Scenario(int id, Map<Integer, Quest> quests)
    {
        this.id = id;
        this.questMap = quests;
    }

    public String toString()
    {
        String tmp = this.id+" ===================================== \n";

        for(Quest current : questMap.values())
        {
            tmp+= current.toString();
            tmp+="\n";
        }
        tmp+="===================================== \n";

        return tmp;
    }
}
