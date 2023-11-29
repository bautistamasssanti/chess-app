package src.internationalCheckers.gameMode;

import src.internationalCheckers.gameMode.drawConditions.InsuficientMaterial;
import src.internationalCheckers.gameMode.gameRules.MoveComplyWithJumpRule;
import src.internationalCheckers.gameMode.optionalGameRules.Promote;
import src.internationalCheckers.gameMode.winConditions.CanOpponentNotMoveAnyPiece;
import src.internationalCheckers.gameMode.winConditions.HasPlayerEatenAllOpponentPieces;
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
