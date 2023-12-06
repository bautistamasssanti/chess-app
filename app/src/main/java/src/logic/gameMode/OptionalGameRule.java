package src.logic.gameMode;

import src.logic.gameState.GameState;

import java.util.List;

public interface OptionalGameRule {
    List<GameState> isOptionalConditionfulfilled(List<GameState> gameStates);
}
