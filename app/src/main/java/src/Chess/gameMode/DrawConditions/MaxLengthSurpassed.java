package src.Chess.gameMode.DrawConditions;

import src.logic.gameMode.DrawCondition;
import src.logic.gameState.GameState;

import java.util.List;

public class MaxLengthSurpassed implements DrawCondition {
    private final int maxLength;

    public MaxLengthSurpassed(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean isGameADraw(List<GameState> gameStates) {
        return gameStates.size() > maxLength;
    }
}
