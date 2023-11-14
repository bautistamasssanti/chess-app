package src.Chess.moveRules.specialRules;

import src.logic.moveRules.MoveType;
import src.logic.piece.Piece;
import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.state.IsPieceWithoutPreviousMovement;
import src.logic.moveRules.basicRules.path.IsStraightPathFreeToCross;

import java.util.List;

import static src.logic.piece.PieceType.ROOK;

public class CastledQueensideRule implements MoveRule {
    private final IsPieceWithoutPreviousMovement isPieceWithoutPreviousMovement = new IsPieceWithoutPreviousMovement();
    private final IsStraightPathFreeToCross isStraightPathFreeToCross = new IsStraightPathFreeToCross();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        try{
            if(isPieceWithoutPreviousMovement.isValidMove(origin, destination, gameStates) != MoveType.INVALID){
                Tile castlingPieceTile = new Tile(0, origin.getY());
                Piece castlingPiece = gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(castlingPieceTile);
                if(castlingPiece.getType().equals(ROOK) && isPieceWithoutPreviousMovement.isValidMove(castlingPieceTile, destination, gameStates) != MoveType.INVALID && isStraightPathFreeToCross.isValidMove(origin, castlingPieceTile, gameStates) != MoveType.INVALID){
                    return MoveType.CASTLED_QUEENSIDE;
                }
            }
            return MoveType.INVALID;
        }catch(Exception e){
            return MoveType.INVALID;
        }
    }
}
