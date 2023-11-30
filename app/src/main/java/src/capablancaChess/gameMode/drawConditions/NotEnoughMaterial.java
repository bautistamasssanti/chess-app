package src.capablancaChess.gameMode.drawConditions;

import src.logic.Tile;
import src.logic.gameMode.DrawCondition;
import src.logic.gameState.GameState;

import java.util.List;

public class NotEnoughMaterial implements DrawCondition {
    @Override
    public boolean isGameADraw(List<GameState> gameStates) {
        GameState gameState = gameStates.get(gameStates.size() - 1);
        List<Tile> teamATiles = gameState.getBoard().getTeamTiles(gameState.getTeamAPlayer().getColor());
        List<Tile> teamBTiles = gameState.getBoard().getTeamTiles(gameState.getTeamBPlayer().getColor());
        return checkConditions(gameStates, teamATiles, teamBTiles);
    }
    private boolean checkConditions(List<GameState> gameStates, List<Tile> teamATiles, List<Tile> teamBTiles){
        if(teamATiles.size() <= 2 && teamBTiles.size() == 1){
            return true;
        }
        if(teamBTiles.size() <= 2 && teamATiles.size() == 1){
            return true;
        }
        return false;
    }
}
