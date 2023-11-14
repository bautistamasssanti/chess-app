package src.Chess.gameMode.WinConditions;

import src.logic.Player;
import src.logic.Tile;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;

import java.util.List;

public class Extintion implements WinCondition {
    @Override
    public boolean isGameWonByPlayer(Player player, List<GameState> gameStates) {
        List<Tile> teamAOccupiedTiles = gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor());
        List<Tile> teamBOccupiedTiles = gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(gameStates.get(gameStates.size() - 1).getTeamBPlayer().getColor());
        if(isPlayerTeamA(player, gameStates) && teamBOccupiedTiles.isEmpty()){
            return true;
        } else return !isPlayerTeamA(player, gameStates) && teamAOccupiedTiles.isEmpty();
    }
    private boolean isPlayerTeamA(Player player, List<GameState> gameStates) {
        return player.getColor() == gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor();
    }
}
