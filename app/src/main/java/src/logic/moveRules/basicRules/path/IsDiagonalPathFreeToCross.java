package src.logic.moveRules.basicRules.path;

import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;

import java.util.List;

public class IsDiagonalPathFreeToCross implements MoveRule {
    private final ArithmethicOperation arithmethicOperation = new ArithmethicOperation();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        int xMovement = arithmethicOperation.getXMovement(origin, destination);
        int yMovement = arithmethicOperation.getYMovement(origin, destination);
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        if((xMovement > 0) && (yMovement > 0)){
            return rightUpwardsChecker(origin, destination, board);
        } else if((xMovement > 0) && (yMovement < 0)){
            return rightDownwardsChecker(origin, destination, board);
        } else if((xMovement < 0) && (yMovement > 0)){
            return leftUpwardsChecker(origin, destination, board);
        } else if((xMovement < 0) && (yMovement < 0)){
            return leftDownwardsChecker(origin, destination, board);
        }
        return MoveType.INVALID;
    }
    private MoveType rightUpwardsChecker(Tile origin, Tile destination, Board board){
        for(int x = origin.getX() + 1, y = origin.getY() + 1; x < destination.getX() && y < destination.getY(); x++, y++){
            if(board.containsPieceInTile(new Tile(x, y))){
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
    private MoveType rightDownwardsChecker(Tile origin, Tile destination, Board board){
        for(int x = origin.getX() + 1, y = origin.getY() - 1; x < destination.getX() && y > destination.getY(); x++, y--){
            if(board.containsPieceInTile(new Tile(x, y))){
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
    private MoveType leftUpwardsChecker(Tile origin, Tile destination, Board board){
        for(int x = origin.getX() - 1, y = origin.getY() + 1; x > destination.getX() && y < destination.getY(); x--, y++){
            if(board.containsPieceInTile(new Tile(x, y))){
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
    private MoveType leftDownwardsChecker(Tile origin, Tile destination, Board board){
        for(int x = origin.getX() - 1, y = origin.getY() - 1; x > destination.getX() && y > destination.getY(); x--, y--){
            if(board.containsPieceInTile(new Tile(x, y))){
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
}
