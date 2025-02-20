package src.chessGames.commons.gameMode.gameRules;

import src.logic.gameState.GameState;
import src.logic.Player;
import src.logic.gameMode.GameRule;

import java.util.List;

public class IsCurrentTurnNotInCheck implements GameRule {
    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        Player playerToReview = getPlayerToReview(gameStates);
        IsPlayerNotInCheck isPlayerNotInCheck = new IsPlayerNotInCheck(playerToReview);
        return isPlayerNotInCheck.isGameRuleValid(gameStates);
    }

    @Override
    public String getGameRuleName() {
        return "Check";
    }
    private Player getPlayerToReview(List<GameState> gameStates){
        GameState previousGameState = gameStates.get(gameStates.size() - 2);
        if (previousGameState.getColorTurn() == previousGameState.getTeamAPlayer().getColor()) {
            return gameStates.get(gameStates.size() - 1).getTeamAPlayer();
        } else {
            return gameStates.get(gameStates.size() - 1).getTeamBPlayer();
        }
    }
}
