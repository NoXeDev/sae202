package fr.sae202.Models;

import fr.sae202.Core.Game;
import fr.sae202.Core.QuestParser;

import java.util.HashMap;
import java.util.Map;

public class Selection {
    private Integer selScenario;
    private Integer selType;
    private Integer selCriteria;
    private Integer selOrder;
    private Map<Integer,Solves> solutionsMap;
    public Selection(Integer scenario, Integer type, Integer criteria, Integer order){
        this.selScenario = scenario;
        this.selType = type;
        this.selCriteria = criteria;
        this.selOrder = order;
    }

    public Solves createSolution() {
        Game mainGame = new Game();
        QuestParser parser = new QuestParser("res");
        switch (selType) {
            case 0:
                switch (selCriteria) {
                    case 0:
                        try {
                            return mainGame.solutionEfficaceGloutonne(parser.parseScenario(selScenario));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 1:
                        try {
                            //return mainGame.solutionEfficaceDuree(selScenario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 2:
                        try {
                            //return mainGame.solutionEfficaceNbQuetes(selScenario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 3:
                        try {
                            //return mainGame.solutionEfficaceDeplacement(selScenario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            case 1:
                switch (selCriteria) {
                    case 0:
                        try {
                            //return mainGame.solutionExhaustiveGloutonne(selScenario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 1:
                        try {
                            //return mainGame.solutionExhaustiveDuree(selScenario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 2:
                        try {
                            //return mainGame.solutionExhaustiveNbQuetes(selScenario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 3:
                        try {
                            //return mainGame.solutionExhaustiveDeplacement(selScenario);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
        }
        return null;}

    public Map<Integer,Solves> createSolvesMap(){
        solutionsMap = new HashMap<Integer, Solves>();
        return solutionsMap;
        //A MODIFIER UNE FOIS LE SYSTEME DE MUTLI-SOLUTIONS CRÉÉ
    }

    public Map<Integer,Solves> getFinalSolvesMap(int solvesValue){
        Map<Integer,Solves> finalSolvesMap = new HashMap<Integer,Solves>();
        if (solvesValue > solutionsMap.size() || solvesValue == -1){
            solvesValue = solutionsMap.size();
        }
        for (int i = 0; i < solvesValue; i++) {
            finalSolvesMap.put(i,solutionsMap.get(i));
        }
        return finalSolvesMap;
        //A MODIFIER UNE FOIS LE SYSTEME DE MUTLI-SOLUTIONS CRÉÉ
    }
}
