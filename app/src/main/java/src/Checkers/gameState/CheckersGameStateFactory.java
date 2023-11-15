package src.Checkers.gameState;

import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CheckersGameStateFactory {
    public List<GameState> movePiece(MoveType type, Tile originalTile, Tile destinationTile, List<GameState> gameStates){
        return switch (type) {
            case BASIC -> basicMovePiece(originalTile, destinationTile, gameStates);
            default -> throw new IllegalArgumentException("Invalid MoveType");
        };
    }
    public List<GameState> basicMovePiece(Tile originalTile, Tile destinationTile, List<GameState> gameStates){
        Map<Tile, Piece> newBoard = gameStates.get(gameStates.size() - 1).getBoard().getBoardCopy();
        Piece piece = newBoard.get(originalTile);
        newBoard.remove(originalTile);
        newBoard.put(destinationTile, piece);
        Board newBoardObject = new Board(newBoard, gameStates.get(gameStates.size() - 1).getBoard().getWidth(), gameStates.get(gameStates.size() - 1).getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), gameStates.get(gameStates.size() - 1).getColorInNextTurn());
        List<GameState> newHistory = new ArrayList<>(gameStates);
        newHistory.add(newGameState);
        return Collections.unmodifiableList(newHistory);
    }
}
