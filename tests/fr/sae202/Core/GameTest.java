package fr.sae202.Core;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.sae202.Exceptions.QuestParseException;
import fr.sae202.Exceptions.ScenarioNotFoundException;
import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;
import fr.sae202.Models.Solves;
import fr.sae202.Utils.Vector2;

public class GameTest {
    ArrayList<Quest> questList = new ArrayList<Quest>();
    Scenario scenario_0;

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
    }

    @Test
    @DisplayName("Greedy Effective solution test")
    public void solutionEfficaceGloutonneTest() 
    {
        Game game = new Game();
        try {
            Solves testSolves = game.solutionEfficaceGloutonne(scenario_0);
            
            assertTrue(testSolves.getSolveDuration() >= 27 || testSolves.getSolveDuration() <= 30); // Values provided by Mr Auger
            assertTrue(testSolves.getSolveXp() >= 350 || testSolves.getSolveXp() <= 450); // Values provided by Mr Auger
            assertTrue(testSolves.getSolveQuestNumber() == 4); // Values provided by Mr Auger
            assertTrue(testSolves.getSumDistancesTraveled() >= 14 || testSolves.getSumDistancesTraveled() <= 20); // Values provided by Mr Auger

        } catch (ScenarioNotFoundException | QuestParseException e) {
            fail("Game throw an error", e);
        }
    }
}
