package src.logic;

import src.logic.gameState.GameState;
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
    public MoveType CanMovePiece(Tile from, Tile to, List<GameState> gameStates) {
        TeamColor turnColor = gameStates.get(gameStates.size() - 1).getColorTurn();

        if(turnColor == color) {
            if(isTileInBoard(to, gameStates.get(gameStates.size() - 1).getBoard().getWidth(), gameStates.get(gameStates.size() - 1).getBoard().getLength())) {
                Piece pieceToMove = gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(from);
                if (pieceToMove != null && pieceToMove.getColor() == turnColor) {
                    for(int i = 0; i <= pieceToMove.getMoveRules().length - 1;i++) {
                        MoveType moveType = pieceToMove.getMoveRules()[i].isValidMove(from, to, gameStates);
                        if(moveType != MoveType.INVALID) {
                            if(from.getX() == 4 && from.getY() == 0) {
                                System.out.println("Debug");
                                System.out.println("position:" + i);
                            }
                            return moveType;
                        }
                    }
                }
            }

        }
        return MoveType.INVALID;
    }
    private boolean isTileInBoard(Tile tile, int BoardWidth, int BoardLength) {
        if(tile.getX() < 0 || tile.getX() > BoardWidth) {
            return false;
        }
        return tile.getY() >= 0 && tile.getY() <= BoardLength;
    }
}
