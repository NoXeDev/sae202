package fr.sae202.Exceptions;

/**
 * Exception that can occure during the quest parsing
 */
public class QuestParseException extends CoreException {
    private int scenarioId;
    private int questId;
    public QuestParseException(String message, int scenarioId, int questId)
    {
        super(message);
        this.scenarioId = scenarioId;
        this.questId = questId;
    }

    @Override
    public void printMessage()
    {
        System.out.println(this.message);
    }

    public int getScenarioId()
    {
        return scenarioId;
    }

    public int getQuestId()
    {
        return questId;
    }
}