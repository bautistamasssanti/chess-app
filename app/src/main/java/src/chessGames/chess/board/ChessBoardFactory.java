package src.chessGames.chess.board;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.board.BoardFactory;
import src.logic.piece.Piece;
import src.chessGames.chess.piece.ChessPieceFactory;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardFactory implements BoardFactory {
    private final ChessPieceFactory chessPieceFactory = new ChessPieceFactory();
    @Override
    public Board newBoard(TeamColor color1, TeamColor color2){
        Map<Tile, Piece> board = new HashMap<>();

        board.put(new Tile(0,0), chessPieceFactory.Rook(color1, 0));
        board.put(new Tile(1,0), chessPieceFactory.Knight(color1, 1));
        board.put(new Tile(2,0), chessPieceFactory.Bishop(color1, 2));
        board.put(new Tile(3,0), chessPieceFactory.Queen(color1, 3));
        board.put(new Tile(4,0), chessPieceFactory.King(color1, 4));
        board.put(new Tile(5,0), chessPieceFactory.Bishop(color1, 5));
        board.put(new Tile(6,0), chessPieceFactory.Knight(color1, 6));
        board.put(new Tile(7,0), chessPieceFactory.Rook(color1, 7));
        board.put(new Tile(0,1), chessPieceFactory.Pawn(color1, 8));
        board.put(new Tile(1,1), chessPieceFactory.Pawn(color1, 9));
        board.put(new Tile(2,1), chessPieceFactory.Pawn(color1, 10));
        board.put(new Tile(3,1), chessPieceFactory.Pawn(color1, 11));
        board.put(new Tile(4,1), chessPieceFactory.Pawn(color1, 12));
        board.put(new Tile(5,1), chessPieceFactory.Pawn(color1, 13));
        board.put(new Tile(6,1), chessPieceFactory.Pawn(color1, 14));
        board.put(new Tile(7,1), chessPieceFactory.Pawn(color1, 15));
        board.put(new Tile(0,6), chessPieceFactory.Pawn(color2, 16));
        board.put(new Tile(1,6), chessPieceFactory.Pawn(color2, 17));
        board.put(new Tile(2,6), chessPieceFactory.Pawn(color2, 18));
        board.put(new Tile(3,6), chessPieceFactory.Pawn(color2, 19));
        board.put(new Tile(4,6), chessPieceFactory.Pawn(color2, 20));
        board.put(new Tile(5,6), chessPieceFactory.Pawn(color2, 21));
        board.put(new Tile(6,6), chessPieceFactory.Pawn(color2, 22));
        board.put(new Tile(7,6), chessPieceFactory.Pawn(color2, 23));
        board.put(new Tile(0,7), chessPieceFactory.Rook(color2, 24));
        board.put(new Tile(1,7), chessPieceFactory.Knight(color2, 25));
        board.put(new Tile(2,7), chessPieceFactory.Bishop(color2, 26));
        board.put(new Tile(3,7), chessPieceFactory.Queen(color2, 27));
        board.put(new Tile(4,7), chessPieceFactory.King(color2, 28));
        board.put(new Tile(5,7), chessPieceFactory.Bishop(color2, 29));
        board.put(new Tile(6,7), chessPieceFactory.Knight(color2, 30));
        board.put(new Tile(7,7), chessPieceFactory.Rook(color2, 31));

        return new Board(board,8,8);
    }

}
