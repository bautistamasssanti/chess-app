package src.chess.moveRules.specialRules;

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
            if(origin.getY() != destination.getY()){
                return MoveType.INVALID;
            }
            if(isPieceWithoutPreviousMovement.isValidMove(origin, destination, gameStates) != MoveType.INVALID){
                int boardWidth = gameStates.get(gameStates.size() - 1).getBoard().getWidth();
                Tile castlingPieceTile = new Tile(boardWidth - 1, origin.getY());
                Piece castlingPiece = gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(castlingPieceTile);
                if(castlingPiece.getType().equals(ROOK) && isPieceWithoutPreviousMovement.isValidMove(castlingPieceTile, destination, gameStates) != MoveType.INVALID && isStraightPathFreeToCross.isValidMove(origin, castlingPieceTile, gameStates) != MoveType.INVALID){
                    return MoveType.SPECIAL1;
                }
            }
            return MoveType.INVALID;
        } catch (Exception e){
            return MoveType.INVALID;
        }
    }
}
