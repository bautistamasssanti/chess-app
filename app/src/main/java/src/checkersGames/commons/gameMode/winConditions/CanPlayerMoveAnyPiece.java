package src.checkersGames.commons.gameMode.winConditions;

import src.logic.Player;
import src.logic.Tile;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;

import java.util.ArrayList;
import java.util.List;

public class CanPlayerMoveAnyPiece implements WinCondition {
    @Override
    public boolean isGameWonByPlayer(Player player, List<GameState> gameStates) {
        List<Tile> playerTiles = gameStates.get(gameStates.size()-1).getBoard().getTeamTiles(player.getColor());
        for (Tile playerTile : playerTiles) {
            for(Tile destination : getAllBoardTiles(gameStates)){
                if (player.canMovePiece(playerTile, destination, gameStates) != MoveType.INVALID) {
                    return true;
                }
            }
        }
        return false;
    }
    private List<Tile> getAllBoardTiles(List<GameState> gameStates) {
        List<Tile> tiles = new ArrayList<>();
        int width = gameStates.get(gameStates.size() - 1).getBoard().getWidth();
        int length = gameStates.get(gameStates.size() - 1).getBoard().getLength();
        for(int x = 0; x < width; x++){
            for(int y = 0; y < length; y++){
                tiles.add(new Tile(x,y));
            }
        }
        return tiles;
    }
}
