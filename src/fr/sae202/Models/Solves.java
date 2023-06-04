package fr.sae202.Models;

import java.util.ArrayList;

public class Solves {
    private ArrayList<Integer> solveList;
    private int solveDuration;
    private int solveXp;
    private int solveQuestNumber;
    private int sumDistancesTraveled;

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
}
