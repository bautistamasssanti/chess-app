package src.logic.moveRules.basicRules.path;

import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;

import java.util.List;

public class IsStraightPathFreeToCross implements MoveRule {
    private final ArithmethicOperation arithmethicOperation = new ArithmethicOperation();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        int xMovement = arithmethicOperation.getXMovement(origin, destination);
        int yMovement = arithmethicOperation.getYMovement(origin, destination);
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        if(xMovement > 0){
            return rightChecker(origin, destination, board);
        } else if(xMovement < 0){
            return leftChecker(origin, destination, board);
        } else if(yMovement > 0){
            return upwardsChecker(origin, destination, board);
        } else if(yMovement < 0){
            return downwardsChecker(origin, destination, board);
        }
        return MoveType.INVALID;
    }
    private MoveType rightChecker(Tile origin, Tile destination, Board board){
        for(int x = origin.getX() + 1; x < destination.getX(); x++){
            if(board.getBoard().containsKey(new Tile(x, origin.getY()))){
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
    private MoveType leftChecker(Tile origin, Tile destination, Board board){
        for(int x = origin.getX() - 1; x > destination.getX(); x--){
            if(board.getBoard().containsKey(new Tile(x, origin.getY()))){
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
    private MoveType upwardsChecker(Tile origin, Tile destination, Board board){
        for(int y = origin.getY() + 1; y < destination.getY(); y++){
            if(board.getBoard().containsKey(new Tile(origin.getX(), y))){
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
    private MoveType downwardsChecker(Tile origin, Tile destination, Board board){
        for(int y = origin.getY() - 1; y > destination.getY(); y--){
            if(board.getBoard().containsKey(new Tile(origin.getX(), y))){
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
}
