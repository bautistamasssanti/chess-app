package src.capablancaChess.piece;

import src.capablancaChess.moveRules.CapablancaMoveRulesFactoy;
import src.logic.TeamColor;
import src.logic.moveRules.MoveRule;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public class CapablancaPieceFactory {
    private final CapablancaMoveRulesFactoy capablancaMoveRulesFactory = new CapablancaMoveRulesFactoy();
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
            case ARCHBISHOP:
                return Archbishop(color, id);
            case CHANCELLOR:
                return Chancellor(color, id);
            default:
                throw new IllegalArgumentException("Invalid PieceType");

        }
}

    public Piece Pawn(TeamColor color, int id) {
        MoveRule[] moveRules = {capablancaMoveRulesFactory.PawnMoveRule(), capablancaMoveRulesFactory.DoubleMovePawnMoveRule(), capablancaMoveRulesFactory.DiagonalPawnMoveRule()};
        return new Piece(PieceType.PAWN, color, moveRules, id);
    }

    public Piece King(TeamColor color, int id) {
        MoveRule[] moveRules = {capablancaMoveRulesFactory.KingMoveRule(), capablancaMoveRulesFactory.CastledKingSideRule(), capablancaMoveRulesFactory.CastledQueenSideRule()};
        return new Piece(PieceType.KING, color, moveRules, id);
    }

    public Piece Queen(TeamColor color, int id) {
        MoveRule[] moveRules = {capablancaMoveRulesFactory.QueenStraightMoveRule(), capablancaMoveRulesFactory.QueenDiagonalMoveRule()};
        return new Piece(PieceType.QUEEN, color, moveRules, id);

    }

    public Piece Knight(TeamColor color, int id) {
        MoveRule[] moveRules = {capablancaMoveRulesFactory.KnightMoveRule()};
        return new Piece(PieceType.KNIGHT, color, moveRules, id);
    }

    public Piece Bishop(TeamColor color, int id) {
        MoveRule[] moveRules = {capablancaMoveRulesFactory.BishopDiagonalMoveRule()};
        return new Piece(PieceType.BISHOP, color, moveRules, id);
    }

    public Piece Rook(TeamColor color, int id) {
        MoveRule[] moveRules = {capablancaMoveRulesFactory.RookStraightMoveRule()};
        return new Piece(PieceType.ROOK, color, moveRules, id);
    }
    public Piece Archbishop(TeamColor color, int id) {
        MoveRule[] moveRules = {capablancaMoveRulesFactory.ArchbishopDiagonalMoveRule(), capablancaMoveRulesFactory.ArchbishopKnightMoveRule()};
        return new Piece(PieceType.ARCHBISHOP, color, moveRules, id);
    }
    public Piece Chancellor(TeamColor color, int id) {
        MoveRule[] moveRules = {capablancaMoveRulesFactory.ChancellorStraightMoveRule(), capablancaMoveRulesFactory.ChancellorKnightMoveRule()};
        return new Piece(PieceType.CHANCELLOR, color, moveRules, id);
    }
}
