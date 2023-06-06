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

            long startTime = System.nanoTime();
            Solves algoSolve = mainGame.speedrun(parser.parseScenario(0), 0);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            
            System.out.println("Speedrun solved in : " + duration + " milliseconds");
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
