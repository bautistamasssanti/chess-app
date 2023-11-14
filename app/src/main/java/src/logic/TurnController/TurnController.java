package src.logic.TurnController;

import src.logic.Player;
import src.logic.Tile;
import src.logic.gameState.GameState;

import java.util.List;

public interface TurnController {
    List<GameState> applyMove(Player player, Tile origin, Tile destination, List<GameState> gameStates);
}
