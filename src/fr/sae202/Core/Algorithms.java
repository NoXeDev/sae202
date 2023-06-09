package fr.sae202.Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import com.google.common.base.Function;

import fr.sae202.Models.Player;
import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;
import fr.sae202.Models.Solves;
import fr.sae202.Utils.Vector2;

/**
 * Class containing all algorithms used in the game
 */
public class Algorithms {
    /**
    * Find the nearest quest available
    * @param availableQuests Available quests
    * @param playerPos The player position
    * @return The nearest quest
    */
    public static Quest nearestQuest(ArrayList<Quest> availableQuests, Vector2<Integer> playerPos)
    {
        Quest nearestQuest = null;
        for(Quest quest : availableQuests)
        {
            if(nearestQuest == null)
            {
                nearestQuest = quest;
                continue;
            }
            if(Vector2.distance(playerPos, quest.getQuestPos()) < Vector2.distance(playerPos, nearestQuest.getQuestPos()))
            {
                nearestQuest = quest;
            }
        }

        return nearestQuest;
    }

    /**
     * Find the nearest and fastest quest available
     * @param availableQuests Available quests
     * @param playerPos The player position
     * @return The nearest and fastest quest
     */
    public static Quest nearestAndFastestQuest(ArrayList<Quest> availableQuests, Vector2<Integer> playerPos)
    {
        Quest nearestQuest = null;
        for(Quest quest : availableQuests)
        {
            if(nearestQuest == null)
            {
                nearestQuest = quest;
                continue;
            }
            if((Vector2.distance(playerPos, quest.getQuestPos()) + quest.getQuestDuration()) < (Vector2.distance(playerPos, nearestQuest.getQuestPos()) + nearestQuest.getQuestDuration()))
            {
                nearestQuest = quest;
            }
        }

        return nearestQuest;
    }
    

    /**
    * Search for all available quests with the actual finished quests
    * @param scenario The scenario to search in
    * @param player The player
    * @param xpFilter Filter the quests by xp
    * @return All available quests
    */
    public static ArrayList<Quest> fetchAvailableQuests(Scenario scenario, Player player, boolean xpFilter)
    {
        ArrayList<Integer> finishedQuestsById = player.getFinishedQuests().stream().map(Quest::getQuestId).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Quest> availableQuests = new ArrayList<>();
        for(Quest quest : scenario.getQuestMap().values())
        {
            if(!player.getFinishedQuests().contains(quest))
            {
                if(quest.getQuestPrecondition().isEmpty())
                {
                    availableQuests.add(quest);
                }
                else {
                    if(
                        (finishedQuestsById.contains(quest.getQuestPrecondition().getX().getX()) 
                        || finishedQuestsById.contains(quest.getQuestPrecondition().getX().getY())) 
                        && 
                        (
                            ((quest.getQuestPrecondition().getY() != null) 
                            && (finishedQuestsById.contains(quest.getQuestPrecondition().getY().getX()) 
                            || finishedQuestsById.contains(quest.getQuestPrecondition().getY().getY()))
                            || quest.getQuestPrecondition().getY() == null
                        )
                    )
                    )
                    {
                        if(quest.getQuestId() == 0 && (player.getPlayerXp() < quest.getQuestXp()) && xpFilter)
                        {
                            continue; // Need to do an other quest for farm XP
                        }
                        availableQuests.add(quest);
                    }
                }
            }
        }
        return availableQuests;
    }

    /**
     * Find all path to finish the scenario
     * @param scenario The scenario to search in
     * @param isExhaustive If true, find path with all quests
     * @return All valids paths for finish the scenario
     */
    public static ArrayList<ArrayList<Integer>> findAllPaths(Scenario scenario, boolean isExhaustive) {
        Quest end = scenario.getQuestMap().get(0);

        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[scenario.getQuestMap().size()];

        Player player = new Player(); // Player simulation
        player.debugOff();
        ArrayList<Quest> availableQuests = fetchAvailableQuests(scenario, player, false);
        for(Quest start : availableQuests)
        {
            player.movePlayer(start.getQuestPos());
            player.addFinishedQuest(start);

            int fetchSolutions = 0;
            if(scenario.getScenarioId() >= 6)
                fetchSolutions = 20_000;
            dfs(scenario, start, end, visited, path, paths, player, fetchSolutions, isExhaustive);
            path = new ArrayList<>();
            visited = new boolean[scenario.getQuestMap().size()];
            player.resetPlayer();
        }

        return paths;
    }

