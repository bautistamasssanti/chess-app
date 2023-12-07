package src.chessGames.commons.moveRules.specialRules;

import src.logic.board.Board;
import src.logic.moveRules.MoveType;
import src.logic.piece.Piece;
import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.state.IsPieceWithoutPreviousMovement;
import src.logic.moveRules.basicRules.path.IsStraightPathFreeToCross;

import java.util.List;

import static src.logic.piece.PieceType.ROOK;

public class CastledKingsideRule implements MoveRule {
    private final IsPieceWithoutPreviousMovement isPieceWithoutPreviousMovement = new IsPieceWithoutPreviousMovement();
    private final IsStraightPathFreeToCross isStraightPathFreeToCross = new IsStraightPathFreeToCross();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        try{
            if(checkConditions(origin, destination, gameStates)){
                return MoveType.SPECIAL1;
            }
            return MoveType.INVALID;
        } catch (Exception e){
            return MoveType.INVALID;
        }
    }
    private boolean checkConditions(Tile origin, Tile destination, List<GameState> gameStates){
        if(!checkValidCoordinates(origin, destination, gameStates))
            return false;
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        Tile castlingPieceTile = new Tile(board.getWidth() - 1, origin.getY());
        Piece castlingPiece = board.getPiece(castlingPieceTile);
        if(!castlingPiece.getType().equals(ROOK))
            return false;
        if(!checkPiecesWithoutMovement(origin, castlingPieceTile, destination, gameStates))
            return false;
        if(isStraightPathFreeToCross.isValidMove(origin, castlingPieceTile, gameStates) == MoveType.INVALID)
            return false;
        return true;
    }
    private boolean checkValidCoordinates(Tile origin, Tile destination, List<GameState> gameStates){
        if(origin.getY() != destination.getY()){
            return false;
        }
        if(destination.getX() != gameStates.get(gameStates.size() - 1).getBoard().getWidth() - 2){
            return false;
        }
        return true;
    }
    private boolean checkPiecesWithoutMovement(Tile origin, Tile castlingPieceTile, Tile destination, List<GameState> gameStates){
        if(isPieceWithoutPreviousMovement.isValidMove(origin, destination, gameStates) == MoveType.INVALID){
            return false;
        }
        if(isPieceWithoutPreviousMovement.isValidMove(castlingPieceTile, destination, gameStates) == MoveType.INVALID){
            return false;
        }
        return true;
    }
}
