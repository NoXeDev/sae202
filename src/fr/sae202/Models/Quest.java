package fr.sae202.Models;

import fr.sae202.Utils.Vector2;

public class Quest {
    private int qId;
    private Vector2<Integer> qPos;
    private Vector2<Vector2<Integer>> qPrecondition;
    private int qDuration;
    private int qXp;
    private String qTitle;

    /**
     * Data structure for store quest datas
     * @param id quest id
     * @param position quest position
     * @param precondition quest precondition
     * @param duration quest duration
     * @param xp reward xp
     * @param title quest title
     */
    public Quest(int id, Vector2<Integer> position, Vector2<Vector2<Integer>> precondition, int duration, int xp, String title)
    {
        qId = id;
        qPos = position;
        if(precondition == null)
            qPrecondition = new Vector2<Vector2<Integer>>(null, null);
        else
            qPrecondition = precondition;
        qDuration = duration;
        qXp = xp;
        qTitle = title;
    }

    public String toString()
    {
        return "ID : " + this.qId + "\nPositions : " + qPos.toString() + "\nPréconditions : " + qPrecondition.toString() + "\nDurée : " + qDuration + "\nXP : " + qXp + "\nTitre : " + qTitle + "\n";
    }

    /**
     * Compare two quest
     * @param obj the other quest
     * @return true if the two quest are equals
     */
    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Quest))
        {
            return false;
        }
        Quest other = (Quest) obj;
        return other.qId == this.qId 
        && other.qPos.equals(this.qPos)
        && other.qPrecondition.equals(this.qPrecondition)
        && other.qDuration == this.qDuration 
        && other.qXp == this.qXp 
        && other.qTitle.equals(this.qTitle);
    }


    public int getQuestId() {
        return qId;
    }
    
    public Vector2<Integer> getQuestPos() {
        return qPos;
    }

    public Vector2<Vector2<Integer>> getQuestPrecondition() {
        return qPrecondition;
    }

    public int getQuestDuration() {
        return qDuration;
    }

    public int getQuestXp() {
        return qXp;
    }

    public String getQuestTitle() {
        return qTitle;
    }
}
