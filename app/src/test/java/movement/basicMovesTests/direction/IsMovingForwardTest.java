package movement.basicMovesTests.direction;

import movement.BoardParser;


import org.junit.jupiter.api.Test;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.direction.IsMovingForward;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsMovingForwardTest {
    public BoardParser parser = new BoardParser();
    private final MoveRule[] moveRules = {new IsMovingForward()};
    Piece white = new Piece(PieceType.PAWN, TeamColor.WHITE, moveRules,1);
    Piece black = new Piece(PieceType.PAWN, TeamColor.BLACK, moveRules,1);
    @Test
    public void isWhiteMovingForwardTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/direction/IsWhiteMovingForward";
        boolean result = parser.parseChessBoardFormFile(filepath,white,new Tile(3,1));
        assertTrue(result);
    }
    @Test
    public void isBlackMovingForwardTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/direction/IsBlackMovingForward";
        boolean result = parser.parseChessBoardFormFile(filepath,black,new Tile(3,6));
        assertTrue(result);
    }
}
