package fr.sae202;

import java.util.ArrayList;

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
            ArrayList<Solves> algoSolve = mainGame.bestDistancePath(parser.parseScenario(1), 100);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            
            for(Solves solve : algoSolve) {
                System.out.println("Speedrun solved in : " + duration + " milliseconds");
                System.out.println(solve.getSolveList());
                System.out.println("Durée total : " + solve.getSolveDuration());
                System.out.println("XP total : " + solve.getSolveXp());
                System.out.println("Nombre de quêtes résolues : " + solve.getSolveQuestNumber());
                System.out.println("Distance totale parcourue : " + solve.getSumDistancesTraveled());
            }
        } catch (ScenarioNotFoundException | QuestParseException e) {
            e.printStackTrace();
        }
    }
}
