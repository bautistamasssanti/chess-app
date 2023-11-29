package src.internationalCheckers.gameMode.winConditions;

import src.logic.Player;
import src.logic.Tile;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;

import java.util.List;

public class CanPlayerMoveAnyPiece implements WinCondition {
    @Override
    public boolean isGameWonByPlayer(Player player, List<GameState> gameStates) {
        List<Tile> playerTiles = gameStates.get(gameStates.size()-1).getBoard().getTeamTiles(player.getColor());
        for (Tile playerTile : playerTiles) {
            for (int x = 0; x < gameStates.get(gameStates.size() - 1).getBoard().getWidth(); x++) {
                for (int y = 0; y < gameStates.get(gameStates.size() - 1).getBoard().getLength(); y++) {
                    Tile destination = new Tile(x, y);
                    if (player.CanMovePiece(playerTile, destination, gameStates) != MoveType.INVALID) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
