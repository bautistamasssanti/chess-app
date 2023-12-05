package movement.basicMovesTests.direction;

import movement.BoardParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.direction.IsMovingLeft;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public class IsMovingLeftTest {
    public BoardParser parser = new BoardParser();
    private final MoveRule[] moveRules = {new IsMovingLeft()};
    Piece white = new Piece(PieceType.PAWN, TeamColor.WHITE, moveRules,1);
    Piece black = new Piece(PieceType.PAWN, TeamColor.BLACK, moveRules,1);
    @Test
    public void IsWhiteMovingLeftTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/direction/IsWhiteMovingLeft";
        boolean result = parser.parseChessBoardFormFile(filepath,white, new Tile(6,0));
        Assertions.assertTrue(result);
    }
    @Test
    public void IsBlackMovingLeftTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/direction/IsBlackMovingLeft";
        boolean result = parser.parseChessBoardFormFile(filepath,black, new Tile(1,0));
        Assertions.assertTrue(result);
    }
}
