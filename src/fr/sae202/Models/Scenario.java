package fr.sae202.Models;

import java.util.Map;

public class Scenario {
    private int sId;
    private Map<Integer, Quest> questMap;

    /**
     * Constructor of the Scenario class.
     * @param id the id of the scenario
     * @param quests the map of quests
     */
    public Scenario(int id, Map<Integer, Quest> quests)
    {
        this.sId = id;
        this.questMap = quests;
    }

    /**
     * toString method of the Scenario class.
     * @return a string containing the scenario's id and all the quests
     */
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

    /**
     * Get the id of the scenario.
     * @return the id of the scenario
     */
    public int getScenarioId()
    {
        return this.sId;
    }

    /**
     * Get the map of quests.
     * @return the map of quests
     */
    public Map<Integer, Quest> getQuestMap()
    {
        return this.questMap;
    }
}
