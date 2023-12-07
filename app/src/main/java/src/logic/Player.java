package src.logic;

import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveRule;
import src.logic.piece.Piece;
import src.logic.moveRules.MoveType;

import java.util.List;

public class Player {
    private final TeamColor color;

    public Player(TeamColor color) {
        this.color = color;
    }
    public TeamColor getColor() {
        return color;
    }
    public MoveType canMovePiece(Tile from, Tile to, List<GameState> gameStates) {
        Piece pieceToMove = gameStates.get(gameStates.size() - 1).getBoard().getPiece(from);
        TeamColor turnColor = gameStates.get(gameStates.size() - 1).getColorTurn();
         if(checksConditions(from, to, gameStates, pieceToMove, turnColor)) {
             return checkMoveType(gameStates, from, to, pieceToMove);
        }
        return MoveType.INVALID;
    }
    private MoveType checkMoveType(List<GameState> gameStates, Tile from, Tile to, Piece pieceToMove){
        MoveRule[] moveRules = pieceToMove.getMoveRules();
        for(MoveRule rule : moveRules){
            MoveType moveType = rule.isValidMove(from, to, gameStates);
            if(moveType != MoveType.INVALID){
                return moveType;
            }
        }
        return MoveType.INVALID;
    }
    private boolean checksConditions(Tile from, Tile to, List<GameState> gameStates, Piece pieceToMove, TeamColor turnColor){
        if(!isPlayerTurn(turnColor)) {
            return false;
        }
        if(!isTileInBoard(to,gameStates)) {
            return false;
        }
        if(!isOriginTileOccupied(from,gameStates)) {
            return false;
        }
        if(!pieceColorMatchesTurnColor(pieceToMove, turnColor)) {
            return false;
        }
        return true;
    }
    private boolean isPlayerTurn(TeamColor turnColor) {
        return turnColor == color;
    }
    private boolean isTileInBoard(Tile to, List<GameState> gameStates) {
        int boardWidth = gameStates.get(gameStates.size() - 1).getBoard().getWidth();
        int boardLength = gameStates.get(gameStates.size() - 1).getBoard().getLength();
        if(to.getX() < 0)
            return false;
        if(to.getX() > boardWidth)
            return false;
        if(to.getY() >= 0){
            if(to.getY() <= boardLength){
                return true;
            }
        }
        return false;
    }
    private boolean isOriginTileOccupied(Tile from, List<GameState> gameStates) {
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        return board.containsPieceInTile(from);
    }
    private boolean pieceColorMatchesTurnColor(Piece pieceToMove, TeamColor turnColor) {
        return pieceToMove.getColor() == turnColor;
    }
}
