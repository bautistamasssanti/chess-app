package src.logic.moveRules.basicRules.shape;

import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;
import src.logic.moveRules.MoveRule;

import java.util.List;

public class IsDiagonalMove implements MoveRule {
    ArithmethicOperation arithmethicOperation = new ArithmethicOperation();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        int xMovement = arithmethicOperation.getXMovement(origin, destination);
        int yMovement = arithmethicOperation.getYMovement(origin, destination);
        if((Math.abs(xMovement) == Math.abs(yMovement)) && (xMovement != 0)){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
}
