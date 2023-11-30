package src.App.listener.client;

import edu.austral.dissis.chess.gui.GameStateListener;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.dissis.chess.gui.MoveResult;
import org.jetbrains.annotations.NotNull;

public class GameStateListenerImplementation implements GameStateListener {
    private final GameView gameView;

    public GameStateListenerImplementation(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void handleInitialState(@NotNull InitialState initialState) {
        gameView.handleInitialState(initialState);
    }

    @Override
    public void handleMoveResult(@NotNull MoveResult moveResult) {
        gameView.handleMoveResult(moveResult);
    }
}
