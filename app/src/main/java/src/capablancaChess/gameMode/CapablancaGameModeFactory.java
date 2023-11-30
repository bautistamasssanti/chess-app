package src.capablancaChess.gameMode;


import src.capablancaChess.gameMode.drawConditions.NotEnoughMaterial;
import src.capablancaChess.gameMode.gameRules.IsCurrentTurnNotInCheck;
import src.capablancaChess.gameMode.optionalGameRules.PromoteRule;
import src.capablancaChess.gameMode.winConditions.HasAttackerCheckmatedOpponent;
import src.logic.gameMode.*;
import src.logic.piece.PieceType;

public class CapablancaGameModeFactory {
    public GameMode capablancaChess(){
        DrawCondition[] drawConditions = {new NotEnoughMaterial()};
        WinCondition[] winConditions = {new HasAttackerCheckmatedOpponent()};
        GameRule[] gameRules = {new IsCurrentTurnNotInCheck()};
        OptionalGameRule[] optionalGameRules = {new PromoteRule(PieceType.PAWN, PieceType.QUEEN)};
        return new GameModeImplementation(winConditions, drawConditions, gameRules, optionalGameRules);
    }
}
