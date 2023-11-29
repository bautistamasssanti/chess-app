package src.logic.moveRules.basicRules.path;

import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;

import java.util.List;

public class IsDestinationTileOccupied implements MoveRule {
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        if(board.getBoard().containsKey(destination)){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
}
