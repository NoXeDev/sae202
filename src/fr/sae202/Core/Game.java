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
     * Effective solution that return the best path for speedrun scenario
     * @param scenario The scenario id
     * @return The solves object obtain by the speedrun solution
     */
    public Solves speedrun(Scenario scenario, int nSolutions)
    {
        return Algorithms.findFastestPath(scenario, Algorithms.findAllPaths(scenario, nSolutions)).get(0);
    }

    /**
     * Return the best speedruns for a scenario
     * @param scenario The scenario id
     * @param nSolutions The number of solutions to find
     * @return The list of the best speedruns
     */
    public ArrayList<Solves> bestSpeedruns(Scenario scenario, int nSolutions)
    {
        return Algorithms.findFastestPath(scenario, Algorithms.findAllPaths(scenario, nSolutions));
    }
}
