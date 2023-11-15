package src.Checkers.gameMode;

import src.Checkers.gameMode.drawConditions.InsuficientMaterial;
import src.Checkers.gameMode.winConditions.HasPlayerEatenAllOpponentPieces;
import src.logic.gameMode.*;



public class CheckersGameModeFactory {
    public GameMode internationalCheckers(){
        DrawCondition[] drawConditions = {new InsuficientMaterial()};
        WinCondition[] winConditions = {new HasPlayerEatenAllOpponentPieces()};
        GameRule[] gameRules = {};
        OptionalGameRule[] optionalGameRules = {};
        return new GameModeImplementation(winConditions, drawConditions, gameRules, optionalGameRules);
    }
}
