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

    /**
     * Constructor for Selection
     * @param scenario the selected scenario
     * @param type the selected type
     * @param criteria the selected criteria
     * @param order the selected order
     */
    public Selection(Integer scenario, Integer type, Integer criteria, Integer order){
        this.selScenario = scenario;
        this.selType = type;
        this.selCriteria = criteria;
        this.selOrder = order;
    }

    /**
     * Create the solutions depending on the object's attributes
     * @param nbSolutions the number of solutions to create
     * @return an arraylist of solutions
     */
    public ArrayList<Solves> createSolution(int nbSolutions) {
        Game mainGame = new Game();
        QuestParser parser = new QuestParser("res");
        switch (selType) {
            case 0: //Effective solutions
                switch (selCriteria) {
                    case 0: //Glouton
                        try {
                            ArrayList<Solves> tempArray = new ArrayList<>();
                            tempArray.add(mainGame.solutionEfficaceGloutonne(parser.parseScenario(selScenario)));
                            return tempArray;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 1: //Duration
                        try {
                            return mainGame.bestSpeedruns(parser.parseScenario(selScenario), nbSolutions, false, selOrder == 1); //selOrder == 1 -> Worst solutions
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 2: //Number of quests
                        try {
                            return mainGame.bestNBQuests(parser.parseScenario(selScenario), nbSolutions, false, selOrder == 1); //selOrder == 1 -> Worst solutions
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 3: //Distance
                        try {
                            return mainGame.bestDistancePath(parser.parseScenario(selScenario), nbSolutions, false, selOrder == 1); //selOrder == 1 -> Worst solutions
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            case 1: //Exhaustive solutions
                switch (selCriteria) {
                    case 0: //Glouton
                        try {
                            ArrayList<Solves> tempArray = new ArrayList<>();
                            tempArray.add(mainGame.solutionExhaustiveGloutonne(parser.parseScenario(selScenario)));
                            return tempArray;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 1: //Duration
                        try {
                            return mainGame.bestSpeedruns(parser.parseScenario(selScenario), nbSolutions, true, selOrder == 1); //selOrder == 1 -> Worst solutions
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 2: //Number of quests
                        try {
                            return mainGame.bestNBQuests(parser.parseScenario(selScenario), nbSolutions, true, selOrder == 1); //selOrder == 1 -> Worst solutions
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 3: //Distance
                        try {
                            return mainGame.bestDistancePath(parser.parseScenario(selScenario), nbSolutions, true, selOrder == 1); //selOrder == 1 -> Worst solutions
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
        }
        return null;}

    /**
     * Get the solutions
     * @param nbSolutions the number of solutions to create
     * @return an arraylist of solutions
     */
    public ArrayList<Solves> getFinalSolves(int nbSolutions){
        return createSolution(nbSolutions);
    }
}
