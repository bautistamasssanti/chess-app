package src.logic.exceptions;

public class NotPlayerTurnException extends Exception{
    public NotPlayerTurnException() {
        super("Not player turn.");
    }
}
