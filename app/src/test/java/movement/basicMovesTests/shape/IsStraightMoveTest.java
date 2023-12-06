package movement.basicMovesTests.shape;

import movement.BoardParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.logic.TeamColor;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.shape.IsStraightMove;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public class IsStraightMoveTest {
    public BoardParser parser = new BoardParser();
    private final MoveRule[] moveRules = {new IsStraightMove()};
    Piece white = new Piece(PieceType.PAWN, TeamColor.WHITE, moveRules,1);
    @Test
    public void isStraightMoveTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/shape/IsStraightMove";
        boolean result = parser.parseChessBoardFormFile(filepath,white,new src.logic.Tile(3,3));
        Assertions.assertTrue(result);
    }
}