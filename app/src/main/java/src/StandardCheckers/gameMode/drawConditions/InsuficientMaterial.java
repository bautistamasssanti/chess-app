package src.StandardCheckers.gameMode.drawConditions;

import src.logic.Player;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameMode.DrawCondition;
import src.logic.gameState.GameState;
import src.logic.piece.PieceType;

import java.util.List;

public class InsuficientMaterial implements DrawCondition {
    @Override
    public boolean isGameADraw(List<GameState> gameStates) {
        Board currentBoard = gameStates.get(gameStates.size()-1).getBoard();
        Player playerA = gameStates.get(0).getTeamAPlayer();
        Player playerB = gameStates.get(0).getTeamBPlayer();
        List<Tile> playerATiles = currentBoard.getTeamTiles(playerA.getColor());
        List<Tile> playerBTiles = currentBoard.getTeamTiles(playerB.getColor());
        if(playerATiles.size() == 1 && playerBTiles.size() == 1){
            return currentBoard.getBoard().get(playerATiles.get(0)).getType() == PieceType.QUEEN && currentBoard.getBoard().get(playerBTiles.get(0)).getType() == PieceType.QUEEN;
        }
        return false;
    }
}
