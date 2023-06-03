package fr.sae202.Core;

import java.util.ArrayList;
import java.util.stream.Collectors;

import fr.sae202.Exceptions.QuestParseException;
import fr.sae202.Exceptions.ScenarioNotFoundException;
import fr.sae202.Models.Player;
import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;

public class Game {
   private Player player;
   QuestParser parser;

   public Game() {
       player = new Player();
       player.debugOff();
       parser = new QuestParser("res");
   }

   public void solutionEfficace()
   {
        try {
            for(Scenario scenario : parser.parseAllScenario().values())
            {
                Quest currentQuest = nearestFirstQuest(scenario);
            
                while(currentQuest.getQuestId() != 0)
                {
                    player.movePlayer(currentQuest.getQuestPos());
                    player.addFinishedQuest(currentQuest);
                    currentQuest = nearestNextQuest(scenario, player.getFinishedQuests());
                }

                player.movePlayer(currentQuest.getQuestPos());
                player.addFinishedQuest(currentQuest);

                System.out.println("Scénario : " + scenario.getScenarioId() + " =======================");
                System.out.println("Temps total : " + player.getPlayerTime());
                System.out.println("XP total : " + player.getPlayerXp());
                System.out.println("Total quêtes : " + player.getFinishedQuests().size());
                System.out.println(player.getFinishedQuests().stream().map(Quest::getQuestId).collect(Collectors.toCollection(ArrayList::new)));
                System.out.println("=====================================");

                this.player.resetPlayer();
            }
        } catch (ScenarioNotFoundException | QuestParseException e) {
            e.printStackTrace();
        }
   }

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

   public Quest nearestNextQuest(Scenario scenario, ArrayList<Quest> finishedQuests)
   {
    Quest nearestQuest = null;
    for(Quest quest : fetchAvailableQuests(scenario, finishedQuests))
    {
        if(nearestQuest == null)
        {
            nearestQuest = quest;
            continue;
        }
        if(this.player.questDistance(quest) < this.player.questDistance(nearestQuest))
        {
            nearestQuest = quest;
        }
    }

    return nearestQuest;
   }

   public ArrayList<Quest> fetchAvailableQuests(Scenario scenario, ArrayList<Quest> finishedQuests)
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
                    if(quest.getQuestId() == 0 && (this.player.getPlayerXp() < quest.getQuestXp()))
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
