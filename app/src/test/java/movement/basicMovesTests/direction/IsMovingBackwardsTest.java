package movement.basicMovesTests.direction;

import movement.BoardParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.direction.IsMovingBackwards;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;


public class IsMovingBackwardsTest {
    public BoardParser parser = new BoardParser();
    private final MoveRule[] moveRules = {new IsMovingBackwards()};
    Piece white = new Piece(PieceType.PAWN, TeamColor.WHITE, moveRules,1);
    Piece black = new Piece(PieceType.PAWN, TeamColor.BLACK, moveRules,1);
    @Test
    public void IsWhiteMovingBackwardsTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/direction/IsWhiteMovingBackwards";
        boolean result = parser.parseChessBoardFormFile(filepath,white,new Tile(3,6));
        Assertions.assertTrue(result);
    }
    @Test
    public void IsBlackMovingBackwardsTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/direction/IsBlackMovingBackwards";
        boolean result = parser.parseChessBoardFormFile(filepath,black,new Tile(3,1));
        Assertions.assertTrue(result);
    }
}
