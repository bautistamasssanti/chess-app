package src.chess.factories;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.piece.Piece;
import src.chess.piece.ChessPieceFactory;
import src.logic.piece.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessGameStateFactory {
    private final ChessPieceFactory chessPieceFactory = new ChessPieceFactory();
    public List<GameState> promotePiece(Tile originalTile, List<GameState> gameStates, PieceType transformTo){
        GameState originalGameState = gameStates.get(gameStates.size() - 1);
        List<GameState> newHistory = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1)) ;
        Map<Tile, Piece> newBoard = originalGameState.getBoard().getBoardCopy();
        Piece newPiece = chessPieceFactory.GetNewPieceByEnum(transformTo, newBoard.get(originalTile).getColor(),newBoard.get(originalTile).getId());
        newBoard.remove(originalTile);
        newBoard.put(originalTile, newPiece);
        Board newBoardObject = new Board(newBoard, originalGameState.getBoard().getWidth(), originalGameState.getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, originalGameState.getGameStatus(), originalGameState.getTeamAPlayer(), originalGameState.getTeamBPlayer(), originalGameState.getColorTurn());
        newHistory.add(newHistory.size(), newGameState);
        return Collections.unmodifiableList(newHistory);
    }
    public List<GameState> changeTurnColor(TeamColor colorTurn, List<GameState> gameStates){
        List<GameState> newHistory = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1)) ;
        GameState newGameState = new GameState(gameStates.get(gameStates.size() - 1).getBoard(), gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), colorTurn);
        newHistory.add(newHistory.size(), newGameState);
        return Collections.unmodifiableList(newHistory);
    }
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
