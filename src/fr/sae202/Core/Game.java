package fr.sae202.Core;

import java.util.ArrayList;
import java.util.stream.Collectors;

import fr.sae202.Exceptions.QuestParseException;
import fr.sae202.Exceptions.ScenarioNotFoundException;
import fr.sae202.Models.Player;
import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;
import fr.sae202.Models.Solves;

public class Game {
   private Player player;
   QuestParser parser;

   public Game() {
       player = new Player();
       player.debugOff();
   }

   public Game(boolean printDebug) {
        player = new Player();
        if(printDebug) 
            player.debugOn();
        else
            player.debugOff();
    }

   /**
    * Greedy Effective solution for the level 1 of sae202
    * @param scenario The scenario id
    * @return The solves object obtain by the greedy effective solution
    * @throws ScenarioNotFoundException
    * @throws QuestParseException
   */
   public Solves solutionEfficaceGloutonne(Scenario scenario)
   {
        Quest currentQuest = Algorithms.nearestQuest(Algorithms.fetchAvailableQuests(scenario, player, true), player.getPlayerPos());
    
        while(currentQuest.getQuestId() != 0)
        {
            player.movePlayer(currentQuest.getQuestPos());
            player.addFinishedQuest(currentQuest);
            currentQuest = Algorithms.nearestQuest(Algorithms.fetchAvailableQuests(scenario, player, true), player.getPlayerPos());
        }

        player.movePlayer(currentQuest.getQuestPos());
        player.addFinishedQuest(currentQuest);

        Solves gameSolve = new Solves(
            (ArrayList<Integer>)player.getFinishedQuests().stream().map(Quest::getQuestId).collect(Collectors.toList()),
            player.getPlayerTime(),
            player.getPlayerXp(),
            player.getFinishedQuests().size(),
            player.sumDistancesTraveled()
        );

        this.player.resetPlayer();

        return gameSolve;
    }


    /**
     * Greedy Exhaustive solution for the level 1 of sae202
     * @param scenario The scenario id
     * @return The solves object obtain by the greedy exhaustive solution
     */
    public Solves solutionExhaustiveGloutonne(Scenario scenario)
    {
        ArrayList<Quest> availableQuests = Algorithms.fetchAvailableQuests(scenario, player, true);

        boolean gameLoop = true;
        while(gameLoop)
        {
            for(Quest quest : availableQuests)
            {
                if(quest.getQuestId() == 0 && availableQuests.size() == 1 && player.getFinishedQuests().size() == (scenario.getQuestMap().size() - 1))
                {
                    gameLoop = false;
                } else if(quest.getQuestId() == 0)
                {
                    continue;
                }
                player.movePlayer(quest.getQuestPos());
                player.addFinishedQuest(quest);
            }
            availableQuests = Algorithms.fetchAvailableQuests(scenario, player, true);
        }

        Solves gameSolve = new Solves(
            (ArrayList<Integer>)player.getFinishedQuests().stream().map(Quest::getQuestId).collect(Collectors.toList()),
            player.getPlayerTime(),
            player.getPlayerXp(),
            player.getFinishedQuests().size(),
            player.sumDistancesTraveled()
        );

        this.player.resetPlayer();

        return gameSolve;
    }


    /**
     * Effective solution for the level 2 of sae202 (Speedrun solution)
     * @param scenario The scenario id
     * @return The solves object obtain by the speedrun solution
     */
    public Solves speedrun(Scenario scenario, int nSolutions)
    {
        Player player = new Player();
        player.debugOff();
        ArrayList<ArrayList<Integer>> pathLists = Algorithms.findAllPaths(scenario, nSolutions);
        System.out.println("All paths found : " + pathLists.size());
        ArrayList<Integer> fastestPath = Algorithms.findFastestPath(scenario, pathLists);

        for(Integer questId : fastestPath)
        {
            player.movePlayer(scenario.getQuestMap().get(questId).getQuestPos());
            player.addFinishedQuest(scenario.getQuestMap().get(questId));
        }
        
        Solves gameSolve = new Solves(
            (ArrayList<Integer>)player.getFinishedQuests().stream().map(Quest::getQuestId).collect(Collectors.toList()),
            player.getPlayerTime(),
            player.getPlayerXp(),
            player.getFinishedQuests().size(),
            player.sumDistancesTraveled()
        );

        this.player.resetPlayer();

        return gameSolve;
    }
}
