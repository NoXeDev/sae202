package fr.sae202.Models;

import fr.sae202.Utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Player {
    private ArrayList<Quest> pFinishedQuests;
    private int pXp;
    private Vector2<Integer> pPos;
    private int pTime;

    /**
     * Create a new player
     */
    public Player(){
        pFinishedQuests = new ArrayList<>();
        pXp = 0;
        pPos = new Vector2<>(0, 0);
        pTime = 0;
    }

    /**
     * Move the player to a new position and increments the time with each change of position
     * @param newPosition the new position
     */
    public void movePlayer(Vector2<Integer> newPosition){
        System.out.println("Se déplacer en "+newPosition.toString());

        this.pPos = newPosition;
        pTime += newPosition.getX() + newPosition.getY();
    }

    /**
     * Calculate the distance between the player and a quest
     * @param nQuest the quest to calculate the distance
     * @return the distance between the player and the quest
     */
    public int questDistance(Quest nQuest){
        return Math.abs(pPos.getX() - nQuest.qPos.getX()) + Math.abs(pPos.getY() - nQuest.qPos.getY());
    }

    /**
     * Calculate the distance between the player and all the quest of a scenario when the player has not finished the quest
     * @param scenario the scenario to calculate the distance
     * @return a map with the quest as key and the distance as value
     */
    public Map<Quest,Integer> allScenarioDistance(Scenario scenario){
        Map<Quest,Integer> locMap = new HashMap<Quest,Integer>();
        for (Integer idQuest : scenario.questMap.keySet()){
            Quest currentQuest = scenario.questMap.get(idQuest);
            if (!pFinishedQuests.contains(currentQuest)) {
                locMap.put(currentQuest, questDistance(currentQuest));
            }
        }
        return locMap;
    }

    /**
     * Add xp to the player
     * @param xp the xp to add
     */
    public void addXp(int xp){
        pXp += xp;
    }

    /**
     * Add time to the player
     * @param time the time to add
     */
    public void addTime(int time){
        pTime += time;
    }

    /**
     * Add a quest to the finished quest list and add xp and time corresponding to the quest
     * @param quest the quest to add
     */
    public void addFinishedQuest(Quest quest){
        pFinishedQuests.add(quest);
        addXp(quest.qXp);
        addTime(quest.qDuration);

        System.out.println(quest.qTitle+" terminée");
    }

    /**
     * @return the finished quest list
     */
    public ArrayList<Quest> getFinishedQuests(){
        return pFinishedQuests;
    }
}
