package src.InternationalCheckers.gameMode;

import src.InternationalCheckers.gameMode.drawConditions.InsuficientMaterial;
import src.InternationalCheckers.gameMode.gameRules.MoveComplyWithJumpRule;
import src.InternationalCheckers.gameMode.optionalGameRules.Promote;
import src.InternationalCheckers.gameMode.winConditions.CanOpponentNotMoveAnyPiece;
import src.InternationalCheckers.gameMode.winConditions.HasPlayerEatenAllOpponentPieces;
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
