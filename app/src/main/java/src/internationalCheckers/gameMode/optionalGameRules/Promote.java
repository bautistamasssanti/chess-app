package src.internationalCheckers.gameMode.optionalGameRules;

import src.internationalCheckers.gameState.CheckersGameStateFactory;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
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
            return updateStates(gameStates);
        }
        return gameStates;
    }
    private List<GameState> updateStates(List<GameState> gameStates){
        List<GameState> currentStates = gameStates;
        List<Tile> occupiedTiles = currentStates.get(currentStates.size() - 1).getBoard().getOccupiedTiles();
        for (Tile occupiedTile : occupiedTiles) {
            Piece piece = currentStates.get(currentStates.size() - 1).getBoard().getBoard().get(occupiedTile);
            if(checkCondition(currentStates, occupiedTile, piece)){
                currentStates = gameStateFactory.promotePiece(occupiedTile, currentStates, PieceType.QUEEN);
            }
        }
        return currentStates;
    }
    private boolean checkCondition(List<GameState> currentStates,Tile occupiedTile, Piece piece){
        if (piece.getType() == PieceType.MAN) {
            if (occupiedTile.getY() == 0) {
                if (hasPieceMoved(occupiedTile, currentStates)) {
                    return true;
                }
            }
            if (occupiedTile.getY() == currentStates.get(currentStates.size() - 1).getBoard().getLength() - 1) {
                if (hasPieceMoved(occupiedTile, currentStates)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean hasPieceMoved(Tile toAnalize, List<GameState> gameStates){
        Board currentBoard = gameStates.get(gameStates.size() - 1).getBoard();
        for (GameState gameState: gameStates) {
            if(gameState.getBoard().getBoard().get(toAnalize).getId() != currentBoard.getBoard().get(toAnalize).getId()){
                return true;
            }
        }
        return false;
    }
}