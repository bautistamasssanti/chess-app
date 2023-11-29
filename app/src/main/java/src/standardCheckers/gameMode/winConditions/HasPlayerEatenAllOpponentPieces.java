package src.standardCheckers.gameMode.winConditions;

import src.logic.Player;
import src.logic.Tile;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;

import java.util.List;

public class HasPlayerEatenAllOpponentPieces implements WinCondition {
    @Override
    public boolean isGameWonByPlayer(Player player, List<GameState> gameStates) {
        return getOpponentTilesList(player, gameStates).isEmpty();
    }
    private List<Tile> getOpponentTilesList(Player player, List<GameState> gameStates){
        if(player.getColor() == gameStates.get(0).getTeamAPlayer().getColor())
            return gameStates.get(gameStates.size()-1).getBoard().getTeamTiles(gameStates.get(0).getTeamBPlayer().getColor());
        else
            return gameStates.get(gameStates.size()-1).getBoard().getTeamTiles(gameStates.get(0).getTeamAPlayer().getColor());
    }
}
