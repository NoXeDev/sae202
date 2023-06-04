package fr.sae202.Models;

import fr.sae202.Utils.Vector2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    ArrayList<Quest> questList = new ArrayList<Quest>();

    public PlayerTest()
    {
        // Add generic and totally random quest list for all tests
        questList.addAll(Arrays.asList(
            new Quest(1, new Vector2<Integer>(1, 2), new Vector2<Vector2<Integer>>(new Vector2<Integer>(1, 2), new Vector2<Integer>(3, 4)), 3, 100, "test quest 1"),
            new Quest(2, new Vector2<Integer>(-1, 4), new Vector2<Vector2<Integer>>(new Vector2<Integer>(-1, 4), new Vector2<Integer>(-3, 2)), 6, 200, "test quest 2"),
            new Quest(3, new Vector2<Integer>(2, -6), new Vector2<Vector2<Integer>>(new Vector2<Integer>(2, -6), new Vector2<Integer>(4, -4)), 2, 300, "test quest 3"),
            new Quest(4, new Vector2<Integer>(-3, -4), new Vector2<Vector2<Integer>>(new Vector2<Integer>(-3, -4), new Vector2<Integer>(-5, -2)), 5, 400, "test quest 4"),
            new Quest(0, new Vector2<Integer>(-5, 2), new Vector2<Vector2<Integer>>(new Vector2<Integer>(2, 1), new Vector2<Integer>(3, 4)), 5, 600, "final boss")
        ));
    }

    
    @Test
    @DisplayName("Player move test")
    @Order(1)
    public void movePlayerTest()
    {
        Player testPlayer = new Player();

        for(Quest quest : questList) {
            testPlayer.movePlayer(quest.getQuestPos());
            assertEquals(testPlayer.getPlayerPos(), quest.getQuestPos());
            assertEquals(testPlayer.getPlayerTime(), testPlayer.questDistance(quest) + testPlayer.getPlayerTime());
        }
    }

    @Test
    @DisplayName("Player quest distance")
    @Order(0)
    public void questDistanceTest()
    {
        Player testPlayer = new Player();
        testPlayer.movePlayer(new Vector2<>(1, 3));

        assertEquals(testPlayer.questDistance(questList.get(0)), 1);
        assertEquals(testPlayer.questDistance(questList.get(1)), 3);
        assertEquals(testPlayer.questDistance(questList.get(2)), 10);
        assertEquals(testPlayer.questDistance(questList.get(3)), 11);
    }


    @Test
    @DisplayName("Player add finished quest")
    @Order(2)
    public void addFinishedQuestTest()
    {
        Player player = new Player();

        ArrayList<Integer> questTime = new ArrayList<Integer>();
        questTime.addAll(Arrays.asList(
                3,
                9,
                11,
                16,
                21
        ));

        ArrayList<Integer> questXp = new ArrayList<Integer>();
        questXp.addAll(Arrays.asList(
                100,
                300,
                600,
                1000,
                1000 // quest 0 doesn't affect xp
        ));

        for(Quest quest : questList)
        {
            player.addFinishedQuest(quest);
            assertEquals(player.getPlayerTime(), questTime.get(questList.indexOf(quest)));
            assertEquals(player.getPlayerXp(), questXp.get(questList.indexOf(quest)));
        }

        for(Quest quest : player.getFinishedQuests())
        {
            assertEquals(quest.getQuestId(), questList.get(questList.indexOf(quest)).getQuestId()); // Check that all quests is here
        }
    }
}
