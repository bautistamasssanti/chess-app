package src.chessGames.commons;

import src.logic.TeamColor;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public interface PieceFactory {
    Piece GetNewPieceByEnum(PieceType pieceType, TeamColor color, int id);
}
