package src.engine;

import edu.austral.dissis.chess.gui.GameEngine;
import src.GameFactory.GameFactory;
import src.adapter.CapablancaGameEngineAdapter;
import src.adapter.ChessGameEngineAdapter;
import src.adapter.InternationalCheckersGameEngineAdapter;
import src.adapter.StandardCheckersGameEngineAdapter;

public class EngineFactory {
    private final GameFactory gameFactory = new GameFactory();
    public GameEngine ChessEngine(){
        return new EngineImplementation(gameFactory.createChess(), new ChessGameEngineAdapter());
    }
    public GameEngine CapablancaChessEngine(){
        return new EngineImplementation(gameFactory.createCapablancaChess(), new CapablancaGameEngineAdapter());
    }
    public GameEngine StandardCheckersEngine(){
        return new EngineImplementation(gameFactory.createCheckers(), new StandardCheckersGameEngineAdapter());
    }
    public GameEngine InternationalCheckersEngine(){
        return new EngineImplementation(gameFactory.createInternationalCheckers(), new InternationalCheckersGameEngineAdapter());
    }
}
