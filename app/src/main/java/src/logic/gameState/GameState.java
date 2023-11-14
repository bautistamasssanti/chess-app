package src.logic.gameState;

import src.logic.board.Board;
import src.logic.Player;
import src.logic.TeamColor;


public class GameState {
    private final Board board;
    private final GameStatus gameStatus;
    private final Player teamAPlayer;
    private final Player teamBPlayer;
    private final TeamColor colorTurn;

    public GameState(Board board, GameStatus gameStatus, Player teamAPlayer, Player teamBPlayer, TeamColor colorTurn) {
        this.board = board;
        this.gameStatus = gameStatus;
        this.teamAPlayer = teamAPlayer;
        this.teamBPlayer = teamBPlayer;
        this.colorTurn = colorTurn;
    }
    public Board getBoard() {
        return board;
    }
    public Player getTeamAPlayer() {
        return teamAPlayer;
    }
    public Player getTeamBPlayer() {
        return teamBPlayer;
    }
    public TeamColor getColorTurn() {
        return colorTurn;
    }
    public TeamColor getColorInNextTurn(){
        if(colorTurn == teamAPlayer.getColor()){
            return teamBPlayer.getColor();
        }
        else return teamAPlayer.getColor();
    }
    public Player getCurrentTurnPlayer(){
        if(colorTurn == teamAPlayer.getColor()){
            return teamAPlayer;
        }
        else return teamBPlayer;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
