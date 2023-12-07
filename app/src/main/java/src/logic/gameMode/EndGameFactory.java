package src.logic.gameMode;

import src.logic.Player;
import src.logic.gameState.GameState;
import src.logic.gameState.GameStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EndGameFactory {
    public List<GameState> victoryFactory(Player player, List<GameState> gameStates){
        GameStatus gameStatus = GameStatus.InProgress;
        GameState previousGameState = gameStates.get(gameStates.size() - 1);
        if(player.getColor() == previousGameState.getTeamAPlayer().getColor()){
            gameStatus = GameStatus.TeamAWon;
        }
        else if(player.getColor() == previousGameState.getTeamBPlayer().getColor()){
            gameStatus = GameStatus.TeamBWon;
        }
        GameState gameState = new GameState(previousGameState.getBoard(), gameStatus, previousGameState.getTeamAPlayer(), previousGameState.getTeamBPlayer(), previousGameState.getColorTurn());
        List<GameState> updatedGameStates = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1));
        updatedGameStates.add(gameState);
        return Collections.unmodifiableList(updatedGameStates);
    }
    public List<GameState> drawFactory(List<GameState> gameStates){
        GameState previousGameState = gameStates.get(gameStates.size() - 1);
        GameState gameState = new GameState(previousGameState.getBoard(), GameStatus.Draw, previousGameState.getTeamAPlayer(), previousGameState.getTeamBPlayer(), previousGameState.getColorTurn());
        List<GameState> updatedGameStates = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1));
        updatedGameStates.add(gameState);
        return Collections.unmodifiableList(updatedGameStates);
    }
}
