package src.chessGames.capablancaChess.gameMode;

import src.chessGames.capablancaChess.gameState.CapablancaGameStateFactory;
import src.chessGames.capablancaChess.piece.CapablancaPieceFactory;
import src.chessGames.commons.gameMode.WinConditions.HasAttackerCheckmatedOpponent;
import src.chessGames.commons.gameMode.optionalGameRules.PromoteRule;

import src.chessGames.commons.gameMode.DrawConditions.NotEnoughMaterial;
import src.chessGames.commons.gameMode.gameRules.IsCurrentTurnNotInCheck;
import src.logic.gameMode.*;
import src.logic.piece.PieceType;

public class CapablancaGameModeFactory {
    public GameMode capablancaChess(){
        DrawCondition[] drawConditions = {new NotEnoughMaterial()};
        WinCondition[] winConditions = {new HasAttackerCheckmatedOpponent(new CapablancaGameStateFactory())};
        GameRule[] gameRules = {new IsCurrentTurnNotInCheck()};
        OptionalGameRule[] optionalGameRules = {new PromoteRule(PieceType.PAWN, PieceType.QUEEN, new CapablancaPieceFactory())};
        return new GameModeImplementation(winConditions, drawConditions, gameRules, optionalGameRules);
    }
}
