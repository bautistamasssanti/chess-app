package src.capablancaChess.gameMode.optionalGameRules;

import src.capablancaChess.gameState.CapablancaGameStateFactory;
import src.logic.Tile;
import src.logic.gameMode.OptionalGameRule;
import src.logic.gameState.GameState;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import java.util.List;

public class PromoteRule implements OptionalGameRule {
    private final CapablancaGameStateFactory gameStateFactory = new CapablancaGameStateFactory();
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
            if (checksConditions(currentStates, occupiedTile, piece)) {
                currentStates = gameStateFactory.promotePiece(occupiedTile, currentStates, transformTo);
            }
        }
        return currentStates;
    }
    private boolean checksConditions(List<GameState> gameStates, Tile occupiedTile, Piece piece){
        if(piece.getType() == transformFrom){
            if(hasPieceReachedOtherSide(occupiedTile, gameStates)){
                return true;
            }
        }
        return false;
    }
    private boolean hasPieceReachedOtherSide(Tile toAnalize, List<GameState> gameStates){
        GameState gameState = gameStates.get(gameStates.size() - 1);
        if(gameState.getBoard().getBoard().get(toAnalize).getColor() == gameState.getTeamAPlayer().getColor()){
            return toAnalize.getY() == gameState.getBoard().getLength() - 1;
        } else{
            return toAnalize.getY() == 0;
        }
    }
}
