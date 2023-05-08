package fr.sae202.Exceptions;

import java.io.IOException;

public class ScenarioNotFoundException extends CoreException {
    private int scenarioNotFound;
    private EException_type type;
    private Exception subException;
    public ScenarioNotFoundException(int scenario, EException_type type, Exception e)
    {
        super("");
        this.scenarioNotFound = scenario;
        this.type = type;
        this.subException = e;
    }

    @Override
    public void printMessage()
    {
        if(type == EException_type.SCENARIO_NOT_FOUND)
        {
            System.out.println("quest " + scenarioNotFound + " is not available.");
        } else if(type == EException_type.IO_EXCEPTION)
        {
            System.out.println("quest file"+ scenarioNotFound + "throw an internal exception");
            if(this.subException != null)
            {
                ((IOException)this.subException).printStackTrace();
            } else {
                System.out.println("no details...");
            }
        }
    }

    public int getQuestNumber()
    {
        return scenarioNotFound;
    }

    public EException_type getSubExceptionType()
    {
        return type;
    }

    public Exception getSubException()
    {
        return subException;
    }
}
