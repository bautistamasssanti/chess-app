package src.Chess.gameMode.optionalGameRules;

import src.logic.Tile;
import src.logic.gameMode.OptionalGameRule;
import src.logic.gameState.GameState;
import src.Chess.factories.ChessGameStateFactory;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import java.util.List;

public class PromoteRule implements OptionalGameRule {
    private final ChessGameStateFactory gameStateFactory = new ChessGameStateFactory();
    private final PieceType transformFrom;
    private final PieceType transformTo;

    public PromoteRule(PieceType transformFrom, PieceType transformTo) {
        this.transformFrom = transformFrom;
        this.transformTo = transformTo;
    }

    @Override
    public List<GameState> isOptionalConditionulfilled(List<GameState> gameStates) {
        List<GameState> currentStates = gameStates;
        List<Tile> occupiedTiles = currentStates.get(currentStates.size() - 1).getBoard().getOccupiedTiles();
        for (Tile occupiedTile : occupiedTiles) {
            Piece piece = currentStates.get(currentStates.size() - 1).getBoard().getBoard().get(occupiedTile);
            if (piece.getType() == transformFrom) {
                if (hasPieceReachedOtherSide(occupiedTile, currentStates)) {
                    currentStates = gameStateFactory.promotePiece(occupiedTile, currentStates, transformTo);
                }
            }
        }
        return currentStates;
    }
    private boolean hasPieceReachedOtherSide(Tile toAnalize, List<GameState> gameStates){
        if(gameStates.get(gameStates.size()-1).getBoard().getBoard().get(toAnalize).getColor() == gameStates.get(0).getTeamAPlayer().getColor()){
            return toAnalize.getY() == gameStates.get(gameStates.size() - 1).getBoard().getLength() - 1;
        } else{
            return toAnalize.getY() == 0;
        }
    }
}
