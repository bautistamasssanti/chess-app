package src.engine;

import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import src.adapter.GameEngineAdapter;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.game.Game;
import src.logic.gameState.GameState;
import src.logic.gameState.GameStatus;

import java.util.List;

public class EngineImplementation implements GameEngine {
    private Game game;
    private final GameEngineAdapter engineAdapter;

    public EngineImplementation(Game game,GameEngineAdapter engineAdapter) {
        this.game = game;
        this.engineAdapter = engineAdapter;
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        List<GameState> initialStates = game.getGameStates();
        List<Tile> tiles = engineAdapter.adaptTiles(move);
        List<GameState> moveResult = game.move(tiles.get(0), tiles.get(1));
        if(moveResult.size() == initialStates.size()){
            return new InvalidMove("move not valid");
        }
        if(moveResult.get(moveResult.size()-1).getGameStatus()!= GameStatus.InProgress){
            return gameOver(moveResult);
        }
        return new NewGameState(engineAdapter.getCurrentPieces(moveResult.get(moveResult.size()-1).getBoard()), engineAdapter.getCurrentTurn(moveResult.get(moveResult.size()-1)));
    }
    private GameOver gameOver(List<GameState> moveResults){
        GameState moveResult = moveResults.get(moveResults.size()-1);
        if(moveResult.getGameStatus() == GameStatus.TeamAWon){
            return new GameOver(engineAdapter.adaptPlayerColor(moveResult.getTeamAPlayer().getColor()));
        }
        else if(moveResults.get(moveResults.size()-1).getGameStatus() == GameStatus.TeamBWon){
            return new GameOver(engineAdapter.adaptPlayerColor(moveResult.getTeamBPlayer().getColor()));
        }
        else{
            System.out.println("draw");
            return new GameOver(engineAdapter.adaptPlayerColor(moveResults.get(0).getColorTurn()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        Board board = game.getGameStates().get(0).getBoard();
        return new InitialState(engineAdapter.getBoardSize(board), engineAdapter.getCurrentPieces(board), engineAdapter.getCurrentTurn(game.getGameStates().get(0)));
    }
}
