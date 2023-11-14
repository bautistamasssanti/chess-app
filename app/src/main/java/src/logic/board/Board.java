package src.logic.board;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.piece.Piece;
import src.logic.piece.PieceType;

import java.util.*;

public class Board {
    private final Map<Tile, Piece> board;
    private final int width;
    private final int length;
    public Board(Map<Tile, Piece> board, int width, int length){
        this.board = board;
        this.width = width;
        this.length = length;
    }
    public Map<Tile, Piece> getBoard() {
        return board;
    }
    public List<Tile> getOccupiedTileFromPieceTypeAndColor(PieceType pieceType, TeamColor teamColor){
        List<Tile> list = new ArrayList<>();
        for (Map.Entry<Tile, Piece> entry : board.entrySet()) {
            if(entry.getValue().getType().equals(pieceType) && entry.getValue().getColor().equals(teamColor)){
                list.add(entry.getKey());
            }
        }
        return Collections.unmodifiableList(list);
    }
    public List<Tile> getTeamTiles(TeamColor teamColor){
        List<Tile> list = new ArrayList<>();
        for (Map.Entry<Tile, Piece> entry : board.entrySet()) {
            if(entry.getValue().getColor().equals(teamColor)){
                list.add(entry.getKey());
            }
        }
        return Collections.unmodifiableList(list);
    }
    public List<Tile> getOccupiedTiles(){
        List<Tile> list = new ArrayList<>();
        for (Map.Entry<Tile, Piece> entry : board.entrySet()) {
            list.add(entry.getKey());
        }
        return Collections.unmodifiableList(list);
    }
    public Map<Tile, Piece> getBoardCopy(){
        return new HashMap<>() {{
            putAll(board);
        }};

    }


    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
