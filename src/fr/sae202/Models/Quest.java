package fr.sae202.Models;

import fr.sae202.Utils.Vector2;

public class Quest {
    public int qId;
    public Vector2<Integer> qPos;
    public Vector2<Vector2<Integer>> qPrecondition;
    public int qDuration;
    public int qXp;
    public String qTitle;

    public Quest(int id, Vector2<Integer> position, Vector2<Vector2<Integer>> precondition, int duration, int xp, String title)
    {
        qId = id;
        qPos = position;
        qPrecondition = precondition;
        qDuration = duration;
        qXp = xp;
        qTitle = title;
    }

    public String toString()
    {
        return "ID : " + this.qId + "\nPositions : " + qPos.toString() + "\nPréconditions : " + qPrecondition.toString() + "\nDurée : " + qDuration + "\nXP : " + qXp + "\nTitre : " + qTitle + "\n";
    }
}
