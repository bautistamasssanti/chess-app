package src.logic.gameMode;

import src.logic.gameState.GameState;

import java.util.List;

public interface DrawCondition {
    boolean isGameADraw(List<GameState> gameStates);
}