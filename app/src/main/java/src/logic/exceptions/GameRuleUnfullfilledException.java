package src.logic.exceptions;

public class GameRuleUnfullfilledException extends Exception{
    public GameRuleUnfullfilledException(String message) {
        super("Game rule unfullfilled: " + message);
    }
}
