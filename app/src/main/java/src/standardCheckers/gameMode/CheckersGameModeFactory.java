package src.standardCheckers.gameMode;

import src.standardCheckers.gameMode.drawConditions.InsuficientMaterial;
import src.standardCheckers.gameMode.gameRules.MoveComplyWithJumpRule;
import src.standardCheckers.gameMode.optionalGameRules.Promote;
import src.standardCheckers.gameMode.winConditions.CanOpponentNotMoveAnyPiece;
import src.standardCheckers.gameMode.winConditions.HasPlayerEatenAllOpponentPieces;
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
