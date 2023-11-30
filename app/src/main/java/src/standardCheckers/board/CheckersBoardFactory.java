package src.standardCheckers.board;

import src.logic.board.BoardFactory;
import src.standardCheckers.piece.CheckersPieceFactory;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class CheckersBoardFactory implements BoardFactory {
    private final CheckersPieceFactory checkersPieceFactory = new CheckersPieceFactory();
    @Override
    public Board newBoard(TeamColor color1, TeamColor color2){
        Map<Tile, Piece> board = insertMans(new HashMap<>(),8 ,color1, color2);
        return new Board(board,8,8);
    }
    private Map<Tile, Piece> insertMans(Map<Tile, Piece> board,int width, TeamColor color1, TeamColor color2){
        int pieceIdCounter = 0;
        for(int x = 0; x < width; x=x+2 ){
            board.put(new Tile(x,0),checkersPieceFactory.man(color1, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 1; x < width; x=x+2 ){
            board.put(new Tile(x,1),checkersPieceFactory.man(color1, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 0; x < width; x=x+2 ){
            board.put(new Tile(x,2),checkersPieceFactory.man(color1, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 1; x < width; x=x+2 ){
            board.put(new Tile(x,5),checkersPieceFactory.man(color2, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 0; x < width; x=x+2 ){
            board.put(new Tile(x,6),checkersPieceFactory.man(color2, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 1; x < width; x=x+2 ){
            board.put(new Tile(x,7),checkersPieceFactory.man(color2, pieceIdCounter));
            pieceIdCounter++;
        }
        return board;
    }
}