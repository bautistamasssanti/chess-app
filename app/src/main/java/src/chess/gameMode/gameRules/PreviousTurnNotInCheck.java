package src.chess.gameMode.gameRules;

import src.logic.gameMode.GameRule;
import src.logic.gameState.GameState;

import java.util.List;

public class PreviousTurnNotInCheck implements GameRule {
    private final IsCurrentTurnNotInCheck isCurrentTurnNotInCheck = new IsCurrentTurnNotInCheck();
    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        if(gameStates.size() < 2){
            return false;
        }

        return isCurrentTurnNotInCheck.isGameRuleValid(gameStates.subList(0, gameStates.size() - 1));
    }

    @Override
    public String getGameRuleName() {
        return "PreviousTurnInCheck";
    }
}
