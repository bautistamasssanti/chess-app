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
        if((Math.abs(xMovement) == side_A && Math.abs(yMovement) == side_B) || (Math.abs(xMovement) == side_B && Math.abs(yMovement) == side_A)){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
}
