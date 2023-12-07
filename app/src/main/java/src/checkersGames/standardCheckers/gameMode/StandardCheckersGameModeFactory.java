package src.checkersGames.standardCheckers.gameMode;


import src.checkersGames.commons.gameMode.drawConditions.InsufficientMaterial;
import src.checkersGames.commons.gameMode.gameRules.EndOfDoubleJumpWithLessPieces;
import src.checkersGames.commons.gameMode.gameRules.MoveComplyWithJumpRule;

import src.checkersGames.commons.gameMode.optionalGameRules.Promote;
import src.checkersGames.commons.gameMode.winConditions.CanOpponentNotMoveAnyPiece;
import src.checkersGames.commons.gameMode.winConditions.HasPlayerEatenAllOpponentPieces;
import src.logic.gameMode.*;
import src.checkersGames.standardCheckers.piece.CheckersPieceFactory;


public class StandardCheckersGameModeFactory {
    public GameMode checkers(){
        DrawCondition[] drawConditions = {new InsufficientMaterial()};
        WinCondition[] winConditions = {new HasPlayerEatenAllOpponentPieces(), new CanOpponentNotMoveAnyPiece()};
        GameRule[] gameRules = {new MoveComplyWithJumpRule(), new EndOfDoubleJumpWithLessPieces()};
        OptionalGameRule[] optionalGameRules = {new Promote(new CheckersPieceFactory())};
        return new GameModeImplementation(winConditions, drawConditions, gameRules, optionalGameRules);
    }
}
