package src.capablancaChess.moveRules.specialRules;

import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.path.IsStraightPathFreeToCross;
import src.logic.moveRules.basicRules.state.IsPieceWithoutPreviousMovement;
import src.logic.piece.Piece;

import java.util.List;

import static src.logic.piece.PieceType.ROOK;

public class CastledQueensideRule implements MoveRule {
    private final IsPieceWithoutPreviousMovement isPieceWithoutPreviousMovement = new IsPieceWithoutPreviousMovement();
    private final IsStraightPathFreeToCross isStraightPathFreeToCross = new IsStraightPathFreeToCross();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        try{
            if(checkConditions(origin, destination, gameStates)){
                return MoveType.SPECIAL2;
            }
            return MoveType.INVALID;
        }catch(Exception e){
            return MoveType.INVALID;
        }
    }
    private boolean checkConditions(Tile origin, Tile destination, List<GameState> gameStates){
        if(!checkValidCoordinates(origin, destination, gameStates)){
            return false;
        }
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        Tile castlingPieceTile = new Tile(0, origin.getY());
        Piece castlingPiece = board.getBoard().get(castlingPieceTile);
        if(!castlingPiece.getType().equals(ROOK)){
            return false;
        }
        if(!checkPiecesWithoutMovement(origin, castlingPieceTile, destination, gameStates)){
            return false;
        }
        if(isStraightPathFreeToCross.isValidMove(origin, castlingPieceTile, gameStates) == MoveType.INVALID){
            return false;
        }
        return true;
    }
    private boolean checkValidCoordinates(Tile origin, Tile destination, List<GameState> gameStates){
        if(origin.getY() != destination.getY()){
            return false;
        }
        if(destination.getX() != 1){
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
