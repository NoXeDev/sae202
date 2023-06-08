package fr.sae202.Exceptions;

/**
 * Abstract class for the core exceptions
 */
public abstract class CoreException extends Exception{
    protected String message;

    public CoreException(String message)
    {
        this.message = message;
    }

    public abstract void printMessage();
}
