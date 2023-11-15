package src.Checkers;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import src.Checkers.adapter.CheckersGameEngineAdapter;
import src.Checkers.gameMode.CheckersGameModeFactory;
import src.Checkers.turnController.CheckersTurnController;
import src.logic.Player;
import src.logic.TeamColor;
import src.logic.gameMode.GameMode;
import src.logic.gameState.GameState;

import java.util.ArrayList;
import java.util.List;

public class CheckersGameEngine implements GameEngine{
    private final CheckersGameEngineAdapter gameEngineAdapter = new CheckersGameEngineAdapter();
    private final GameMode gameMode = new CheckersGameModeFactory().internationalCheckers();
    private final CheckersTurnController turnController = new CheckersTurnController(gameMode);
    private final List<GameState> gameStates = new ArrayList<>();
    private final Player playerA = new Player(TeamColor.WHITE);
    private final Player playerB = new Player(TeamColor.BLACK);

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        return null;
    }

    @NotNull
    @Override
    public InitialState init() {
        return null;
    }
}
