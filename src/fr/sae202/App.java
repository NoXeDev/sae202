package fr.sae202;

import java.util.Map;

import fr.sae202.Controller.QuestParser;
import fr.sae202.Exceptions.QuestParseException;
import fr.sae202.Exceptions.ScenarioNotFoundException;
import fr.sae202.Models.Scenario;

public class App {
    public static void main(String[] args) {
        QuestParser parser = new QuestParser("res");
        try {
            Map<Integer, Scenario> scenarioList = parser.parseAllScenario();
            for(Scenario current: scenarioList.values())
            {
                System.out.println(current.toString());
            }
        } catch (ScenarioNotFoundException | QuestParseException e) {
            e.printMessage();
        }
    }
}
