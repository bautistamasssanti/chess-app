package src.Checkers.gameMode.gameRules;

import src.logic.TeamColor;
import src.logic.gameMode.GameRule;
import src.logic.gameState.GameState;

import java.util.List;

public class MoveComplyWithJumpRule implements GameRule {

    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        TeamColor currentTurnColor = gameStates.get(gameStates.size() - 1).getColorTurn();
        TeamColor previousTurnColor = gameStates.get(gameStates.size() - 2).getColorTurn();
        return false;
    }

    @Override
    public String getGameRuleName() {
        return "MoveComplyWithJumpRule";
    }
}
