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
       parser = new QuestParser("res");
   }

   /**
    * Greedy Effective solution for the level 1 of sae202
    * @param scenarioId The scenario id
    * @return The solves object obtain by the greedy effective solution
    * @throws ScenarioNotFoundException
    * @throws QuestParseException
   */
   public Solves solutionEfficaceGloutonne(int scenarioId) throws ScenarioNotFoundException, QuestParseException
   {
        Scenario scenario = parser.parseScenario(scenarioId);
        Quest currentQuest = Algorithms.nearestFirstQuest(scenario);
    
        while(currentQuest.getQuestId() != 0)
        {
            player.movePlayer(currentQuest.getQuestPos());
            player.addFinishedQuest(currentQuest);
            currentQuest = Algorithms.nearestNextQuest(scenario, player.getFinishedQuests(), this.player);
        }

        player.movePlayer(currentQuest.getQuestPos());
        player.addFinishedQuest(currentQuest);

        Solves gameSolve = new Solves(
            (ArrayList<Integer>)player.getFinishedQuests().stream().map(Quest::getQuestId).collect(Collectors.toList()),
            player.getPlayerTime(),
            player.getPlayerXp(),
            player.getFinishedQuests().size()
        );

        this.player.resetPlayer();

        return gameSolve;
    }
}
