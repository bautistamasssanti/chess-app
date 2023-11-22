package src.Chess.gameMode;

import src.Chess.gameMode.DrawConditions.NotEnoughMaterial;
import src.Chess.gameMode.WinConditions.HasAttackerCheckmatedOpponent;
import src.Chess.gameMode.gameRules.IsCurrentTurnNotInCheck;
import src.Chess.gameMode.optionalGameRules.PromoteRule;
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
