package src.logic.gameMode;

import src.logic.Player;
import src.logic.gameState.GameState;
import src.logic.gameState.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class EndGameFactory {
    public List<GameState> victoryFactory(Player player, List<GameState> gameStates){
        GameStatus gameStatus = GameStatus.InProgress;
        if(player.getColor() == gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor()){
            gameStatus = GameStatus.TeamAWon;
        }
        else if(player.getColor() == gameStates.get(gameStates.size() - 1).getTeamBPlayer().getColor()){
            gameStatus = GameStatus.TeamBWon;
        }
        GameState gameState = new GameState(gameStates.get(gameStates.size() - 1).getBoard(), gameStatus, gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), gameStates.get(gameStates.size() - 1).getColorTurn());
        List<GameState> updatedGameStates = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1));
        updatedGameStates.add(gameState);
        return updatedGameStates;
    }
    public List<GameState> drawFactory(List<GameState> gameStates){
        GameState gameState = new GameState(gameStates.get(gameStates.size() - 1).getBoard(), GameStatus.Draw, gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), gameStates.get(gameStates.size() - 1).getColorTurn());
        List<GameState> updatedGameStates = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1));
        updatedGameStates.add(gameState);
        return updatedGameStates;
    }
}
