package src.internationalCheckers.gameMode.winConditions;

import src.logic.Player;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;

import java.util.List;

public class HasPlayerEatenAllOpponentPieces implements WinCondition {
    @Override
    public boolean isGameWonByPlayer(Player player, List<GameState> gameStates) {
        return getOpponentTilesList(player, gameStates).isEmpty();
    }
    private List<Tile> getOpponentTilesList(Player player, List<GameState> gameStates){
        Board currentBoard = gameStates.get(gameStates.size()-1).getBoard();
        if(player.getColor() == gameStates.get(0).getTeamAPlayer().getColor())
            return currentBoard.getTeamTiles(gameStates.get(0).getTeamBPlayer().getColor());
        else
            return currentBoard.getTeamTiles(gameStates.get(0).getTeamAPlayer().getColor());
    }
}
