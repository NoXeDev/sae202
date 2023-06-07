package fr.sae202.Models;

import fr.sae202.Core.Game;
import fr.sae202.Core.QuestParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Selection {
    private Integer selScenario;
    private Integer selType;
    private Integer selCriteria;
    private Integer selOrder;
    public Selection(Integer scenario, Integer type, Integer criteria, Integer order){
        this.selScenario = scenario;
        this.selType = type;
        this.selCriteria = criteria;
        this.selOrder = order;
    }

    public ArrayList<Solves> createSolution(int nbSolutions) {
        Game mainGame = new Game();
        QuestParser parser = new QuestParser("res");
        switch (selType) {
            case 0:
                switch (selCriteria) {
                    case 0:
                        try {
                            ArrayList<Solves> tempArray = new ArrayList<>();
                            tempArray.add(mainGame.solutionEfficaceGloutonne(parser.parseScenario(selScenario)));
                            return tempArray;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 1:
                        try {
                            return mainGame.bestSpeedruns(parser.parseScenario(selScenario), nbSolutions, false, selOrder == 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 2:
                        try {
                            return mainGame.bestNBQuests(parser.parseScenario(selScenario), nbSolutions, false, selOrder == 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 3:
                        try {
                            return mainGame.bestDistancePath(parser.parseScenario(selScenario), nbSolutions, false, selOrder == 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            case 1:
                switch (selCriteria) {
                    case 0:
                        try {
                            ArrayList<Solves> tempArray = new ArrayList<>();
                            tempArray.add(mainGame.solutionExhaustiveGloutonne(parser.parseScenario(selScenario)));
                            return tempArray;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 1:
                        try {
                            return mainGame.bestSpeedruns(parser.parseScenario(selScenario), nbSolutions, true, selOrder == 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 2:
                        try {
                            return mainGame.bestNBQuests(parser.parseScenario(selScenario), nbSolutions, true, selOrder == 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 3:
                        try {
                            return mainGame.bestDistancePath(parser.parseScenario(selScenario), nbSolutions, true, selOrder == 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
        }
        return null;}

    public ArrayList<Solves> getFinalSolves(int nbSolutions){
        return createSolution(nbSolutions);
    }
}
