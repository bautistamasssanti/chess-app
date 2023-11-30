package src.engine;

import edu.austral.dissis.chess.gui.GameEngine;
import src.adapter.ChessGameEngineAdapter;
import src.adapter.GameEngineAdapter;
import src.adapter.InternationalCheckersGameEngineAdapter;
import src.adapter.StandardCheckersGameEngineAdapter;
import src.chess.factories.ChessBoardFactory;
import src.chess.gameMode.ChessGameModeFactory;
import src.chess.turnController.ChessTurnController;
import src.internationalCheckers.board.InternationalCheckersBoardFactory;
import src.internationalCheckers.gameMode.InternationalCheckersGameModeFactory;
import src.internationalCheckers.turnController.InternationalCheckersTurnController;
import src.logic.Player;
import src.logic.TeamColor;
import src.logic.TurnController.TurnController;
import src.logic.board.BoardFactory;
import src.logic.gameMode.GameMode;
import src.standardCheckers.board.CheckersBoardFactory;
import src.standardCheckers.gameMode.StandardCheckersGameModeFactory;
import src.standardCheckers.turnController.CheckersTurnController;

public class GameEngineFactory {
    public GameEngine chessGameEngine(){
        Player playerA = new Player(TeamColor.WHITE);
        Player playerB = new Player(TeamColor.BLACK);
        GameMode gameMode = new ChessGameModeFactory().standardChess();
        TurnController turnController = new ChessTurnController(gameMode);
        GameEngineAdapter adapter = new ChessGameEngineAdapter();
        BoardFactory boardFactory = new ChessBoardFactory();
        return new GameEngineImplementation(playerA, playerB,turnController,gameMode,adapter, boardFactory, TeamColor.WHITE);
    }
    public GameEngine standardCheckersGameEngine(){
        Player playerA = new Player(TeamColor.WHITE);
        Player playerB = new Player(TeamColor.BLACK);
        GameMode gameMode = new StandardCheckersGameModeFactory().checkers();
        TurnController turnController = new CheckersTurnController(gameMode);
        GameEngineAdapter adapter = new StandardCheckersGameEngineAdapter();
        BoardFactory boardFactory = new CheckersBoardFactory();
        return new GameEngineImplementation(playerA, playerB,turnController,gameMode,adapter, boardFactory, TeamColor.BLACK);
    }
    public GameEngine internationalCheckersGameEngine(){
        Player playerA = new Player(TeamColor.WHITE);
        Player playerB = new Player(TeamColor.BLACK);
        GameMode gameMode = new InternationalCheckersGameModeFactory().internationalCheckers();
        TurnController turnController = new InternationalCheckersTurnController(gameMode);
        GameEngineAdapter adapter = new InternationalCheckersGameEngineAdapter();
        BoardFactory boardFactory = new InternationalCheckersBoardFactory();
        return new GameEngineImplementation(playerA, playerB,turnController,gameMode,adapter, boardFactory, TeamColor.BLACK);
    }
}
