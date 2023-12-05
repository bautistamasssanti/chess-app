package movement.basicMovesTests.direction;

import movement.BoardParser;
import org.junit.jupiter.api.Test;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.direction.IsMovingRight;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public class IsMovingRightTest {
    public BoardParser parser = new BoardParser();
    private final MoveRule[] moveRules = {new IsMovingRight()};
    Piece white = new Piece(PieceType.PAWN, TeamColor.WHITE, moveRules,1);
    Piece black = new Piece(PieceType.PAWN, TeamColor.BLACK, moveRules,1);
    @Test
    public void IsWhiteMovingRightTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/direction/IsWhiteMovingRight";
        boolean result = parser.parseChessBoardFormFile(filepath,white,new Tile(1,0));
        org.junit.jupiter.api.Assertions.assertTrue(result);
    }
    @Test
    public void IsBlackMovingRightTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/direction/IsBlackMovingRight";
        boolean result = parser.parseChessBoardFormFile(filepath,black,new Tile(6,0));
        org.junit.jupiter.api.Assertions.assertTrue(result);
    }
}
