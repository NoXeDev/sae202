package fr.sae202.Core;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;
import fr.sae202.Models.Solves;
import fr.sae202.Utils.Vector2;

public class GameTest {
    ArrayList<Quest> questList = new ArrayList<Quest>();
    ArrayList<Quest> questList2 = new ArrayList<Quest>();
    Scenario scenario_0;
    Scenario scenario_1;

    public GameTest()
    {
        // Hand parsed scenario_0.txt (need valids quest for algorithms tests)
        questList.addAll(Arrays.asList(
            new Quest(1, new Vector2<Integer>(4, 3), null, 2, 100, "explorer pic de Bhanborim"),
            new Quest(2, new Vector2<Integer>(3, 1), new Vector2<Vector2<Integer>>(new Vector2<Integer>(1, null), null), 1, 150, "dialoguer avec Kaela la chaman des esprits"),
            new Quest(3, new Vector2<Integer>(0, 4), new Vector2<Vector2<Integer>>(new Vector2<Integer>(2, null), null), 3, 200, "explorer palais de Ahehona"),
            new Quest(4, new Vector2<Integer>(3, 2), new Vector2<Vector2<Integer>>(new Vector2<Integer>(2, null), null), 6, 100, "vaincre Loup Géant"),
            new Quest(0, new Vector2<Integer>(1, 1), new Vector2<Vector2<Integer>>(new Vector2<Integer>(3, 4), null), 4, 350, "vaincre Araignée lunaire")
        ));
        scenario_0 = new Scenario(0, questList.stream().collect(Collectors.toMap(quest -> quest.getQuestId(), quest -> quest)));

        // Hand parsed scenario_1.txt (need valids quest for algorithms tests)
        questList2.addAll(Arrays.asList(
            new Quest(2, new Vector2<Integer>(2, 2), new Vector2<Vector2<Integer>>(new Vector2<Integer>(4, 1), null), 2, 100, "explorer tombeau de Reha Thalor"),
            new Quest(5, new Vector2<Integer>(4, 3), new Vector2<Vector2<Integer>>(new Vector2<Integer>(1, 4), new Vector2<Integer>(2, null)), 1, 150, "explorer jardin de Syhe Lenora"),
            new Quest(3, new Vector2<Integer>(1, 0), new Vector2<Vector2<Integer>>(new Vector2<Integer>(4, null), new Vector2<Integer>(1, 2)), 7, 100, "dialoguer avec Morrigan la déesse de la mort"),
            new Quest(1, new Vector2<Integer>(3, 1), null, 2, 50, "dialoguer avec Alaric le mage noir"),
            new Quest(4, new Vector2<Integer>(4, 0), null, 2, 100, "explorer porte de Ifha Ennore"),
            new Quest(0, new Vector2<Integer>(2, 3), new Vector2<Vector2<Integer>>(new Vector2<Integer>(3, null), null), 3, 400, "explorer collines de Kortorhm")
        ));
        scenario_1 = new Scenario(1, questList2.stream().collect(Collectors.toMap(quest -> quest.getQuestId(), quest -> quest)));
    }

    @Test
    @DisplayName("Greedy Effective solution test")
    public void solutionEfficaceGloutonneTest() 
    {
        Game game = new Game();
        Solves testSolves = game.solutionEfficaceGloutonne(scenario_0);
        
        assertTrue(testSolves.getSolveDuration() >= 27 || testSolves.getSolveDuration() <= 30); // Values provided by Mr Auger
        assertTrue(testSolves.getSolveXp() >= 350 || testSolves.getSolveXp() <= 450); // Values provided by Mr Auger
        assertTrue(testSolves.getSolveQuestNumber() == 4); // Values provided by Mr Auger
        assertTrue(testSolves.getSumDistancesTraveled() >= 14 || testSolves.getSumDistancesTraveled() <= 20); // Values provided by Mr Auger

    }


    @Test 
    @DisplayName("Greedy Exhaustive solution test")
    public void solutionExhaustiveGloutonneTest()
    {
        Game game = new Game();
        Solves testSolves = game.solutionExhaustiveGloutonne(scenario_0);

        assertTrue(testSolves.getSolveDuration() >= 36 || testSolves.getSolveDuration() <= 40); // Values provided by Mr Auger
        assertTrue(testSolves.getSolveXp() >= 550 || testSolves.getSolveXp() <= 550); // Values provided by Mr Auger
        assertTrue(testSolves.getSolveQuestNumber() == 5); // Values provided by Mr Auger
        assertTrue(testSolves.getSumDistancesTraveled() >= 20 || testSolves.getSumDistancesTraveled() <= 24); // Values provided by Mr Auger
    }

    @Test
    @DisplayName("Speedrun solution test")
    public void speedrunTest()
    {
        Game game = new Game();
        Solves testSolves = game.speedrun(scenario_1, 0);

        // known speedrun path
        assertTrue(testSolves.getSolveList().equals(new ArrayList<Integer>(Arrays.asList(1, 4, 3, 2, 5, 0))));
    }

    @Test
    @DisplayName("Best Speedruns solution test")
    public void bestSpeedrunsTest()
    {
        Game game = new Game();
        ArrayList<Solves> testSolves = game.bestSpeedruns(scenario_1, 0, false, false);

        // known speedrun path
        assertTrue(testSolves.get(0).getSolveList().equals(new ArrayList<Integer>(Arrays.asList(1, 4, 3, 2, 5, 0))));
    }

    @Test
    @DisplayName("Speedrun solution test")
    public void bestNBQuestsTest()
    {
        Game game = new Game();
        ArrayList<Solves> testSolves = game.bestNBQuests(scenario_1, 0, false, false);

        // known speedrun path
        assertTrue(testSolves.get(0).getSolveList().equals(new ArrayList<Integer>(Arrays.asList(4, 2, 3, 5, 0))));
    }

    @Test
    @DisplayName("Speedrun solution test")
    public void bestDistancePathTest()
    {
        Game game = new Game();
        ArrayList<Solves> testSolves = game.bestDistancePath(scenario_1, 0, false, false);

        // known speedrun path
        assertTrue(testSolves.get(0).getSolveList().equals(new ArrayList<Integer>(Arrays.asList(1, 4, 3, 2, 5, 0))));
    }
}
