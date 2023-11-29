package src.standardCheckers.gameMode.optionalGameRules;

import src.standardCheckers.gameState.CheckersGameStateFactory;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.gameMode.OptionalGameRule;
import src.logic.gameState.GameState;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import java.util.List;

public class Promote implements OptionalGameRule {
    private final CheckersGameStateFactory gameStateFactory = new CheckersGameStateFactory();
    @Override
    public List<GameState> isOptionalConditionulfilled(List<GameState> gameStates) {
        TeamColor currentTurnColor = gameStates.get(gameStates.size() - 1).getColorTurn();
        TeamColor previousTurnColor = gameStates.get(gameStates.size() - 2).getColorTurn();
        if(currentTurnColor != previousTurnColor){
            List<GameState> currentStates = gameStates;
            List<Tile> occupiedTiles = currentStates.get(currentStates.size() - 1).getBoard().getOccupiedTiles();
            for (Tile occupiedTile : occupiedTiles) {
                Piece piece = currentStates.get(currentStates.size() - 1).getBoard().getBoard().get(occupiedTile);
                if (piece.getType() == PieceType.MAN) {
                    if (((occupiedTile.getY() == 0 || occupiedTile.getY() == currentStates.get(currentStates.size() - 1).getBoard().getLength() - 1) && hasPieceMoved(occupiedTile, currentStates))) {
                        currentStates = gameStateFactory.promotePiece(occupiedTile, currentStates, PieceType.QUEEN);
                    }
                }
            }
            return currentStates;
        }
        return gameStates;
    }
    private boolean hasPieceMoved(Tile toAnalize, List<GameState> gameStates){
        for (int i = 0; i < gameStates.size() - 1; i++) {
            if(gameStates.get(i).getBoard().getBoard().get(toAnalize).getId() != gameStates.get(gameStates.size()-1).getBoard().getBoard().get(toAnalize).getId()){
                return true;
            }
        }
        return false;

    }
}
