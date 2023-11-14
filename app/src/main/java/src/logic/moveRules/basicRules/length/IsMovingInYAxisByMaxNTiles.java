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
        int YMovement = arithmethicOperation.getYMovement(origin, destination);
        if(Math.abs(YMovement) <= maxTiles){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
}
