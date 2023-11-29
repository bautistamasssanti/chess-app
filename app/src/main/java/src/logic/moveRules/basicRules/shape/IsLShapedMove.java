package src.logic.moveRules.basicRules.shape;

import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;
import src.logic.moveRules.MoveRule;

import java.util.List;

public class IsLShapedMove implements MoveRule {
    private final int side_A;
    private final int side_B;
    ArithmethicOperation arithmethicOperation = new ArithmethicOperation();

    public IsLShapedMove(int side_A,int side_B){
        this.side_A = side_A;
        this.side_B = side_B;
    }

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
        if(arithmethicOperation.getAbsoluteValue(xMovement) == side_A){
            if(arithmethicOperation.getAbsoluteValue(yMovement) == side_B){
                return true;
            }
        }
        if(arithmethicOperation.getAbsoluteValue(xMovement) == side_B){
            if(arithmethicOperation.getAbsoluteValue(yMovement) == side_A){
                return true;
            }
        }
        return false;
    }
}
