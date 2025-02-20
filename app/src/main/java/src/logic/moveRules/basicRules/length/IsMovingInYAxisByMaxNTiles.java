package src.logic.moveRules.basicRules.length;

import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;

import java.util.List;

public class IsMovingInYAxisByMaxNTiles implements MoveRule {
    private final int maxTiles;
    private final ArithmethicOperation arithmethicOperation = new ArithmethicOperation();

    public IsMovingInYAxisByMaxNTiles(int maxTiles) {
        this.maxTiles = maxTiles;
    }
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        int yMovement = arithmethicOperation.getYMovement(origin, destination);
        if(arithmethicOperation.getAbsoluteValue(yMovement) <= maxTiles){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
}
