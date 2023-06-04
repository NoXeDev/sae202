package fr.sae202.Core;

import java.util.ArrayList;
import java.util.stream.Collectors;

import fr.sae202.Models.Player;
import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;

public class Algorithms {

    /**
     * Find the nearest quest without preconditions
     * @param scenario The scenario to search in
     * @return The nearest quest
     */
    public static Quest nearestFirstQuest(Scenario scenario) {
        Quest nearestQuest = null;
        for(Quest quest : scenario.getQuestMap().values()) {
            if(quest.getQuestPrecondition().isEmpty())
            {
                if(nearestQuest == null)
                {
                    nearestQuest = quest;
                    continue;
                }
                if(quest.getQuestPos().lessThan(nearestQuest.getQuestPos()))
                {
                    nearestQuest = quest;
                }
            }
        }
        return nearestQuest;
       }
    
       /**
        * Find the nearest quest available with the actual finished quests
        * @param scenario The scenario to search in
        * @param finishedQuests Finished quests
        * @param player The player
        * @return The nearest quest
        */
       public static Quest nearestNextQuest(Scenario scenario, ArrayList<Quest> finishedQuests, Player player)
       {
        Quest nearestQuest = null;
        for(Quest quest : fetchAvailableQuests(scenario, finishedQuests, player))
        {
            if(nearestQuest == null)
            {
                nearestQuest = quest;
                continue;
            }
            if(player.questDistance(quest) < player.questDistance(nearestQuest))
            {
                nearestQuest = quest;
            }
        }
    
        return nearestQuest;
       }
    

       /**
        * Search for all available quests with the actual finished quests
        * @param scenario The scenario to search in
        * @param finishedQuests Finished quests
        * @param player The player
        * @return All available quests
        */
       public static ArrayList<Quest> fetchAvailableQuests(Scenario scenario, ArrayList<Quest> finishedQuests, Player player)
       {
        ArrayList<Integer> finishedQuestsById = finishedQuests.stream().map(Quest::getQuestId).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Quest> availableQuests = new ArrayList<>();
        for(Quest quest : scenario.getQuestMap().values())
        {
            if(!finishedQuests.contains(quest))
            {
                if(quest.getQuestPrecondition().isEmpty())
                {
                    availableQuests.add(quest);
                }
                else {
                    if(
                        (finishedQuestsById.contains(quest.getQuestPrecondition().getX().getX()) 
                        || finishedQuestsById.contains(quest.getQuestPrecondition().getX().getY())) 
                        && 
                        (
                            ((quest.getQuestPrecondition().getY() != null) 
                            && (finishedQuestsById.contains(quest.getQuestPrecondition().getY().getX()) 
                            || finishedQuestsById.contains(quest.getQuestPrecondition().getY().getY()))
                            || quest.getQuestPrecondition().getY() == null
                        )
                    )
                    )
                    {
                        if(quest.getQuestId() == 0 && (player.getPlayerXp() < quest.getQuestXp()))
                        {
                            continue; // Need to do an other quest for farm XP
                        }
                        availableQuests.add(quest);
                    }
                }
            }
        }
        return availableQuests;
       }
}