    /**
     * DFS algorithm for find all paths
     * @param scenario The scenario to search in
     * @param u The start quest (current quest recursive)
     * @param end The end quest
     * @param visited is the node already have been visited
     * @param path The current path
     * @param paths All valids paths
     * @param player The player
     * @param nSolutions The number of solutions to find
     * @param isExhaustive If true, find path with all quests
     */
    private static void dfs(Scenario scenario, Quest u, Quest end, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths, Player player, int nSolutions, boolean isExhaustive) {
        if((nSolutions != 0) && (paths.size() >= nSolutions))
                    return;
        visited[u.getQuestId()] = true;
        path.add(u.getQuestId());

        if (u == end) {
            if(enoughtExperienceFilter(scenario, path))
                if(isExhaustive && (path.size() == scenario.getQuestMap().size()))
                    paths.add(new ArrayList<>(path));
                else if(!isExhaustive)
                    paths.add(new ArrayList<>(path));
        } else {
            for (Quest v : fetchAvailableQuests(scenario, player, false)) {
                if (!visited[v.getQuestId()]) {
                    player.movePlayer(v.getQuestPos());
                    player.addFinishedQuest(v);
                    dfs(scenario, v, end, visited, path, paths, player, nSolutions, isExhaustive);
                }
            } 
        }

        visited[u.getQuestId()] = false;
        path.remove(path.size() - 1);
        player.rollBackQuest();
    }

    /**
     * Filter paths with enought experience for finish the scenario (quest 0)
     * @param scenario The scenario to search in
     * @param paths All valids paths
     */
    private static boolean enoughtExperienceFilter(Scenario scenario, ArrayList<Integer> paths) {
        int xp = 0;
        for(Integer questId : paths)
        {
            if(questId == 0)
                continue;
            xp += scenario.getQuestMap().get(questId).getQuestXp();
        }
        if(xp < scenario.getQuestMap().get(0).getQuestXp())
        {
            return false;
        }

        return true;
    }

    /**
     * Do a simulation of a path for find the fastest path
     * @param scenario The scenario to search in
     * @param paths All valids paths
     * @param nSolutions The number of solutions to find
     * @param worthFilter If true, return worth solutions
     * @return The fastest path
     */
    public static ArrayList<Solves> fastestPath(Scenario scenario, ArrayList<ArrayList<Integer>> paths, int nSolutions, boolean worthFilter)
    {
        ArrayList<Solves> solves = new ArrayList<>();
        for(ArrayList<Integer> path : paths)
        {
            solves.add(doPathSimulation(scenario, path));
        }
        return insertionSort(solves, (Solves s1) -> s1.getSolveDuration(), nSolutions, worthFilter);
    }

    /**
     * Do a simulation of a path for find the shortest path in number of quests
     * @param scenario The scenario to search in
     * @param paths All valids paths
     * @param nSolutions The number of solutions to find
     * @param worthFilter If true, return worth solutions
     * @return The shortest path in number of quests
     */
    public static ArrayList<Solves> shortestNBQuestsPath(Scenario scenario, ArrayList<ArrayList<Integer>> paths, int nSolutions, boolean worthFilter)
    {
        ArrayList<Solves> solves = new ArrayList<>();
        for(ArrayList<Integer> path : paths) {
            solves.add(doPathSimulation(scenario, path));
        }
        return insertionSort(solves, (Solves s1) -> s1.getSolveQuestNumber(), nSolutions, worthFilter);
    }

    /**
     * Do a simulation of a path for find the shortest path in distance
     * @param scenario The scenario to search in
     * @param paths All valids paths
     * @param nSolutions The number of solutions to find
     * @param worthFilter If true, return worth solutions
     * @return The shortest path in distance
     */
    public static ArrayList<Solves> shortestDistancePath(Scenario scenario, ArrayList<ArrayList<Integer>> paths, int nSolutions, boolean worthFilter)
    {
        ArrayList<Solves> solves = new ArrayList<>();
        for(ArrayList<Integer> path : paths)
        {
            solves.add(doPathSimulation(scenario, path));
        }
        return insertionSort(solves, (Solves s1) -> s1.getSumDistancesTraveled(), nSolutions, worthFilter);
    }

    /**
     * Do a simulation of a path
     * @param scenario The scenario to search in
     * @param path The path to simulate
     * @return The simulation result
     */
    private static Solves doPathSimulation(Scenario scenario, ArrayList<Integer> path)
    {
        Player player = new Player();
        player.debugOff();
        for(Integer questId : path)
        {
            player.movePlayer(scenario.getQuestMap().get(questId).getQuestPos());
            player.addFinishedQuest(scenario.getQuestMap().get(questId));
        }

        return new Solves(
            (ArrayList<Integer>)player.getFinishedQuests().stream().map(Quest::getQuestId).collect(Collectors.toList()),
            player.getPlayerTime(),
            player.getPlayerXp(),
            player.getFinishedQuests().size(),
            player.sumDistancesTraveled()
        );
    }

    /**
     * Sort a list with insertion sort
     * @param list The list to sort
     * @param f property filter to sort quests
     * @param nSolutions The number of solutions to find
     * @param reverse If true, reverse the list (for get worth solutions)
     * @return The sorted list
     */
    public static ArrayList<Solves> insertionSort(ArrayList<Solves> list, Function<Solves, Integer> f, int nSolutions, boolean reverse) {
        int n = list.size();
        int resultBound = (nSolutions > list.size() || nSolutions == 0) ? list.size() : nSolutions;

        for (int i = 1; i < n; i++) {
            Solves key = list.get(i);
            int j = i - 1;

            while (j >= 0 && f.apply(list.get(j)) > f.apply(key)) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
        if(reverse)
            Collections.reverse(list);

        return new ArrayList<Solves>(list.subList(0, resultBound));
    }
}
