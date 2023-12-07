package src.GameFactory;

import src.checkersGames.internationalCheckers.board.InternationalCheckersBoardFactory;
import src.checkersGames.internationalCheckers.gameMode.InternationalCheckersGameModeFactory;
import src.checkersGames.internationalCheckers.turnController.InternationalCheckersTurnController;
import src.checkersGames.standardCheckers.board.CheckersBoardFactory;
import src.checkersGames.standardCheckers.gameMode.StandardCheckersGameModeFactory;
import src.checkersGames.standardCheckers.turnController.CheckersTurnController;
import src.chessGames.capablancaChess.board.CapablancaBoardFactory;
import src.chessGames.capablancaChess.gameMode.CapablancaGameModeFactory;
import src.chessGames.capablancaChess.turnController.CapablancaTurnController;
import src.chessGames.chess.board.ChessBoardFactory;
import src.chessGames.chess.gameMode.ChessGameModeFactory;
import src.chessGames.chess.turnController.ChessTurnController;
import src.logic.Player;
import src.logic.TeamColor;
import src.logic.TurnController.TurnController;
import src.logic.board.Board;
import src.logic.game.Game;
import src.logic.gameMode.GameMode;

public class GameFactory {
    public Game createCheckers(){
        GameMode gameMode = new StandardCheckersGameModeFactory().checkers();
        TurnController turnController = new CheckersTurnController(gameMode);
        Player playerA = new Player(TeamColor.WHITE);
        Player playerB = new Player(TeamColor.BLACK);
        Board board = new CheckersBoardFactory().newBoard(playerA.getColor(), playerB.getColor());
        return new Game(turnController, playerA, playerB, gameMode, TeamColor.BLACK, board);
    }
    public Game createInternationalCheckers(){
        GameMode gameMode = new InternationalCheckersGameModeFactory().internationalCheckers();
        TurnController turnController = new InternationalCheckersTurnController(gameMode);
        Player playerA = new Player(TeamColor.WHITE);
        Player playerB = new Player(TeamColor.BLACK);
        Board board = new InternationalCheckersBoardFactory().newBoard(playerA.getColor(), playerB.getColor());
        return new Game(turnController, playerA, playerB, gameMode, TeamColor.BLACK, board);
    }
    public Game createChess(){
        GameMode gameMode = new ChessGameModeFactory().standardChess();
        TurnController turnController = new ChessTurnController(gameMode);
        Player playerA = new Player(TeamColor.WHITE);
        Player playerB = new Player(TeamColor.BLACK);
        Board board = new ChessBoardFactory().newBoard(playerA.getColor(), playerB.getColor());
        return new Game(turnController, playerA, playerB, gameMode, TeamColor.WHITE, board);
    }
    public Game createCapablancaChess(){
        GameMode gameMode = new CapablancaGameModeFactory().capablancaChess();
        TurnController turnController = new CapablancaTurnController(gameMode);
        Player playerA = new Player(TeamColor.WHITE);
        Player playerB = new Player(TeamColor.BLACK);
        Board board = new CapablancaBoardFactory().newBoard(playerA.getColor(), playerB.getColor());
        return new Game(turnController, playerA, playerB, gameMode, TeamColor.WHITE, board);
    }
}
