package src.logic.game;

import src.logic.Player;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.TurnController.TurnController;
import src.logic.board.Board;
import src.logic.gameMode.EndGameFactory;
import src.logic.gameMode.GameMode;
import src.logic.gameState.GameState;

import java.util.List;

public class Game {
    private List<GameState> gameStates;
    private final EndGameFactory endGameFactory = new EndGameFactory();
    private final TurnController turnController;
    private final Player playerA;
    private final Player playerB;
    private final GameMode gameMode;
    private final TeamColor initialTurn;


    public Game(TurnController turnController, Player playerA, Player playerB, GameMode gameMode, TeamColor initialTurn, Board board) {
        this.gameMode = gameMode;
        this.turnController = turnController;
        this.playerA = playerA;
        this.playerB = playerB;
        this.initialTurn = initialTurn;
        this.gameStates = generateInitialGameState(board);
    }
    public List<GameState> generateInitialGameState(Board board){
        return gameMode.getInitialState(board, playerA, playerB, initialTurn);
    }
    public List<GameState> move(Tile from, Tile to) {
        List<GameState> moveResult = turnController.applyMove(gameStates.get(gameStates.size()-1).getCurrentTurnPlayer(),from, to, gameStates);
        if(moveResult.size() == gameStates.size()){
            return gameStates;
        }
        gameStates = checkGameStatus(moveResult, gameStates.get(gameStates.size()-1).getCurrentTurnPlayer());
        return gameStates;
    }
    private List<GameState> checkGameStatus(List<GameState> newGameState, Player player){
        if(gameMode.isGameWonByPlayer(player, newGameState)){
            return endGameFactory.victoryFactory(player, newGameState);
        }
        else if(gameMode.isGameADraw(newGameState)){
            return endGameFactory.drawFactory(newGameState);
        }
        return newGameState;
    }

    public List<GameState> getGameStates() {
        return gameStates;
    }
}
