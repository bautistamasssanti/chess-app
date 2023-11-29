package src.chess.gameMode.DrawConditions;

import src.logic.Tile;
import src.logic.gameMode.DrawCondition;
import src.logic.gameState.GameState;

import java.util.List;

public class NotEnoughMaterial implements DrawCondition {
    @Override
    public boolean isGameADraw(List<GameState> gameStates) {
        List<Tile> teamAOccupiedTiles = gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor());
        List<Tile> teamBOccupiedTiles = gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(gameStates.get(gameStates.size() - 1).getTeamBPlayer().getColor());
        return teamAOccupiedTiles.size() <= 2 && teamBOccupiedTiles.size() == 1 || teamBOccupiedTiles.size() <= 2 && teamAOccupiedTiles.size() == 1;
    }
}
