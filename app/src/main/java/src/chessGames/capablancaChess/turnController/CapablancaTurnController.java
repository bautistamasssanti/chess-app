package src.chessGames.capablancaChess.turnController;

import src.chessGames.capablancaChess.gameState.CapablancaGameStateFactory;
import src.logic.Player;
import src.logic.Tile;
import src.logic.TurnController.TurnController;
import src.logic.exceptions.GameRuleUnfullfilledException;
import src.logic.exceptions.NotPlayerTurnException;
import src.logic.gameMode.EndGameFactory;
import src.logic.gameMode.GameMode;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;

import java.util.List;

public class CapablancaTurnController implements TurnController {
    private final GameMode gameMode;
    private final CapablancaGameStateFactory capablancaGameStateFactory = new CapablancaGameStateFactory();

    public CapablancaTurnController(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public List<GameState> applyMove(Player player, Tile origin, Tile destination, List<GameState> gameStates) {
        try{
            if (player.getColor() != gameStates.get(gameStates.size() - 1).getCurrentTurnPlayer().getColor())
                throw new NotPlayerTurnException();
            MoveType moveType = player.canMovePiece(origin, destination, gameStates);
            if(moveType == MoveType.INVALID)
                throw new GameRuleUnfullfilledException("Invalid move");
            List<GameState> newGameState = capablancaGameStateFactory.movePiece(moveType, origin, destination, gameStates);
            newGameState = gameMode.isBoardStateValid(newGameState);
            return newGameState;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return gameStates;
        }
    }
}
