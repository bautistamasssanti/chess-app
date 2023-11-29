package src.logic.moveRules.basicRules.shape;

import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;
import src.logic.moveRules.MoveRule;

import java.util.List;

public class IsStraightMove implements MoveRule {
    private final ArithmethicOperation arithmethicOperation = new ArithmethicOperation();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        int xMovement = arithmethicOperation.getXMovement(origin, destination);
        int yMovement = arithmethicOperation.getYMovement(origin, destination);
        if(checksConditions(xMovement, yMovement)){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
    private boolean checksConditions(int xMovement, int yMovement){
        if(xMovement == 0){
            if(yMovement != 0){
                return true;
            }
        }
        if (xMovement != 0){
            if(yMovement == 0){
                return true;
            }
        }
        return false;
    }
}
