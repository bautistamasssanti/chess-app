package src.chess.gameMode;

import src.chess.gameMode.DrawConditions.NotEnoughMaterial;
import src.chess.gameMode.WinConditions.HasAttackerCheckmatedOpponent;
import src.chess.gameMode.gameRules.IsCurrentTurnNotInCheck;
import src.chess.gameMode.optionalGameRules.PromoteRule;
import src.logic.gameMode.*;
import src.logic.piece.PieceType;

public class ChessGameModeFactory {
    public GameMode standardChess(){
        DrawCondition[] drawConditions = {new NotEnoughMaterial()};
        WinCondition[] winConditions = {new HasAttackerCheckmatedOpponent()};
        GameRule[] gameRules = {new IsCurrentTurnNotInCheck()};
        OptionalGameRule[] optionalGameRules = {new PromoteRule(PieceType.PAWN, PieceType.QUEEN)};
        return new GameModeImplementation(winConditions, drawConditions, gameRules, optionalGameRules);
    }
}
