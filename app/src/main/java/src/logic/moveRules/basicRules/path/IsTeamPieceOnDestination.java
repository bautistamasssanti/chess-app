package src.logic.moveRules.basicRules.path;

import src.logic.TeamColor;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;

import java.util.List;

public class IsTeamPieceOnDestination implements MoveRule {

    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        try {
            TeamColor originColor = board.getPiece(origin).getColor();
            TeamColor destinationColor = board.getPiece(destination).getColor();
            if(originColor == destinationColor)
                return MoveType.BASIC;
            return MoveType.INVALID;
        } catch (NullPointerException e) {
            return MoveType.INVALID;
        }
    }
}
