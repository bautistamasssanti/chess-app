package src.engine;

import edu.austral.dissis.chess.gui.GameEngine;
import src.adapter.*;
import src.chessGames.capablancaChess.board.CapablancaBoardFactory;
import src.chessGames.capablancaChess.gameMode.CapablancaGameModeFactory;
import src.chessGames.capablancaChess.turnController.CapablancaTurnController;
import src.chessGames.chess.board.ChessBoardFactory;
import src.chessGames.chess.gameMode.ChessGameModeFactory;
import src.chessGames.chess.turnController.ChessTurnController;
import src.checkersGames.internationalCheckers.board.InternationalCheckersBoardFactory;
import src.checkersGames.internationalCheckers.gameMode.InternationalCheckersGameModeFactory;
import src.checkersGames.internationalCheckers.turnController.InternationalCheckersTurnController;
import src.logic.Player;
import src.logic.TeamColor;
import src.logic.TurnController.TurnController;
import src.logic.board.BoardFactory;
import src.logic.gameMode.GameMode;
import src.checkersGames.standardCheckers.board.CheckersBoardFactory;
import src.checkersGames.standardCheckers.gameMode.StandardCheckersGameModeFactory;
import src.checkersGames.standardCheckers.turnController.CheckersTurnController;

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
    public GameEngine capablancaChessGameEngine(){
        Player playerA = new Player(TeamColor.WHITE);
        Player playerB = new Player(TeamColor.BLACK);
        GameMode gameMode = new CapablancaGameModeFactory().capablancaChess();
        TurnController turnController = new CapablancaTurnController(gameMode);
        GameEngineAdapter adapter = new CapablancaGameEngineAdapter();
        BoardFactory boardFactory = new CapablancaBoardFactory();
        return new GameEngineImplementation(playerA, playerB,turnController,gameMode,adapter, boardFactory, TeamColor.WHITE);
    }
}
