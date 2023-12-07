package src.logic.moveRules.basicRules.path;

import src.logic.TeamColor;
import src.logic.board.Board;
import src.logic.Tile;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;

import java.util.List;

public class IsDestinationFreeOfTeamPiece implements MoveRule {
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        if(checksConditions(origin, destination, board)){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
    private boolean checksConditions(Tile origin, Tile destination, Board board){
        if (!board.containsPieceInTile(destination)) {
            return true;
        }
        if(board.containsPieceInTile(origin)){
            TeamColor originColor = board.getPiece(origin).getColor();
            TeamColor destinationColor = board.getPiece(destination).getColor();
            return originColor != destinationColor;
        }
        return false;
    }
}