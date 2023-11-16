package src.InternationalCheckers.gameMode.winConditions;

import src.InternationalCheckers.gameState.CheckersGameStateFactory;
import src.logic.Player;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;

import java.util.List;

public class CanOpponentNotMoveAnyPiece implements WinCondition {
    private final CheckersGameStateFactory checkersGameStateFactory = new CheckersGameStateFactory();
    @Override
    public boolean isGameWonByPlayer(Player player, List<GameState> gameStates) {
        Player opponent = getOpponent(player, gameStates);
        List<GameState> switchedTurnGameStates = checkersGameStateFactory.switchTurn(gameStates,opponent.getColor());
        System.out.println("opponent color: " + opponent.getColor());
        System.out.println("currentTurn: " + gameStates.get(gameStates.size()-1).getCurrentTurnPlayer().getColor());
        return !new CanPlayerMoveAnyPiece().isGameWonByPlayer(opponent, switchedTurnGameStates);
    }
    private Player getOpponent(Player player, List<GameState> gameStates){
        if(gameStates.get(0).getTeamAPlayer().getColor() == player.getColor()){
            return gameStates.get(0).getTeamBPlayer();
        }
        return gameStates.get(0).getTeamAPlayer();
    }
}
