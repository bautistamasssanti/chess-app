package src.checkersGames.commons;

import src.logic.TeamColor;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public interface PieceFactory {
    Piece getNewPieceByEnum(PieceType pieceType, TeamColor color, int id);
}
