package src.standardCheckers.piece;

import src.standardCheckers.moveRules.CheckersMoveRulesFactory;
import src.logic.TeamColor;
import src.logic.moveRules.MoveRule;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public class CheckersPieceFactory {
    CheckersMoveRulesFactory checkersMoveRulesFactory = new CheckersMoveRulesFactory();
    public Piece getNewPieceByEnum(PieceType pieceType, TeamColor color, int id){
        return switch (pieceType) {
            case MAN -> man(color, id);
            case QUEEN -> Queen(color, id);
            default -> throw new IllegalArgumentException("Invalid PieceType");
        };
    }
    public Piece man(TeamColor color, int id){
        MoveRule[] moveRules = {checkersMoveRulesFactory.manStandardMoveRule(), checkersMoveRulesFactory.manJump()};
        return new Piece(PieceType.MAN,color,moveRules,id);
    }
    public Piece Queen(TeamColor color, int id){
        MoveRule[] moveRules = {checkersMoveRulesFactory.queenStandardMoveRule(), checkersMoveRulesFactory.queenJump()};
        return new Piece(PieceType.QUEEN,color,moveRules,id);
    }
}
