package src.logic.moveRules.basicRules.path;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;

import java.util.List;

public class IsRivalPieceNotInDestination implements MoveRule {
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        if(!board.containsPieceInTile(destination)){
            return MoveType.BASIC;
        }
        if(getPieceColor(origin,board) != getPieceColor(destination,board)){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
    private TeamColor getPieceColor(Tile tile, Board board){
        return board.getPiece(tile).getColor();
    }
}
