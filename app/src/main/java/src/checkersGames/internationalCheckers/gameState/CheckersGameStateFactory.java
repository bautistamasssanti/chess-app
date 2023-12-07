package src.checkersGames.internationalCheckers.gameState;

import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;
import src.logic.piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CheckersGameStateFactory {
    private final ArithmethicOperation arithmethicOperation = new ArithmethicOperation();

    public List<GameState> movePiece(MoveType type, Tile originalTile, Tile destinationTile, List<GameState> gameStates){
        return switch (type) {
            case BASIC -> basicMovePiece(originalTile, destinationTile, gameStates);
            case SPECIAL1 -> jumpMovePiece(originalTile, destinationTile, gameStates);
            case SPECIAL2 -> multiJumpMovePiece(originalTile, destinationTile, gameStates);
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
        if (getTileBeforeDestinationFirstCondition(origin, destination)) {
            return new Tile(destination.getX() - 1, destination.getY() - 1);
        } else if (getTileBeforeDestinationSecondCondition(origin, destination)) {
            return new Tile(destination.getX() - 1, destination.getY() + 1);
        } else if (getTileBeforeDestinationThirdCondition(origin, destination)) {
            return new Tile(destination.getX() + 1, destination.getY() - 1);
        } else return new Tile(destination.getX() + 1, destination.getY() + 1);
    }
    private boolean getTileBeforeDestinationFirstCondition(Tile origin, Tile destination){
        if(arithmethicOperation.getXMovement(origin, destination) > 0){
            if(arithmethicOperation.getYMovement(origin, destination) > 0)
                return true;
        }
        return false;
    }
    private boolean getTileBeforeDestinationSecondCondition(Tile origin, Tile destination){
        if(arithmethicOperation.getXMovement(origin, destination) > 0){
            if(arithmethicOperation.getYMovement(origin, destination) < 0)
                return true;
        }
        return false;
    }
    private boolean getTileBeforeDestinationThirdCondition(Tile origin, Tile destination){
        if(arithmethicOperation.getXMovement(origin, destination) < 0){
            if(arithmethicOperation.getYMovement(origin, destination) > 0)
                return true;
        }
        return false;
    }
}
