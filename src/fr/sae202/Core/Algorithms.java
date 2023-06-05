package fr.sae202.Core;

import java.util.ArrayList;
import java.util.stream.Collectors;

import fr.sae202.Models.Player;
import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;
import fr.sae202.Models.Solves;
import fr.sae202.Utils.Vector2;

public class Algorithms {
    /**
    * Find the nearest quest available
    * @param availableQuests Available quests
    * @param player The player
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
    * @param finishedQuests Finished quests
    * @param player The player
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
     * @param nSolutions The number of solutions to find
     * @return All valids paths for finish the scenario
     */
    public static ArrayList<ArrayList<Integer>> findAllPaths(Scenario scenario, int nSolutions) {
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

            int roundBonus = 0;
            if(availableQuests.indexOf(start)+1 == availableQuests.size())
            {
                roundBonus = (nSolutions % availableQuests.size())*availableQuests.size();
            }
            dfs(scenario, start, end, visited, path, paths, player, (nSolutions/availableQuests.size())*(availableQuests.indexOf(start)+1) + roundBonus);
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
     */
    private static void dfs(Scenario scenario, Quest u, Quest end, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths, Player player, int nSolutions) {
        if((nSolutions != 0) && (paths.size() >= nSolutions))
                    return;
        visited[u.getQuestId()] = true;
        path.add(u.getQuestId());

        if (u == end) {
            if(enoughtExperienceFilter(scenario, path))
                paths.add(new ArrayList<>(path));
        } else {
            for (Quest v : fetchAvailableQuests(scenario, player, false)) {
                if (!visited[v.getQuestId()]) {
                    player.movePlayer(v.getQuestPos());
                    player.addFinishedQuest(v);
                    dfs(scenario, v, end, visited, path, paths, player, nSolutions);
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
     * @return The fastest path
     */
    public static ArrayList<Integer> findFastestPath(Scenario scenario, ArrayList<ArrayList<Integer>> paths)
    {
        ArrayList<Integer> fastestPath = null;
        Solves fastestSolves = null;
        for(ArrayList<Integer> path : paths)
        {
            if(fastestSolves == null)
            {
                fastestSolves = doPathSimulation(scenario, path);
            }
            else
            {
                Solves solve = doPathSimulation(scenario, path);
                if(solve.getSolveDuration() < fastestSolves.getSolveDuration())
                {
                    fastestSolves = solve;
                    fastestPath = path;
                }
            }
        }
        return fastestPath;
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
}
