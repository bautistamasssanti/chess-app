package src.chessGames.capablancaChess.gameState;

import src.chessGames.capablancaChess.piece.CapablancaPieceFactory;
import src.chessGames.commons.GameStateFactory;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CapablancaGameStateFactory implements GameStateFactory {
    public List<GameState> movePiece(MoveType type, Tile originalTile, Tile targetTile, List<GameState> gameStates){
        return switch (type) {
            case BASIC -> basicMovePiece(originalTile, targetTile, gameStates);
            case SPECIAL1 -> castledKingSide(originalTile, targetTile, gameStates);
            case SPECIAL2 -> castledQueenSise(originalTile, targetTile, gameStates);
            default -> throw new IllegalArgumentException("Invalid MoveType");
        };
    }
    private List<GameState> basicMovePiece(Tile originalTile, Tile destinationTile, List<GameState> gameStates){
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
    private List<GameState> castledKingSide(Tile originalTile, Tile destinationTile, List<GameState> gameStates){
        Map<Tile, Piece> newBoard = gameStates.get(gameStates.size() - 1).getBoard().getBoardCopy();
        Piece king = newBoard.get(originalTile);
        Tile rookOriginalTile = new Tile(gameStates.get(gameStates.size() - 1).getBoard().getWidth() - 1, originalTile.getY());
        Piece rook = newBoard.get(rookOriginalTile);
        newBoard.remove(originalTile);
        newBoard.put(destinationTile, king);
        newBoard.remove(rookOriginalTile);
        newBoard.put(new Tile(destinationTile.getX() - 1, destinationTile.getY()), rook);
        Board newBoardObject = new Board(newBoard, gameStates.get(gameStates.size() - 1).getBoard().getWidth(), gameStates.get(gameStates.size() - 1).getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), gameStates.get(gameStates.size() - 1).getColorInNextTurn());
        List<GameState> newHistory = new ArrayList<>(gameStates);
        newHistory.add(newGameState);
        return Collections.unmodifiableList(newHistory);
    }
    private List<GameState> castledQueenSise(Tile originalTile, Tile DestinationTile, List<GameState> gameStates){
        Map<Tile, Piece> newBoard = gameStates.get(gameStates.size() - 1).getBoard().getBoardCopy();
        Piece king = newBoard.get(originalTile);
        Tile rookOriginalTile = new Tile(0, originalTile.getY());
        Piece rook = newBoard.get(rookOriginalTile);
        newBoard.remove(originalTile);
        newBoard.put(DestinationTile, king);
        newBoard.remove(rookOriginalTile);
        newBoard.put(new Tile(DestinationTile.getX() + 1, DestinationTile.getY()), rook);
        Board newBoardObject = new Board(newBoard, gameStates.get(gameStates.size() - 1).getBoard().getWidth(), gameStates.get(gameStates.size() - 1).getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), gameStates.get(gameStates.size() - 1).getColorInNextTurn());
        List<GameState> newHistory = new ArrayList<>(gameStates);
        newHistory.add(newGameState);
        return Collections.unmodifiableList(newHistory);
    }
}
