package fr.sae202.Models;

import java.util.ArrayList;

public class Solves {
    private ArrayList<Integer> solveList;
    private int solveDuration;
    private int solveXp;
    private int solveQuestNumber;

    public Solves(ArrayList<Integer> solveList, int solveDuration, int solveXp, int solveQuestNumber) 
    {
        this.solveList = solveList;
        this.solveDuration = solveDuration;
        this.solveXp = solveXp;
        this.solveQuestNumber = solveQuestNumber;
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
}
