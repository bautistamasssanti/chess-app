package movement.basicMovesTests.path;

import movement.BoardParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.length.IsMovingInXAxisByMinNTiles;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

public class IsMovingInXAxisByMinNTilesTest {
    public BoardParser parser = new BoardParser();
    private final MoveRule[] moveRules = {new IsMovingInXAxisByMinNTiles(3)};
    Piece white = new Piece(PieceType.PAWN, TeamColor.WHITE, moveRules,1);
    @Test
    public void IsMovingInXAxisByMinNTilesRightTest() throws Exception {
        String filepath = "src/test/java/testBoards/basicMoves/length/IsMovingInXAxisByMinNTiles";
        boolean result = parser.parseChessBoardFormFile(filepath,white,new Tile(3,3));
        Assertions.assertTrue(result);
    }
}
