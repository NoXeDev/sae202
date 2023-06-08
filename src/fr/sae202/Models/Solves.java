package fr.sae202.Models;

import java.util.ArrayList;

/**
 * Class representing a solution
 */
public class Solves {
    private ArrayList<Integer> solveList;
    private int solveDuration;
    private int solveXp;
    private int solveQuestNumber;
    private int sumDistancesTraveled;

    /**
     * Constructor of the Solves class.
     * @param solveList
     * @param solveDuration
     * @param solveXp
     * @param solveQuestNumber
     * @param sumDistancesTraveled
     */
    public Solves(ArrayList<Integer> solveList, int solveDuration, int solveXp, int solveQuestNumber, int sumDistancesTraveled) 
    {
        this.solveList = solveList;
        this.solveDuration = solveDuration;
        this.solveXp = solveXp;
        this.solveQuestNumber = solveQuestNumber;
        this.sumDistancesTraveled = sumDistancesTraveled;
    }

    public ArrayList<Integer> getSolveList() {
        return solveList;
    }

    public int getSolveDuration() {
        return solveDuration;
    }

    public int getSolveXp() {
        return solveXp;
    }

    public int getSolveQuestNumber() {
        return solveQuestNumber;
    }

    public int getSumDistancesTraveled() {
        return sumDistancesTraveled;
    }

    @Override
    public String toString() {
        return "Solve : " + solveList + "\n" +
            "Durée total : " + solveDuration + "\n" +
            "XP total : " + solveXp + "\n" +
            "Nombre de quêtes résolues : " + solveQuestNumber + "\n" +
            "Distance totale parcourue : " + sumDistancesTraveled + "\n";
    }
}
