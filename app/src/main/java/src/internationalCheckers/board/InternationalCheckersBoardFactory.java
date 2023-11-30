package src.internationalCheckers.board;

import src.internationalCheckers.piece.CheckersPieceFactory;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.board.BoardFactory;
import src.logic.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class InternationalCheckersBoardFactory implements BoardFactory {
    private final CheckersPieceFactory checkersPieceFactory = new CheckersPieceFactory();
    @Override
    public Board newBoard(TeamColor color1, TeamColor color2){
        Map<Tile, Piece> board = insertMans(new HashMap<>(),10,color1, color2);
        return new Board(board,10,10);
    }
    private Map<Tile, Piece> insertMans(Map<Tile, Piece> board,int width, TeamColor color1, TeamColor color2){
        int pieceIdCounter = 0;
        for(int x = 0; x < width; x=x+2 ){
            board.put(new Tile(x,0),checkersPieceFactory.man(TeamColor.WHITE, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 1; x < width; x=x+2 ){
            board.put(new Tile(x,1),checkersPieceFactory.man(TeamColor.WHITE, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 0; x < width; x=x+2 ){
            board.put(new Tile(x,2),checkersPieceFactory.man(TeamColor.WHITE, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 1; x < width; x=x+2 ){
            board.put(new Tile(x,3),checkersPieceFactory.man(TeamColor.WHITE, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 0; x < width; x=x+2 ){
            board.put(new Tile(x,6),checkersPieceFactory.man(TeamColor.BLACK, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 1; x < width; x=x+2 ){
            board.put(new Tile(x,7),checkersPieceFactory.man(TeamColor.BLACK, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 0; x < width; x=x+2 ){
            board.put(new Tile(x,8),checkersPieceFactory.man(TeamColor.BLACK, pieceIdCounter));
            pieceIdCounter++;
        }
        for(int x = 1; x < width; x=x+2 ){
            board.put(new Tile(x,9),checkersPieceFactory.man(TeamColor.BLACK, pieceIdCounter));
            pieceIdCounter++;
        }
        return board;
    }
}
