package src.logic.moveRules;

import src.logic.gameState.GameState;
import src.logic.Tile;

import java.util.List;

public class ComposedMoveRule implements MoveRule{
    private final MoveRule[] moveRules;

    public ComposedMoveRule(MoveRule[] moveRules) {
        this.moveRules = moveRules;
    }

    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        for (MoveRule moveRule : moveRules) {
            if (moveRule.isValidMove(origin, destination, gameStates) == MoveType.INVALID) {
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
}
