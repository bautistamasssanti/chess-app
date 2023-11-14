package src.logic.gameMode;

import src.logic.gameState.GameState;
import src.logic.Player;

import java.util.List;

public interface WinCondition {
    boolean isGameWonByPlayer(Player player, List<GameState> gameStates);
}
