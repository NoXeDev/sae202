package fr.sae202;

import fr.sae202.Core.Game;
import fr.sae202.Core.QuestParser;
import fr.sae202.Exceptions.QuestParseException;
import fr.sae202.Exceptions.ScenarioNotFoundException;
import fr.sae202.Models.Solves;
public class App {
    public static void main(String[] args) {
        Game mainGame = new Game();
        try {
            QuestParser parser = new QuestParser("res");
            Solves algoSolve = mainGame.solutionEfficaceGloutonne(parser.parseScenario(0));
            
            System.out.println(algoSolve.getSolveList());
            System.out.println("Durée total : " + algoSolve.getSolveDuration());
            System.out.println("XP total : " + algoSolve.getSolveXp());
            System.out.println("Nombre de quêtes résolues : " + algoSolve.getSolveQuestNumber());
            System.out.println("Distance totale parcourue : " + algoSolve.getSumDistancesTraveled());
        } catch (ScenarioNotFoundException | QuestParseException e) {
            e.printStackTrace();
        }
    }
}
