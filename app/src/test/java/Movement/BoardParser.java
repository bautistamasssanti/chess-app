package Movement;


import src.logic.Player;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.piece.Piece;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static src.logic.TeamColor.BLACK;
import static src.logic.TeamColor.WHITE;
import static src.logic.gameState.GameStatus.InProgress;


public class BoardParser {
    public boolean parseChessBoardFormFile(String filePath, Piece piece, Tile startingPosition) throws Exception {
        List<String> lines = new ArrayList<>();
        try{
            File file = new File(filePath);
            if(file.exists()){
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            } else {
                throw new FileNotFoundException("File not found in the following route: " + filePath);
            }
        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
        if (lines.size() != 8) {
            System.out.println("Invalid chess board format. The file should contain exactly 8 lines. Currently, it is " + lines.size());
            return false;
        }
        for (int y = 0; y < 8; y++) {
            String line = lines.get(y);
            if (line.length() != 15) {
                System.out.println("Invalid chess board format. Each line should have exactly 15 characters. Line number " + y + " has " + line.length());
                return false;
            }
            int spacesCounter = 0;
            System.out.println(piece.getColor().toString());
            for (int x = 0; x < 15; x++){
                char character = line.charAt(x);
                if (character != ' '){
                    System.out.println(character);
                    Tile coordinate = new Tile(x - spacesCounter, y);
                    if (!pieceAnswersCorrectly(character, coordinate, piece, startingPosition)) {
                        System.out.println("Piece answered incorrectly for " +"[" + coordinate.getX() + "," + coordinate.getY() + "]");
                        return false;
                    }
                    System.out.println("Piece answered correctly for " +"[" + coordinate.getX() + "," + coordinate.getY() + "]");
                    System.out.println("----------------------------");
                }
                else spacesCounter++;
            }
        }
        return true;
    }
    public boolean pieceAnswersCorrectly(char character, Tile coordinate, Piece piece, Tile startingPosition) throws Exception {
        Board board = new Board(Map.of(startingPosition, piece), 8, 8);
        Player A = new Player(WHITE);
        Player B = new Player(BLACK);
        GameState gameState = new GameState(board,InProgress,A,B,piece.getColor());
        List<GameState> history = List.of(gameState);
        MoveType isValidMove;
        if(piece.getColor() == A.getColor())
            isValidMove = A.canMovePiece(startingPosition, coordinate, history);
        else
            isValidMove = B.canMovePiece(startingPosition, coordinate, history);
        if(character == 'x'){
            return isValidMove != MoveType.INVALID;
        }
        if(character == '-'){

            return isValidMove == MoveType.INVALID;
        }
        if(character == 'p'){
            if (!coordinate.equals(startingPosition)) {
                throw new Exception("The piece was not located correctly, should be in " + "[" + coordinate.getX() + "," + coordinate.getY() + "] " +" but it is in " + startingPosition.getX() + "," + startingPosition.getY());
            } else {
                return true;
            }
        }
        return false;
    }
}
