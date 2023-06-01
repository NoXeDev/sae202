package fr.sae202.Models;

import fr.sae202.Utils.Vector2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    @DisplayName("Player move test")
    public void movePlayertest()
    {
        ArrayList<Vector2<Integer>> vecList = new ArrayList<Vector2<Integer>>();
        vecList.addAll(Arrays.asList(
                new Vector2<>(1, 2),
                new Vector2<>(-1, 4),
                new Vector2<>(2, -6),
                new Vector2<>(-3, -4)
        ));

        ArrayList<Integer> timeList = new ArrayList<Integer>();
        timeList.addAll(Arrays.asList(
                3,
                6,
                2,
                -5
        ));

        Player testPlayer = new Player();

        for(Vector2<Integer> vec : vecList) {
            testPlayer.movePlayer(vec);
            assertEquals(testPlayer.getPlayerPos(), vec);
            assertEquals(testPlayer.getPlayerTime(), timeList.get(vecList.indexOf(vec)));
        }
    }

    @Test
    @DisplayName("Player quest distance")
    public void questDistanceTest()
    {
        /*ArrayList<Quest> questList = new ArrayList<Quest>();
        questList.addAll(Arrays.asList(
               new Quest(1, new Vector2<Integer>(1, 2), new Vector2<Vector2<Integer>>(new Vector2<Integer>(1, 2), new Vector2<Integer>(3, 4)), 3, 100, "test quest 1"),
                new Quest(2, new Vector2<Integer>(-1, 4), new Vector2<Vector2<Integer>>(new Vector2<Integer>(-1, 4), new Vector2<Integer>(-3, 2)), 6, 200, "test quest 2"),
                new Quest(3, new Vector2<Integer>(2, -6), new Vector2<Vector2<Integer>>(new Vector2<Integer>(2, -6), new Vector2<Integer>(4, -4)), 2, 300, "test quest 3"),
                new Quest(4, new Vector2<Integer>(-3, -4), new Vector2<Vector2<Integer>>(new Vector2<Integer>(-3, -4), new Vector2<Integer>(-5, -2)), -5, 400, "test quest 4")
        ));

        Player testPlayer = new Player();
        testPlayer.movePlayer(new Vector2<>(1, 3));

        for(Quest current: questList)
        {
            assertEquals(testPlayer.questDistance(current), Math.abs(testPlayer.getPlayerPos().getX() - current.getQuestPos().getX()) + Math.abs(testPlayer.getPlayerPos().getY() - current.getQuestPos().getY()));
        }*/
    }
}
