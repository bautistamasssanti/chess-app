package src.chessGames.chess.gameMode;

import src.chessGames.chess.gameState.ChessGameStateFactory;
import src.chessGames.chess.piece.ChessPieceFactory;
import src.chessGames.commons.gameMode.DrawConditions.NotEnoughMaterial;
import src.chessGames.commons.gameMode.WinConditions.HasAttackerCheckmatedOpponent;
import src.chessGames.commons.gameMode.gameRules.IsCurrentTurnNotInCheck;
import src.chessGames.commons.gameMode.optionalGameRules.PromoteRule;
import src.logic.gameMode.*;
import src.logic.piece.PieceType;

public class ChessGameModeFactory {
    public GameMode standardChess(){
        DrawCondition[] drawConditions = {new NotEnoughMaterial()};
        WinCondition[] winConditions = {new HasAttackerCheckmatedOpponent(new ChessGameStateFactory())};
        GameRule[] gameRules = {new IsCurrentTurnNotInCheck()};
        OptionalGameRule[] optionalGameRules = {new PromoteRule(PieceType.PAWN, PieceType.QUEEN, new ChessPieceFactory())};
        return new GameModeImplementation(winConditions, drawConditions, gameRules, optionalGameRules);
    }
}
