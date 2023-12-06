package src.checkersGames.commons.gameMode.winConditions;

import src.logic.Player;
import src.logic.TeamColor;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CanOpponentNotMoveAnyPiece implements WinCondition {
    @Override
    public boolean isGameWonByPlayer(Player player, List<GameState> gameStates) {
        Player opponent = getOpponent(player, gameStates);
        List<GameState> switchedTurnGameStates = switchTurn(gameStates,opponent.getColor());
        return !new CanPlayerMoveAnyPiece().isGameWonByPlayer(opponent, switchedTurnGameStates);
    }
    private Player getOpponent(Player player, List<GameState> gameStates){
        if(gameStates.get(0).getTeamAPlayer().getColor() == player.getColor()){
            return gameStates.get(0).getTeamBPlayer();
        }
        return gameStates.get(0).getTeamAPlayer();
    }
    private List<GameState> switchTurn(List<GameState> gameStates, TeamColor colorToSwitch){
        GameState newGameState = new GameState(gameStates.get(gameStates.size() - 1).getBoard(), gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), colorToSwitch);
        List<GameState> newHistory = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1));
        newHistory.add(newHistory.size(), newGameState);
        return Collections.unmodifiableList(newHistory);
    }
}
