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
            for(int i = origin.getX() + 1; i < destination.getX(); i++){
                if(board.getBoard().get(new Tile(i, origin.getY())) != null){
                    return MoveType.INVALID;
                }
            }
            return MoveType.BASIC;
        }
        else if(xMovement < 0){
            for(int i = origin.getX() - 1; i > destination.getX(); i--){
                if(board.getBoard().get(new Tile(i, origin.getY())) != null){
                    return MoveType.INVALID;
                }
            }
            return MoveType.BASIC;
        }
        else if(yMovement > 0){
            for(int i = origin.getY() + 1; i < destination.getY(); i++){
                if(board.getBoard().get(new Tile(origin.getX(), i)) != null){
                    return MoveType.INVALID;
                }
            }
            return MoveType.BASIC;
        }
        else if(yMovement < 0){
            for(int i = origin.getY() - 1; i > destination.getY(); i--){
                if(board.getBoard().get(new Tile(origin.getX(), i)) != null){
                    return MoveType.INVALID;
                }
            }
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
}
