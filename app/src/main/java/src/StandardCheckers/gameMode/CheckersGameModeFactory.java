package src.StandardCheckers.gameMode;

import src.StandardCheckers.gameMode.drawConditions.InsuficientMaterial;
import src.StandardCheckers.gameMode.gameRules.MoveComplyWithJumpRule;
import src.StandardCheckers.gameMode.optionalGameRules.Promote;
import src.StandardCheckers.gameMode.winConditions.CanOpponentNotMoveAnyPiece;
import src.StandardCheckers.gameMode.winConditions.HasPlayerEatenAllOpponentPieces;
import src.logic.gameMode.*;



public class CheckersGameModeFactory {
    public GameMode internationalCheckers(){
        DrawCondition[] drawConditions = {new InsuficientMaterial()};
        WinCondition[] winConditions = {new HasPlayerEatenAllOpponentPieces(), new CanOpponentNotMoveAnyPiece()};
        GameRule[] gameRules = {new MoveComplyWithJumpRule()};
        OptionalGameRule[] optionalGameRules = {new Promote()};
        return new GameModeImplementation(winConditions, drawConditions, gameRules, optionalGameRules);
    }
}
