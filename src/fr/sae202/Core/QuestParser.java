package fr.sae202.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import fr.sae202.Models.Quest;
import fr.sae202.Models.Scenario;
import fr.sae202.Utils.CoreIO;
import fr.sae202.Utils.GlobalUtils;
import fr.sae202.Utils.Vector2;
import fr.sae202.Exceptions.EException_type;
import fr.sae202.Exceptions.QuestParseException;
import fr.sae202.Exceptions.ScenarioNotFoundException;

public class QuestParser {
    private String scenariosPath;
    private ArrayList<Integer> availableScenarioList;

    /**
     * Constructor of the QuestParser class
     * @param path Path to the scenario folder
     */
    public QuestParser(String path) {
        scenariosPath = path;
        ArrayList<Integer> tmp = new ArrayList<>();
        for(String fileName: CoreIO.listFiles(scenariosPath))
        {
            if(Pattern.matches("^scenario_\\d+\\.txt$", fileName))
            {
                tmp.add(Integer.parseInt(fileName.replace("scenario_", "").replace(".txt", "")));
            }
        }
        availableScenarioList = tmp;
    }

    /**
     * Parse the scenario in the given path
     * @param scenarioNumber Scenario number
     * @return Scenario object containing all quests
     * @throws ScenarioNotFoundException If a scenario is not found
     * @throws QuestParseException If a quest is not well formatted
     */
    public Scenario parseScenario(int scenarioNumber) throws ScenarioNotFoundException, QuestParseException {
        if(availableScenarioList.contains(scenarioNumber))
        {
            File scenarioFile = new File(scenariosPath + "/scenario_"+scenarioNumber+".txt");
            Map<Integer, Quest> parsedScenarioMap = new HashMap<Integer, Quest>();
            try {
                try (BufferedReader fReader = new BufferedReader(new InputStreamReader(new FileInputStream(scenarioFile), "UTF-8"))) {
                    String line = fReader.readLine();
                    while (line != null)
                    {
                        String[] splittedStr = line.split("\\|"); // Split over pipe |
                        List<String> parsedPositions = GlobalUtils.arrayMatches(splittedStr[1], "\\d+"); // Get positions
                        List<String> preConditionRaw = GlobalUtils.arrayMatches(splittedStr[2], "\\(\\d,\\s*\\d*\\)"); // Get tuples positions
                        List<Vector2<Integer>> preConditionParsed = new ArrayList<Vector2<Integer>>();
                        for(String tuple: preConditionRaw)
                        {
                            String replaced = tuple.replaceAll("[\\(\\)\\s*]", ""); // remove "("" and ")" and spaces
                            String[] tmp = replaced.split(",");
                            if(tmp.length > 2)
                            {
                                throw new QuestParseException("Too much value in tuple positions", scenarioNumber, Integer.parseInt(splittedStr[0]));
                            }
                            
                            preConditionParsed.add(new Vector2<Integer>(Integer.parseInt(tmp[0]), (tmp.length > 1 ? Integer.parseInt(tmp[1]) : null)));
                        }


                        Quest currentQuest = new Quest(
                            Integer.parseInt(splittedStr[0]),
                            new Vector2<Integer>(Integer.parseInt(parsedPositions.get(0)), Integer.parseInt(parsedPositions.get(1))),
                            new Vector2<Vector2<Integer>>((preConditionParsed.size() > 0 ? preConditionParsed.get(0) : null), (preConditionParsed.size() > 1 ? preConditionParsed.get(1) : null)), 
                            Integer.parseInt(splittedStr[3]), 
                            Integer.parseInt(splittedStr[4]), 
                            splittedStr[5]
                        );

                        parsedScenarioMap.put(currentQuest.qId, currentQuest);
                        line = fReader.readLine();
                    }

                    fReader.close(); // REALLY IMPORTANT !!! (ressources need to be closed)
                } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                    throw new QuestParseException("Number conversion was failed", scenarioNumber, -1);
                }
            } 
            catch(IOException e)
            {
                throw new ScenarioNotFoundException(scenarioNumber, EException_type.IO_EXCEPTION, e);
            }

            return new Scenario(scenarioNumber, parsedScenarioMap);
        }
        else {
            throw new ScenarioNotFoundException(scenarioNumber, EException_type.SCENARIO_NOT_FOUND, null);
        }
    }

    /**
     * Parse all scenario in the given path
     * @return Map<Integer, Scenario> scenarioMap
     * @throws ScenarioNotFoundException If a scenario is not found
     * @throws QuestParseException If a quest is not well formatted
     */
    public Map<Integer, Scenario> parseAllScenario() throws ScenarioNotFoundException, QuestParseException
    {
        Map<Integer, Scenario> scenarioList = new HashMap<>();
        for(int i: availableScenarioList)
        {
            Scenario current = this.parseScenario(i);
            scenarioList.put(current.id, current);
        }

        return scenarioList;
    }
}
