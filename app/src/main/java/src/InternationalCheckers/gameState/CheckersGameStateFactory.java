package src.InternationalCheckers.gameState;

import src.InternationalCheckers.piece.CheckersPieceFactory;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CheckersGameStateFactory {
    private final ArithmethicOperation arithmethicOperation = new ArithmethicOperation();
    private final CheckersPieceFactory checkersPieceFactory = new CheckersPieceFactory();
    public List<GameState> promotePiece(Tile originalTile, List<GameState> gameStates, PieceType transformTo){
        GameState originalGameState = gameStates.get(gameStates.size() - 1);
        List<GameState> newHistory = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1)) ;
        Map<Tile, Piece> newBoard = originalGameState.getBoard().getBoardCopy();
        Piece newPiece = checkersPieceFactory.getNewPieceByEnum(transformTo, newBoard.get(originalTile).getColor(),newBoard.get(originalTile).getId());
        newBoard.remove(originalTile);
        newBoard.put(originalTile, newPiece);
        Board newBoardObject = new Board(newBoard, originalGameState.getBoard().getWidth(), originalGameState.getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, originalGameState.getGameStatus(), originalGameState.getTeamAPlayer(), originalGameState.getTeamBPlayer(), originalGameState.getColorTurn());
        newHistory.add(newHistory.size(), newGameState);
        return newHistory;
    }
    public List<GameState> switchTurn(List<GameState> gameStates, TeamColor colorToSwitch){
        GameState newGameState = new GameState(gameStates.get(gameStates.size() - 1).getBoard(), gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), colorToSwitch);
        List<GameState> newHistory = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1));
        newHistory.add(newHistory.size(), newGameState);
        return Collections.unmodifiableList(newHistory);
    }
    public List<GameState> movePiece(MoveType type, Tile originalTile, Tile destinationTile, List<GameState> gameStates){
        return switch (type) {
            case BASIC -> basicMovePiece(originalTile, destinationTile, gameStates);
            case JUMP -> jumpMovePiece(originalTile, destinationTile, gameStates);
            case MULTI_JUMP -> multiJumpMovePiece(originalTile, destinationTile, gameStates);
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
    public List<GameState> jumpMovePiece(Tile originalTile, Tile destinationTile, List<GameState> gameStates){
        Map<Tile, Piece> newBoard = gameStates.get(gameStates.size() - 1).getBoard().getBoardCopy();
        Piece piece = newBoard.get(originalTile);
        newBoard.remove(originalTile);
        newBoard.remove(getTileBeforeDestination(originalTile, destinationTile));
        newBoard.put(destinationTile, piece);
        Board newBoardObject = new Board(newBoard, gameStates.get(gameStates.size() - 1).getBoard().getWidth(), gameStates.get(gameStates.size() - 1).getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), gameStates.get(gameStates.size() - 1).getColorInNextTurn());
        List<GameState> newHistory = new ArrayList<>(gameStates);
        newHistory.add(newGameState);
        return Collections.unmodifiableList(newHistory);
    }
    public List<GameState> multiJumpMovePiece(Tile originalTile, Tile destinationTile, List<GameState> gameStates){
        Map<Tile, Piece> newBoard = gameStates.get(gameStates.size() - 1).getBoard().getBoardCopy();
        Piece piece = newBoard.get(originalTile);
        newBoard.remove(originalTile);
        newBoard.remove(getTileBeforeDestination(originalTile, destinationTile));
        newBoard.put(destinationTile, piece);
        Board newBoardObject = new Board(newBoard, gameStates.get(gameStates.size() - 1).getBoard().getWidth(), gameStates.get(gameStates.size() - 1).getBoard().getLength());
        GameState newGameState = new GameState(newBoardObject, gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), gameStates.get(gameStates.size() - 1).getColorTurn());
        List<GameState> newHistory = new ArrayList<>(gameStates);
        newHistory.add(newGameState);
        return Collections.unmodifiableList(newHistory);
    }
    private Tile getTileBeforeDestination(Tile origin, Tile destination){
        if (arithmethicOperation.getXMovement(origin, destination) > 0 && arithmethicOperation.getYMovement(origin, destination) > 0) {
            return new Tile(destination.getX() - 1, destination.getY() - 1);
        } else if (arithmethicOperation.getXMovement(origin, destination) > 0 && arithmethicOperation.getYMovement(origin, destination) < 0) {
            return new Tile(destination.getX() - 1, destination.getY() + 1);
        } else if (arithmethicOperation.getXMovement(origin, destination) < 0 && arithmethicOperation.getYMovement(origin, destination) > 0) {
            return new Tile(destination.getX() + 1, destination.getY() - 1);
        } else return new Tile(destination.getX() + 1, destination.getY() + 1);
    }
}
