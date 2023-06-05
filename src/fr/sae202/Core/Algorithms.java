package fr.sae202.Core;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static ArrayList<ArrayList<Integer>> findAllPaths(Scenario scenario) {
        Quest end = scenario.getQuestMap().get(0);

        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[scenario.getQuestMap().size()];

        Player player = new Player(); // Player simulation
        player.debugOff();
        for(Quest start : fetchAvailableQuests(scenario, player, false))
        {
            player.movePlayer(start.getQuestPos());
            player.addFinishedQuest(start);

            dfs(scenario, start, end, visited, path, paths, player);
            player.resetPlayer();
        }
        enoughtExperienceFilter(scenario, paths); // put a first validation filter layer
        return paths;
    }

    private static void dfs(Scenario scenario, Quest u, Quest end, boolean[] visited, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths, Player player) {
        visited[u.getQuestId()] = true;
        path.add(u.getQuestId());

        if (u == end) {
            paths.add(new ArrayList<>(path));
        } else {
            for (Quest v : fetchAvailableQuests(scenario, player, false)) {
                if (!visited[v.getQuestId()]) {
                    player.movePlayer(v.getQuestPos());
                    player.addFinishedQuest(v);
                    dfs(scenario, v, end, visited, path, paths, player);
                }
            }
        }

        visited[u.getQuestId()] = false;
        path.remove(path.size() - 1);
        player.rollBackQuest();
    }

    private static void enoughtExperienceFilter(Scenario scenario, ArrayList<ArrayList<Integer>> paths) {
        ArrayList<ArrayList<Integer>> pathsToRemove = new ArrayList<>();
        for(ArrayList<Integer> path : paths)
        {
            int xp = 0;
            for(Integer questId : path)
            {
                if(questId == 0)
                    continue;
                xp += scenario.getQuestMap().get(questId).getQuestXp();
            }
            if(xp < scenario.getQuestMap().get(0).getQuestXp())
            {
                pathsToRemove.add(path);
            }
        }

        for(ArrayList<Integer> path : pathsToRemove)
        {
            paths.remove(path);
        }
    }

    public static ArrayList<Integer> findFastestPath(Scenario scenario, ArrayList<ArrayList<Integer>> paths)
    {
        ArrayList<Integer> fastestPath = null;
        Solves fastestSolves = null;
        ArrayList<Solves> debugSolves = new ArrayList<>();
        for(ArrayList<Integer> path : paths)
        {
            if(fastestSolves == null)
            {
                fastestSolves = doPathSimulation(scenario, path);
                debugSolves.add(fastestSolves);
            }
            else
            {
                Solves solve = doPathSimulation(scenario, path);
                if(solve.getSolveDuration() < fastestSolves.getSolveDuration())
                {
                    fastestSolves = solve;
                    fastestPath = path;
                }
                debugSolves.add(solve);
            }
        }
        System.out.println(debugSolves);
        return fastestPath;
    }

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
