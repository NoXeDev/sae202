package fr.sae202.Core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import fr.sae202.Models.Player;
import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;
import fr.sae202.Models.Solves;
import fr.sae202.Utils.Vector2;

public class AlgorithmsTest {
    ArrayList<Quest> questList = new ArrayList<Quest>();
    Scenario scenario_0;

    public AlgorithmsTest()
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
    @DisplayName("Nearest Quest Test")
    @Order(1)
    public void nearestQuestTest()
    {
        Player player = new Player();
        player.debugOff();
        player.movePlayer(scenario_0.getQuestMap().get(1).getQuestPos());
        player.addFinishedQuest(scenario_0.getQuestMap().get(1));

        Quest nearestNextQuest = Algorithms.nearestQuest(Algorithms.fetchAvailableQuests(scenario_0, player, true), player.getPlayerPos());
        assertEquals(nearestNextQuest, scenario_0.getQuestMap().get(2)); // The nearest next quest is the quest id 2

        player.movePlayer(nearestNextQuest.getQuestPos());
        player.addFinishedQuest(nearestNextQuest);

        nearestNextQuest = Algorithms.nearestQuest(Algorithms.fetchAvailableQuests(scenario_0, player, true), player.getPlayerPos());
        assertEquals(nearestNextQuest, scenario_0.getQuestMap().get(4)); // The nearest next quest is the quest id 4
    }

    @Test
    @DisplayName("Fetch available quests Test")
    @Order(2)
    public void fetchAvailableQuestsTest()
    {
        Player player = new Player();
        player.movePlayer(scenario_0.getQuestMap().get(1).getQuestPos());
        player.addFinishedQuest(scenario_0.getQuestMap().get(1));
        Quest nearestNextQuest = Algorithms.nearestQuest(Algorithms.fetchAvailableQuests(scenario_0, player, true), player.getPlayerPos());
        player.movePlayer(nearestNextQuest.getQuestPos());
        player.addFinishedQuest(nearestNextQuest);

        ArrayList<Quest> availableQuests = Algorithms.fetchAvailableQuests(scenario_0, player, true);
        assertEquals(availableQuests, Arrays.asList(questList.get(2), questList.get(3))); // The player can do quest number 3 and 4
    }

    @Test
    @DisplayName("Find all paths Test")
    @Order(3)
    public void findAllPathsTest()
    {
        // Known and verified result
        ArrayList<ArrayList<Integer>> awaitedResult = new ArrayList<ArrayList<Integer>>();
        awaitedResult.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 0)));
        awaitedResult.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 0)));
        awaitedResult.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 0)));
        awaitedResult.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3, 0)));

        ArrayList<ArrayList<Integer>> result = Algorithms.findAllPaths(scenario_0, false);

        assertEquals(result, awaitedResult);
    }

    @Test
    @DisplayName("Find all paths exhaustive version Test")
    @Order(3)
    public void findAllPathsExhaustiveTest()
    {
        ArrayList<ArrayList<Integer>> result = Algorithms.findAllPaths(scenario_0, true);

        for(ArrayList<Integer> path : result)
        {
            assertEquals(path.size(), result.get(0).size()); // Check if all paths have the same size
        }
    }

    @Test
    @DisplayName("Find fastest path Test")
    @Order(4)
    public void findFastestPathTest()
    {
        ArrayList<ArrayList<Integer>> pathsTest = new ArrayList<ArrayList<Integer>>();
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3, 0)));


        ArrayList<Solves> results = Algorithms.fastestPath(scenario_0, pathsTest, 0, false);
        ArrayList<Solves> worthResults = Algorithms.fastestPath(scenario_0, pathsTest, 0, true);

        ArrayList<Integer> awaitedResult = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 0));
        ArrayList<Integer> awaitedWorthResult = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 0));

        assertEquals(results.get(0).getSolveList(), awaitedResult);
        assertEquals(worthResults.get(0).getSolveList(), awaitedWorthResult);
    }

    @Test
    @DisplayName("Find shortest path in number of quests Test")
    @Order(5)
    public void shortestNBQuestsPathTest()
    {
        ArrayList<ArrayList<Integer>> pathsTest = new ArrayList<ArrayList<Integer>>();
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3, 0)));

        ArrayList<Solves> results = Algorithms.shortestNBQuestsPath(scenario_0, pathsTest, 0, false);
        ArrayList<Solves> worthResults = Algorithms.shortestNBQuestsPath(scenario_0, pathsTest, 0, true);

        ArrayList<Integer> awaitedResult = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 0));
        ArrayList<Integer> awaitedWorthResult = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3, 0));

        assertEquals(results.get(0).getSolveList(), awaitedResult);
        assertEquals(worthResults.get(0).getSolveList(), awaitedWorthResult);
    }

    @Test
    @DisplayName("Find shortest path in distance of quests Test")
    @Order(6)
    public void shortestDistancePathTest()
    {
        ArrayList<ArrayList<Integer>> pathsTest = new ArrayList<ArrayList<Integer>>();
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 0)));
        pathsTest.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3, 0)));

        ArrayList<Solves> results = Algorithms.shortestDistancePath(scenario_0, pathsTest, 0, false);
        ArrayList<Solves> worthResults = Algorithms.shortestDistancePath(scenario_0, pathsTest, 0, true);

        ArrayList<Integer> awaitedResult = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 0));
        ArrayList<Integer> awaitedWorthResult = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 0));

        assertEquals(results.get(0).getSolveList(), awaitedResult);
        assertEquals(worthResults.get(0).getSolveList(), awaitedWorthResult);
    }
}
