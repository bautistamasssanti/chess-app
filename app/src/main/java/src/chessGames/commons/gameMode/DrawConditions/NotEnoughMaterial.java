package src.chessGames.commons.gameMode.DrawConditions;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.gameMode.DrawCondition;
import src.logic.gameState.GameState;

import java.util.List;

public class NotEnoughMaterial implements DrawCondition {
    @Override
    public boolean isGameADraw(List<GameState> gameStates) {
        GameState gameState = gameStates.get(gameStates.size() - 1);
        TeamColor teamAColor = gameState.getTeamAPlayer().getColor();
        List<Tile> teamATiles = gameState.getBoard().getTeamTiles(teamAColor);
        TeamColor teamBColor = gameState.getTeamBPlayer().getColor();
        List<Tile> teamBTiles = gameState.getBoard().getTeamTiles(teamBColor);
        return checkConditions(teamATiles, teamBTiles);
    }
    private boolean checkConditions(List<Tile> teamATiles, List<Tile> teamBTiles){
        if(teamATiles.size() <= 2){
            if(teamBTiles.size() == 1)
                return true;
        }
        if(teamBTiles.size() <= 2){
            if(teamATiles.size() == 1)
                return true;
        }
        return false;
    }
}
