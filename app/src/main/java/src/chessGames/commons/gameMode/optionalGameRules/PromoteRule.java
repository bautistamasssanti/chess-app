package src.chessGames.commons.gameMode.optionalGameRules;

import src.chessGames.commons.PieceFactory;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameMode.OptionalGameRule;
import src.logic.gameState.GameState;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PromoteRule implements OptionalGameRule {
    private final PieceFactory pieceFactory;
    private final PieceType transformFrom;
    private final PieceType transformTo;

    public PromoteRule(PieceType transformFrom, PieceType transformTo, PieceFactory pieceFactory) {
        this.transformFrom = transformFrom;
        this.transformTo = transformTo;
        this.pieceFactory = pieceFactory;
    }

    @Override
    public List<GameState> isOptionalConditionfulfilled(List<GameState> gameStates) {
        List<GameState> currentStates = gameStates;
        List<Tile> occupiedTiles = currentStates.get(currentStates.size() - 1).getBoard().getOccupiedTiles();
        for (Tile occupiedTile : occupiedTiles) {
            Piece piece = currentStates.get(currentStates.size() - 1).getBoard().getPiece(occupiedTile);
            if (checksConditions(currentStates, occupiedTile, piece)) {
                currentStates = promotePiece(occupiedTile, currentStates, transformTo);
            }
        }
        return currentStates;
    }

    private boolean checksConditions(List<GameState> gameStates, Tile occupiedTile, Piece piece) {
        if (piece.getType() == transformFrom) {
            if (hasPieceReachedOtherSide(occupiedTile, gameStates)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPieceReachedOtherSide(Tile toAnalize, List<GameState> gameStates) {
        GameState gameState = gameStates.get(gameStates.size() - 1);
        if (gameState.getBoard().getPiece(toAnalize).getColor() == gameState.getTeamAPlayer().getColor()) {
            return toAnalize.getY() == gameState.getBoard().getLength() - 1;
        } else {
            return toAnalize.getY() == 0;
        }
    }
    private List<GameState> promotePiece(Tile originalTile, List<GameState> gameStates, PieceType transformTo){
        GameState originalGameState = gameStates.get(gameStates.size() - 1);
        List<GameState> newHistory = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1)) ;
        Map<Tile, Piece> newBoard = originalGameState.getBoard().getBoardCopy();
        Piece newPiece = pieceFactory.GetNewPieceByEnum(transformTo, newBoard.get(originalTile).getColor(),newBoard.get(originalTile).getId());
        newBoard.remove(originalTile);
        newBoard.put(originalTile, newPiece);
        Board newBoardObject = new Board(newBoard, originalGameState.getBoard().getWidth(), originalGameState.getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, originalGameState.getGameStatus(), originalGameState.getTeamAPlayer(), originalGameState.getTeamBPlayer(), originalGameState.getColorTurn());
        newHistory.add(newHistory.size(), newGameState);
        return Collections.unmodifiableList(newHistory);
    }
}
