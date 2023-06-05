package fr.sae202;

import fr.sae202.Core.Algorithms;
import fr.sae202.Core.Game;
import fr.sae202.Core.QuestParser;
import fr.sae202.Exceptions.QuestParseException;
import fr.sae202.Exceptions.ScenarioNotFoundException;
import fr.sae202.Models.Scenario;
public class App {
    public static void main(String[] args) {
        Game mainGame = new Game();
        try {
            QuestParser parser = new QuestParser("res");
            /*for(Scenario scenario : parser.parseAllScenario().values())
            {
                Solves algoSolve = mainGame.solutionExhaustiveGloutonne(scenario);
            
                System.out.println(algoSolve.getSolveList());
                System.out.println("Durée total : " + algoSolve.getSolveDuration());
                System.out.println("XP total : " + algoSolve.getSolveXp());
                System.out.println("Nombre de quêtes résolues : " + algoSolve.getSolveQuestNumber());
                System.out.println("Distance totale parcourue : " + algoSolve.getSumDistancesTraveled());
            }*/

            Scenario scenario = parser.parseScenario(1);
            System.out.println(Algorithms.findFastestPath(scenario, Algorithms.findAllPaths(scenario)));
        } catch (ScenarioNotFoundException | QuestParseException e) {
            e.printStackTrace();
        }
    }
}
