package src.logic.gameMode;

import src.logic.board.Board;
import src.logic.TeamColor;
import src.logic.gameState.GameState;
import src.logic.Player;

import java.util.List;

public interface GameMode {
    boolean isGameADraw(List<GameState> gameStates);
    boolean isGameWonByPlayer(Player player, List<GameState> gameStates);
    List<GameState> isBoardStateValid(List<GameState> gameStates);
    List<GameState> getInitialState(Board board, Player teamAPlayer, Player teamBPlayer, TeamColor initialTurn);
}
