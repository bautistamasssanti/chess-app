package src.internationalCheckers.gameMode.drawConditions;

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
        return checksConditions(currentBoard, playerA, playerB);
    }
    private boolean checksConditions(Board board, Player playerA, Player playerB){
        List<Tile> playerATiles = board.getTeamTiles(playerA.getColor());
        List<Tile> playerBTiles = board.getTeamTiles(playerB.getColor());
        if(playerATiles.size() != 1){
            return false;
        }
        if(playerBTiles.size() != 1){
            return false;
        }
        if(board.getBoard().get(playerATiles.get(0)).getType() != PieceType.QUEEN){
            return false;
        }
        if(board.getBoard().get(playerBTiles.get(0)).getType() != PieceType.QUEEN){
            return false;
        }
        return true;
    }
}
