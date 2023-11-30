package src.capablancaChess.board;

import src.capablancaChess.piece.CapablancaPieceFactory;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.board.BoardFactory;
import src.logic.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class CapablancaBoardFactory implements BoardFactory {
    @Override
    public Board newBoard(TeamColor color1, TeamColor color2) {
        Map<Tile,Piece> board = new HashMap<>();

        board.put(new Tile(0,0), new CapablancaPieceFactory().Rook(color1, 0));
        board.put(new Tile(1,0), new CapablancaPieceFactory().Knight(color1, 1));
        board.put(new Tile(2,0), new CapablancaPieceFactory().Archbishop(color1, 2));
        board.put(new Tile(3,0), new CapablancaPieceFactory().Bishop(color1, 3));
        board.put(new Tile(4,0), new CapablancaPieceFactory().Queen(color1, 4));
        board.put(new Tile(5,0), new CapablancaPieceFactory().King(color1, 5));
        board.put(new Tile(6,0), new CapablancaPieceFactory().Bishop(color1, 6));
        board.put(new Tile(7,0), new CapablancaPieceFactory().Chancellor(color1, 7));
        board.put(new Tile(8,0), new CapablancaPieceFactory().Knight(color1, 8));
        board.put(new Tile(9,0), new CapablancaPieceFactory().Rook(color1, 9));
        board.put(new Tile(0,1), new CapablancaPieceFactory().Pawn(color1, 10));
        board.put(new Tile(1,1), new CapablancaPieceFactory().Pawn(color1, 11));
        board.put(new Tile(2,1), new CapablancaPieceFactory().Pawn(color1, 12));
        board.put(new Tile(3,1), new CapablancaPieceFactory().Pawn(color1, 13));
        board.put(new Tile(4,1), new CapablancaPieceFactory().Pawn(color1, 14));
        board.put(new Tile(5,1), new CapablancaPieceFactory().Pawn(color1, 15));
        board.put(new Tile(6,1), new CapablancaPieceFactory().Pawn(color1, 16));
        board.put(new Tile(7,1), new CapablancaPieceFactory().Pawn(color1, 17));
        board.put(new Tile(8,1), new CapablancaPieceFactory().Pawn(color1, 18));
        board.put(new Tile(9,1), new CapablancaPieceFactory().Pawn(color1, 19));
        board.put(new Tile(0,6), new CapablancaPieceFactory().Pawn(color2, 20));
        board.put(new Tile(1,6), new CapablancaPieceFactory().Pawn(color2, 21));
        board.put(new Tile(2,6), new CapablancaPieceFactory().Pawn(color2, 22));
        board.put(new Tile(3,6), new CapablancaPieceFactory().Pawn(color2, 23));
        board.put(new Tile(4,6), new CapablancaPieceFactory().Pawn(color2, 24));
        board.put(new Tile(5,6), new CapablancaPieceFactory().Pawn(color2, 25));
        board.put(new Tile(6,6), new CapablancaPieceFactory().Pawn(color2, 26));
        board.put(new Tile(7,6), new CapablancaPieceFactory().Pawn(color2, 27));
        board.put(new Tile(8,6), new CapablancaPieceFactory().Pawn(color2, 28));
        board.put(new Tile(9,6), new CapablancaPieceFactory().Pawn(color2, 29));
        board.put(new Tile(0,7), new CapablancaPieceFactory().Rook(color2, 30));
        board.put(new Tile(1,7), new CapablancaPieceFactory().Knight(color2, 31));
        board.put(new Tile(2,7), new CapablancaPieceFactory().Archbishop(color2, 32));
        board.put(new Tile(3,7), new CapablancaPieceFactory().Bishop(color2, 33));
        board.put(new Tile(4,7), new CapablancaPieceFactory().Queen(color2, 34));
        board.put(new Tile(5,7), new CapablancaPieceFactory().King(color2, 35));
        board.put(new Tile(6,7), new CapablancaPieceFactory().Bishop(color2, 36));
        board.put(new Tile(7,7), new CapablancaPieceFactory().Chancellor(color2, 37));
        board.put(new Tile(8,7), new CapablancaPieceFactory().Knight(color2, 38));
        board.put(new Tile(9,7), new CapablancaPieceFactory().Rook(color2, 39));

        return new Board(board,10,8);
    }
}
