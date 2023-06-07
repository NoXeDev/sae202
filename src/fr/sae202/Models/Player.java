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
    private boolean printDebug = true;
    private Vector2<Integer> pOldPos;

    /**
     * Create a new player
     */
    public Player(){
        pFinishedQuests = new ArrayList<>();
        pXp = 0;
        pPos = new Vector2<>(0, 0);
        pTime = 0;
        pOldPos = new Vector2<>(0, 0);
    }

    /**
     * Move the player to a new position and increments the time with each change of position
     * @param newPosition the new position
     */
    public void movePlayer(Vector2<Integer> newPosition){
        if (printDebug)
            System.out.println("Se déplacer en "+newPosition.toString());
            
        pTime += Math.abs(this.pPos.getX() - newPosition.getX()) + Math.abs(this.pPos.getY() - newPosition.getY());
        if(this.pOldPos != new Vector2<Integer>(0, 0))
            this.pOldPos = this.pPos;
        this.pPos = newPosition;
    }

    /**
     * Calculate the distance between the player and a quest
     * @param nQuest the quest to calculate the distance
     * @return the distance between the player and the quest
     */
    public int questDistance(Quest nQuest){
        return Math.abs(pPos.getX() - nQuest.getQuestPos().getX()) + Math.abs(pPos.getY() - nQuest.getQuestPos().getY());
    }

    /**
     * Calculate the distance between the player and all the quest of a scenario when the player has not finished the quest
     * @param scenario the scenario to calculate the distance
     * @return a map with the quest as key and the distance as value
     */
    public Map<Quest,Integer> allScenarioDistance(Scenario scenario){
        Map<Quest,Integer> locMap = new HashMap<Quest,Integer>();
        for (Integer idQuest : scenario.getQuestMap().keySet()){
            Quest currentQuest = scenario.getQuestMap().get(idQuest);
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
        if(quest.getQuestId() != 0)
        {
            addXp(quest.getQuestXp());
        }
        addTime(quest.getQuestDuration());

        if(printDebug)
            System.out.println(quest.getQuestTitle()+" terminée");
    }

    /**
     * @return the finished quest list
     */
    public ArrayList<Quest> getFinishedQuests(){
        return pFinishedQuests;
    }

    /**
     * get the current player position
     * @return the current player position
     */
    public Vector2<Integer> getPlayerPos() {
        return pPos;
    }

    public int getPlayerTime() {
        return pTime;
    }

    public int getPlayerXp() {
        return pXp;
    }

    /**
     * Reset the player vars
     */
    public void resetPlayer()
    {
        pFinishedQuests.clear();
        pXp = 0;
        pPos = new Vector2<>(0, 0);
        pTime = 0;
    }

    /**
     * Disable the print of all the players movements and other actions
     */
    public void debugOff()
    {
        printDebug = false;
    }

    /**
     * Enable the print of all the players movements and other actions
     */
    public void debugOn()
    {
        printDebug = true;
    }


    public int sumDistancesTraveled()
    {
        int sum = this.pTime;
        for(Quest quest : this.pFinishedQuests)
        {
            sum -= quest.getQuestDuration();
        }

        return sum;
    }

    /**
     * Rollback the last quest (useful for dfs algorithm)
     */
    public void rollBackQuest()
    {
        if(pFinishedQuests.size() == 0)
            return;
        Quest lastQuest = pFinishedQuests.get(pFinishedQuests.size() - 1);
        if(lastQuest.getQuestId() != 0)
            pXp -= lastQuest.getQuestXp();

        pTime -= lastQuest.getQuestDuration();
        pFinishedQuests.remove(lastQuest);
        pPos = pOldPos;
    }
}
