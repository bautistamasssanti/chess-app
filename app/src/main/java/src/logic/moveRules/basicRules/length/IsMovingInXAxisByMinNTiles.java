package src.logic.moveRules.basicRules.length;

import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;

import java.util.List;

public class IsMovingInXAxisByMinNTiles implements MoveRule {
    private final int minTiles;
    private final ArithmethicOperation arithmethicOperation = new ArithmethicOperation();

    public IsMovingInXAxisByMinNTiles(int minTiles) {
        this.minTiles = minTiles;
    }

    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        int xMovement = arithmethicOperation.getXMovement(origin, destination);
        if(Math.abs(xMovement) >= minTiles){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
}
