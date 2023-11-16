package src.StandardCheckers.adapter;

import edu.austral.dissis.chess.gui.*;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameEngineAdapter.GameEngineAdapter;
import src.logic.gameState.GameState;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public class CheckersGameEngineAdapter implements GameEngineAdapter {
    @Override
    public BoardSize getBoardSize(Board board) {
        return new BoardSize(board.getWidth(), board.getLength());
    }

    @Override
    public List<ChessPiece> getCurrentPieces(Board board) {
        List<Tile> occupiedTiles = board.getOccupiedTiles();
        List<ChessPiece> chessPieces = new ArrayList<>();
        for (Tile occupiedTile : occupiedTiles) {
            Piece piece = board.getBoard().get(occupiedTile);
            chessPieces.add(new ChessPiece(String.valueOf(piece.getId()), adaptPlayerColor(piece.getColor()), adaptPosition(occupiedTile), adaptPieceType(piece.getType())));
        }
        return chessPieces;
    }

    @Override
    public PlayerColor getCurrentTurn(GameState gameState) {
        return adaptPlayerColor(gameState.getColorTurn());
    }

    @Override
    public Position adaptPosition(Tile tile) {
        return new Position(tile.getY()+1, tile.getX()+1);
    }

    @Override
    public PlayerColor adaptPlayerColor(TeamColor teamColor) {
        if(teamColor.equals(TeamColor.WHITE)){
            return PlayerColor.WHITE;
        }
        else return PlayerColor.BLACK;
    }

    @Override
    public String adaptPieceType(PieceType pieceType) {
        if (pieceType == PieceType.MAN) {
            return "pawn";
        }
        else return "queen";
    }

    @Override
    public List<Tile> adaptTiles(Move move) {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(move.component1().getColumn()-1,move.component1().getRow()-1));
        tiles.add(new Tile(move.component2().getColumn()-1,move.component2().getRow()-1));
        return tiles;
    }
}
