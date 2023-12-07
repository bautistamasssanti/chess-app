package src.engine.deprecated;

import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import src.adapter.GameEngineAdapter;
import src.logic.Player;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.TurnController.TurnController;
import src.logic.board.Board;
import src.logic.board.BoardFactory;
import src.logic.gameMode.GameMode;
import src.logic.gameState.GameState;
import src.logic.gameState.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class GameEngineImplementation implements GameEngine {
    private List<GameState> gameStates = new ArrayList<>();
    private final Player playerA;
    private final Player playerB;
    private final TurnController turnController;
    private final GameMode gameMode;
    private final GameEngineAdapter engineAdapter;
    private final BoardFactory boardFactory;
    private final TeamColor initialTurn;

    public GameEngineImplementation(Player playerA, Player playerB, TurnController turnController, GameMode gameMode, GameEngineAdapter engineAdapter, BoardFactory boardFactory, TeamColor initialTurn) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.turnController = turnController;
        this.gameMode = gameMode;
        this.engineAdapter = engineAdapter;
        this.boardFactory = boardFactory;
        this.initialTurn = initialTurn;
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        List<Tile> tile = engineAdapter.adaptTiles(move);
        List<GameState> moveResult = turnController.applyMove(gameStates.get(gameStates.size()-1).getCurrentTurnPlayer(),tile.get(0), tile.get(1), gameStates);
        if(moveResult.size() == gameStates.size()){
            return new InvalidMove("move not valid");
        }
        gameStates = moveResult;
        if(gameStates.get(gameStates.size()-1).getGameStatus()!= src.logic.gameState.GameStatus.InProgress){
            return gameOver();
        }
        return new NewGameState(engineAdapter.getCurrentPieces(gameStates.get(gameStates.size()-1).getBoard()), engineAdapter.getCurrentTurn(gameStates.get(gameStates.size()-1)));
    }
    private GameOver gameOver(){
        if(gameStates.get(gameStates.size()-1).getGameStatus() == GameStatus.TeamAWon){
            return new GameOver(engineAdapter.adaptPlayerColor(playerA.getColor()));
        }
        else if(gameStates.get(gameStates.size()-1).getGameStatus() == GameStatus.TeamBWon){
            return new GameOver(engineAdapter.adaptPlayerColor(playerB.getColor()));
        }

        else{
            System.out.println("draw");
            return new GameOver(engineAdapter.adaptPlayerColor(initialTurn));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        Board newBoard = boardFactory.newBoard(playerA.getColor(), playerB.getColor());
        gameStates = gameMode.getInitialState(newBoard, playerA, playerB, initialTurn);
        return new InitialState(engineAdapter.getBoardSize(newBoard), engineAdapter.getCurrentPieces(newBoard), engineAdapter.getCurrentTurn(gameStates.get(0)));
    }
}
