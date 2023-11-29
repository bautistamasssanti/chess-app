package src.logic.moveRules.basicRules.state;

import src.logic.moveRules.MoveType;
import src.logic.piece.Piece;
import src.logic.gameState.GameState;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;

import java.util.List;

public class IsPieceWithoutPreviousMovement implements MoveRule {
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        Piece currentPiece = gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(origin);
        for (GameState gameState : gameStates) {
            Piece pieceInGameState = gameState.getBoard().getBoard().get(origin);
            if (!currentPiece.equals(pieceInGameState)) {
                return MoveType.INVALID;
            }
        }
        return MoveType.BASIC;
    }
}
