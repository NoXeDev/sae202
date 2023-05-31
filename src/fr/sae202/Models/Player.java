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

    public Player(){
        /**
         * Create a new player
         * @param parScenario the scenario the player is playing
         */
        pFinishedQuests = new ArrayList<Quest>();
        pXp = 0;
        pPos = new Vector2<Integer>(0, 0);
        pTime = 0;
    }

    public void movePlayer(Vector2<Integer> parNewPos){
        /**
         * Move the player to a new position and increments the time with each change of position
         * @param parNewPos the new position
         */
        System.out.println("Se déplacer en "+parNewPos.toString());

        while (pPos.getX() != parNewPos.getX()){
            if (pPos.getX() < parNewPos.getX()){
                pPos.setX(pPos.getX() + 1);
            }
            else{
                pPos.setX(pPos.getX() - 1);
            }
            pTime++;
        }
        while (pPos.getY() != parNewPos.getY()){
            if (pPos.getY() < parNewPos.getY()){
                pPos.setY(pPos.getY() + 1);
            }
            else{
                pPos.setY(pPos.getY() - 1);
            }
            pTime++;
        }
    }

    public int questDistance(Quest parQuest){
        /**
         * Calculate the distance between the player and a quest
         * @param parQuest the quest to calculate the distance
         * @return the distance between the player and the quest
         */
        return Math.abs(pPos.getX() - parQuest.qPos.getX()) + Math.abs(pPos.getY() - parQuest.qPos.getY());
    }

    public Map<Quest,Integer> allScenarioDistance(Scenario parScenario){
        /**
         * Calculate the distance between the player and all the quest of a scenario when the player has not finished the quest
         * @param parScenario the scenario to calculate the distance
         * @return a map with the quest as key and the distance as value
         */
        Map<Quest,Integer> locMap = new HashMap<Quest,Integer>();
        for (Integer idQuest : parScenario.questMap.keySet()){
            Quest currentQuest = parScenario.questMap.get(idQuest);
            if (!pFinishedQuests.contains(currentQuest)) {
                locMap.put(currentQuest, questDistance(currentQuest));
            }
        }
        return locMap;
    }

    public void addXp(int parXp){
        /**
         * Add xp to the player
         * @param parXp the xp to add
         */
        pXp += parXp;
    }

    public void addTime(int parTime){
        /**
         * Add time to the player
         * @param parTime the time to add
         */
        pTime += parTime;
    }

    public void addFinishedQuest(Quest parQuest){
        /**
         * Add a quest to the finished quest list and add xp and time corresponding to the quest
         * @param parQuest the quest to add
         */
        pFinishedQuests.add(parQuest);
        addXp(parQuest.qXp);
        addTime(parQuest.qDuration);

        System.out.println(parQuest.qTitle+" terminée");
    }

    public ArrayList<Quest> getFinishedQuests(){
        /**
         * @return the finished quest list
         */
        return pFinishedQuests;
    }
}
