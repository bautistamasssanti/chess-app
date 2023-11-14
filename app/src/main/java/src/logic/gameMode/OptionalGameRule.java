package src.logic.gameMode;

import src.logic.gameState.GameState;

import java.util.List;

public interface OptionalGameRule {
    List<GameState> isOptionalConditionulfilled(List<GameState> gameStates);
}
