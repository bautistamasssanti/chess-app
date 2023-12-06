package src.chessGames.chess.piece;

import src.chessGames.commons.PieceFactory;
import src.logic.TeamColor;
import src.logic.moveRules.MoveRule;
import src.chessGames.chess.MoveRules.ChessRuleFactory;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public class ChessPieceFactory implements PieceFactory {
    private final ChessRuleFactory chessRuleFactory = new ChessRuleFactory();
    public Piece GetNewPieceByEnum(PieceType pieceType, TeamColor color, int id){
        switch (pieceType){
            case ROOK:
                return Rook(color, id);
            case BISHOP:
                return Bishop(color, id);
            case KNIGHT:
                return Knight(color, id);
            case QUEEN:
                return Queen(color, id);
            case KING:
                return King(color, id);
            case PAWN:
                return Pawn(color, id);
            default:
                throw new IllegalArgumentException("Invalid PieceType");
        }
    }
    public Piece Rook(TeamColor color, int id){
        MoveRule[] moveRules = {chessRuleFactory.RookStraightMoveRule()};
        return new Piece(PieceType.ROOK, color, moveRules, id);
    }
    public Piece Bishop(TeamColor color, int id){
        MoveRule[] moveRules = {chessRuleFactory.BishopDiagonalMoveRule()};
        return new Piece(PieceType.BISHOP, color, moveRules, id);
    }
    public Piece Knight(TeamColor color, int id){
        MoveRule[] moveRules = {chessRuleFactory.KnightMoveRule()};
        return new Piece(PieceType.KNIGHT, color, moveRules, id);
    }
    public Piece Queen(TeamColor color, int id){
        MoveRule[] moveRules = {chessRuleFactory.QueenStraightMoveRule(), chessRuleFactory.QueenDiagonalMoveRule()};
        return new Piece(PieceType.QUEEN, color, moveRules, id);
    }
    public Piece King(TeamColor color, int id){
        MoveRule[] moveRules = {chessRuleFactory.KingMoveRule(), chessRuleFactory.CastledKingSideRule(), chessRuleFactory.CastledQueenSideRule()};
        return new Piece(PieceType.KING, color, moveRules, id);
    }
    public Piece Pawn(TeamColor color, int id){
        MoveRule[] moveRules = {chessRuleFactory.PawnMoveRule(), chessRuleFactory.DoubleMovePawnMoveRule(), chessRuleFactory.DiagonalPawnMoveRule()};
        return new Piece(PieceType.PAWN, color, moveRules, id);
    }
}
