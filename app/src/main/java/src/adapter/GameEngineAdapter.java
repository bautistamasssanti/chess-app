package src.adapter;

import edu.austral.dissis.chess.gui.*;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.piece.PieceType;

import java.util.List;

public interface GameEngineAdapter {
    BoardSize getBoardSize(Board board);
    List<ChessPiece> getCurrentPieces(Board board);
    PlayerColor getCurrentTurn(GameState gameState);
    Position adaptPosition(Tile tile);
    PlayerColor adaptPlayerColor(TeamColor teamColor);
    String adaptPieceType(PieceType pieceType);
    List<Tile> adaptTiles(Move move);
}
