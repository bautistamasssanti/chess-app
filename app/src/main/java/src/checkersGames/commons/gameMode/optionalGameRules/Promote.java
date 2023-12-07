package src.checkersGames.commons.gameMode.optionalGameRules;

import org.jetbrains.annotations.NotNull;
import src.checkersGames.commons.PieceFactory;
import src.logic.TeamColor;
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

public class Promote implements OptionalGameRule {
    private final PieceFactory pieceFactory;

    public Promote(PieceFactory pieceFactory) {
        this.pieceFactory = pieceFactory;
    }

    @Override
    public List<GameState> isOptionalConditionfulfilled(@NotNull List<GameState> gameStates) {
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
            Piece piece = currentStates.get(currentStates.size() - 1).getBoard().getPiece(occupiedTile);
            if(checkCondition(currentStates, occupiedTile, piece)){
                currentStates = promotePiece(occupiedTile, currentStates, PieceType.QUEEN);
            }
        }
        return currentStates;
    }
    private boolean checkCondition(List<GameState> currentStates, Tile occupiedTile, @NotNull Piece piece){
        try{
            if (piece.getType() == PieceType.MAN) {
                if(hasTileReachedItsOwnSideLimit(occupiedTile, piece, currentStates))
                    return false;
                return hasTileReachedSideLimit(occupiedTile, currentStates);
            }
            return false;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    private boolean hasTileReachedItsOwnSideLimit(Tile toAnalise,@NotNull Piece piece, List<GameState> gameStates){
        try{
            Board originalBoard = gameStates.get(0).getBoard();
            Tile pieceOriginalTile = originalBoard.getTileFromPieceId(piece.getId());
            if(pieceOriginalTile.getY() <= originalBoard.getLength()/2){
                return toAnalise.getY() == 0;
            } else
                return toAnalise.getY() == originalBoard.getLength() - 1;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }
    private boolean hasTileReachedSideLimit(Tile occupiedTile, List<GameState> currentStates){
        if (occupiedTile.getY() == 0) {
                return true;
        }
        if (occupiedTile.getY() == currentStates.get(currentStates.size() - 1).getBoard().getLength() - 1) {
                return true;
        }
        return false;
    }

    private List<GameState> promotePiece(Tile originalTile, @NotNull List<GameState> gameStates, PieceType transformTo){
        GameState originalGameState = gameStates.get(gameStates.size() - 1);
        List<GameState> newHistory = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1)) ;
        Map<Tile, Piece> newBoard = originalGameState.getBoard().getBoardCopy();
        Piece newPiece = pieceFactory.getNewPieceByEnum(transformTo, newBoard.get(originalTile).getColor(),newBoard.get(originalTile).getId());
        newBoard.remove(originalTile);
        newBoard.put(originalTile, newPiece);
        Board newBoardObject = new Board(newBoard, originalGameState.getBoard().getWidth(), originalGameState.getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, originalGameState.getGameStatus(), originalGameState.getTeamAPlayer(), originalGameState.getTeamBPlayer(), originalGameState.getColorTurn());
        newHistory.add(newHistory.size(), newGameState);
        return Collections.unmodifiableList(newHistory);
    }
}