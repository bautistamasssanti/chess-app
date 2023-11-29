package src.chess.factories;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.piece.Piece;
import src.chess.piece.ChessPieceFactory;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardFactory {
    private final ChessPieceFactory chessPieceFactory = new ChessPieceFactory();
    public Board standardChessBoard(){
        Map<Tile, Piece> board = new HashMap<>();

        board.put(new Tile(0,0), chessPieceFactory.Rook(TeamColor.WHITE, 0));
        board.put(new Tile(1,0), chessPieceFactory.Knight(TeamColor.WHITE, 1));
        board.put(new Tile(2,0), chessPieceFactory.Bishop(TeamColor.WHITE, 2));
        board.put(new Tile(3,0), chessPieceFactory.Queen(TeamColor.WHITE, 3));
        board.put(new Tile(4,0), chessPieceFactory.King(TeamColor.WHITE, 4));
        board.put(new Tile(5,0), chessPieceFactory.Bishop(TeamColor.WHITE, 5));
        board.put(new Tile(6,0), chessPieceFactory.Knight(TeamColor.WHITE, 6));
        board.put(new Tile(7,0), chessPieceFactory.Rook(TeamColor.WHITE, 7));
        board.put(new Tile(0,1), chessPieceFactory.Pawn(TeamColor.WHITE, 8));
        board.put(new Tile(1,1), chessPieceFactory.Pawn(TeamColor.WHITE, 9));
        board.put(new Tile(2,1), chessPieceFactory.Pawn(TeamColor.WHITE, 10));
        board.put(new Tile(3,1), chessPieceFactory.Pawn(TeamColor.WHITE, 11));
        board.put(new Tile(4,1), chessPieceFactory.Pawn(TeamColor.WHITE, 12));
        board.put(new Tile(5,1), chessPieceFactory.Pawn(TeamColor.WHITE, 13));
        board.put(new Tile(6,1), chessPieceFactory.Pawn(TeamColor.WHITE, 14));
        board.put(new Tile(7,1), chessPieceFactory.Pawn(TeamColor.WHITE, 15));
        board.put(new Tile(0,6), chessPieceFactory.Pawn(TeamColor.BLACK, 16));
        board.put(new Tile(1,6), chessPieceFactory.Pawn(TeamColor.BLACK, 17));
        board.put(new Tile(2,6), chessPieceFactory.Pawn(TeamColor.BLACK, 18));
        board.put(new Tile(3,6), chessPieceFactory.Pawn(TeamColor.BLACK, 19));
        board.put(new Tile(4,6), chessPieceFactory.Pawn(TeamColor.BLACK, 20));
        board.put(new Tile(5,6), chessPieceFactory.Pawn(TeamColor.BLACK, 21));
        board.put(new Tile(6,6), chessPieceFactory.Pawn(TeamColor.BLACK, 22));
        board.put(new Tile(7,6), chessPieceFactory.Pawn(TeamColor.BLACK, 23));
        board.put(new Tile(0,7), chessPieceFactory.Rook(TeamColor.BLACK, 24));
        board.put(new Tile(1,7), chessPieceFactory.Knight(TeamColor.BLACK, 25));
        board.put(new Tile(2,7), chessPieceFactory.Bishop(TeamColor.BLACK, 26));
        board.put(new Tile(3,7), chessPieceFactory.Queen(TeamColor.BLACK, 27));
        board.put(new Tile(4,7), chessPieceFactory.King(TeamColor.BLACK, 28));
        board.put(new Tile(5,7), chessPieceFactory.Bishop(TeamColor.BLACK, 29));
        board.put(new Tile(6,7), chessPieceFactory.Knight(TeamColor.BLACK, 30));
        board.put(new Tile(7,7), chessPieceFactory.Rook(TeamColor.BLACK, 31));

        return new Board(board,8,8);
    }
    public Board twoPawnsBoard(){
        Map<Tile, Piece> board = new HashMap<>();
        board.put(new Tile(4,0), chessPieceFactory.King(TeamColor.WHITE, 4));
        board.put(new Tile(4,7), chessPieceFactory.King(TeamColor.BLACK, 28));
        board.put(new Tile(0,5), chessPieceFactory.Pawn(TeamColor.WHITE, 8));
        board.put(new Tile(2,3), chessPieceFactory.Pawn(TeamColor.BLACK, 18));
        return new Board(board,8,8);
    }
    public Board notEnoughMaterialBoard(){
        Map<Tile, Piece> board = new HashMap<>();
        board.put(new Tile(4,0), chessPieceFactory.King(TeamColor.WHITE, 4));
        board.put(new Tile(4,7), chessPieceFactory.King(TeamColor.BLACK, 28));
        board.put(new Tile(3,2), chessPieceFactory.Pawn(TeamColor.WHITE, 8));
        board.put(new Tile(2,3), chessPieceFactory.Pawn(TeamColor.BLACK, 18));
        board.put(new Tile(0,0), chessPieceFactory.Bishop(TeamColor.WHITE, 0));
        return new Board(board,8,8);
    }
    public Board drownedBoard(){
        Map<Tile, Piece> board = new HashMap<>();
        board.put(new Tile(0,0), chessPieceFactory.King(TeamColor.BLACK, 4));
        board.put(new Tile(1,2), chessPieceFactory.Queen(TeamColor.WHITE, 28));
        board.put(new Tile(3,3), chessPieceFactory.King(TeamColor.WHITE, 5));
        board.put(new Tile(5,2), chessPieceFactory.Pawn(TeamColor.WHITE, 0));
        board.put(new Tile(5,5), chessPieceFactory.Pawn(TeamColor.BLACK, 1));
        return new Board(board,8,8);
    }
}
