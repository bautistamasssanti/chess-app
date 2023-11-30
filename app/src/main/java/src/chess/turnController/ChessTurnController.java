package src.chess.turnController;

import src.chess.factories.ChessGameStateFactory;
import src.logic.TurnController.TurnController;
import src.logic.Player;
import src.logic.Tile;
import src.logic.exceptions.GameRuleUnfullfilledException;
import src.logic.exceptions.NotPlayerTurnException;
import src.logic.gameMode.EndGameFactory;
import src.logic.gameMode.GameMode;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;

import java.util.List;

public class ChessTurnController implements TurnController {
    private final GameMode gameMode;
    private final EndGameFactory endGameFactory = new EndGameFactory();
    private final ChessGameStateFactory chessGameStateFactory = new ChessGameStateFactory();

    public ChessTurnController(GameMode gameMode) {
        this.gameMode = gameMode;
    }


    @Override
    public List<GameState> applyMove(Player player,Tile origin, Tile destination, List<GameState> gameStates) {
        try{
            if (player.getColor() != gameStates.get(gameStates.size() - 1).getCurrentTurnPlayer().getColor()) {
                throw new NotPlayerTurnException();
            }
            MoveType moveType = player.canMovePiece(origin, destination, gameStates);
            if(moveType == MoveType.INVALID) {
                throw new GameRuleUnfullfilledException("Invalid move");
            }
            List<GameState> newGameState = chessGameStateFactory.movePiece(moveType, origin, destination, gameStates);
            newGameState = gameMode.isBoardStateValid(newGameState);
            return checkGameStatus(newGameState, player);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return gameStates;
        }
    }
    private List<GameState> checkGameStatus(List<GameState> newGameState, Player player){
        if(gameMode.isGameWonByPlayer(player, newGameState)){
            return endGameFactory.victoryFactory(player, newGameState);
        }
        else if(gameMode.isGameADraw(newGameState)){
            return endGameFactory.drawFactory(newGameState);
        }
        return newGameState;
    }
}