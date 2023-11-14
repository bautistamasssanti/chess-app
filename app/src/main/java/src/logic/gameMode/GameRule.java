package src.logic.gameMode;

import src.logic.gameState.GameState;

import java.util.List;

public interface GameRule {
    boolean isGameRuleValid(List<GameState> gameStates);
    String getGameRuleName();
}
