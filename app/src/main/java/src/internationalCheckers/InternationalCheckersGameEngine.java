package src.internationalCheckers;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import src.adapter.InternationalCheckersGameEngineAdapter;
import src.internationalCheckers.board.InternationalCheckersBoardFactory;
import src.internationalCheckers.gameMode.InternationalCheckersGameModeFactory;
import src.internationalCheckers.turnController.InternationalCheckersTurnController;
import src.logic.Player;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameMode.GameMode;
import src.logic.gameState.GameState;
import src.logic.gameState.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class InternationalCheckersGameEngine implements GameEngine{
    private final InternationalCheckersGameEngineAdapter gameEngineAdapter = new InternationalCheckersGameEngineAdapter();
    private final GameMode gameMode = new InternationalCheckersGameModeFactory().internationalCheckers();
    private final InternationalCheckersTurnController turnController = new InternationalCheckersTurnController(gameMode);
    private List<GameState> gameStates = new ArrayList<>();
    private final Player playerA = new Player(TeamColor.WHITE);
    private final Player playerB = new Player(TeamColor.BLACK);

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        List<Tile> tiles = gameEngineAdapter.adaptTiles(move);
        List<GameState> moveResult = turnController.applyMove(gameStates.get(gameStates.size()-1).getCurrentTurnPlayer(),tiles.get(0), tiles.get(1), gameStates);
        if(moveResult.size() == gameStates.size()){
            return new InvalidMove("move not valid");
        }
        gameStates = moveResult;
        if(gameStates.get(gameStates.size()-1).getGameStatus()!= GameStatus.InProgress){
            if(gameStates.get(gameStates.size()-1).getGameStatus() == GameStatus.TeamAWon){
                return new GameOver(gameEngineAdapter.adaptPlayerColor(playerA.getColor()));
            }
            else if(gameStates.get(gameStates.size()-1).getGameStatus() == GameStatus.TeamBWon){
                return new GameOver(gameEngineAdapter.adaptPlayerColor(playerB.getColor()));
            }

            else{
                System.out.println("draw");
                return new GameOver(gameEngineAdapter.adaptPlayerColor(TeamColor.WHITE));
            }
        }
        return new NewGameState(gameEngineAdapter.getCurrentPieces(gameStates.get(gameStates.size()-1).getBoard()), gameEngineAdapter.getCurrentTurn(gameStates.get(gameStates.size()-1)));
    }

    @NotNull
    @Override
    public InitialState init() {
        Board gameBoard = new InternationalCheckersBoardFactory().newBoard(playerA.getColor(), playerB.getColor());
        gameStates = gameMode.getInitialState(gameBoard, playerA, playerB, TeamColor.BLACK);
        return new InitialState(gameEngineAdapter.getBoardSize(gameBoard), gameEngineAdapter.getCurrentPieces(gameBoard), gameEngineAdapter.getCurrentTurn(gameStates.get(0)));
    }
}
