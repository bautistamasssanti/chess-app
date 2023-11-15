package src.Checkers.gameMode;

import src.Checkers.gameMode.drawConditions.InsuficientMaterial;
import src.Checkers.gameMode.gameRules.MoveComplyWithJumpRule;
import src.Checkers.gameMode.optionalGameRules.Promote;
import src.Checkers.gameMode.winConditions.HasPlayerEatenAllOpponentPieces;
import src.logic.gameMode.*;



public class CheckersGameModeFactory {
    public GameMode internationalCheckers(){
        DrawCondition[] drawConditions = {new InsuficientMaterial()};
        WinCondition[] winConditions = {new HasPlayerEatenAllOpponentPieces()};
        GameRule[] gameRules = {new MoveComplyWithJumpRule()};
        OptionalGameRule[] optionalGameRules = {new Promote()};
        return new GameModeImplementation(winConditions, drawConditions, gameRules, optionalGameRules);
    }
}
