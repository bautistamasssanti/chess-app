package src.logic.moveRules;

import src.logic.gameState.GameState;
import src.logic.Tile;

import java.util.List;

public interface MoveRule {
    MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates);
}
