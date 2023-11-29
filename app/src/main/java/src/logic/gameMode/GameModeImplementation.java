package src.logic.gameMode;

import src.logic.board.Board;
import src.logic.TeamColor;
import src.logic.exceptions.GameRuleUnfullfilledException;
import src.logic.gameState.GameState;
import src.logic.Player;
import src.logic.gameState.GameStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameModeImplementation implements GameMode{
    private final WinCondition[] winConditions;
    private final DrawCondition[] drawConditions;
    private final GameRule[] gameRules;
    private final OptionalGameRule[] optionalGameRules;

    public GameModeImplementation(WinCondition[] winConditions, DrawCondition[] drawConditions, GameRule[] gameRules, OptionalGameRule[] optionalGameRules) {
        this.winConditions = winConditions;
        this.drawConditions = drawConditions;
        this.gameRules = gameRules;
        this.optionalGameRules = optionalGameRules;
    }


    @Override
    public boolean isGameADraw(List<GameState> gameStates) {
        for (DrawCondition drawCondition : drawConditions) {
            if (drawCondition.isGameADraw(gameStates)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isGameWonByPlayer(Player player, List<GameState> gameStates) {

        for (WinCondition winCondition : winConditions) {
            if (winCondition.isGameWonByPlayer(player, gameStates)) {

                return true;
            }
        }
        return false;
    }

    @Override
    public List<GameState> isBoardStateValid(List<GameState> gameStates) {
        List<GameState> stateToCheck = getGameStateToCheck(gameStates);
        try{
            areGameRulesValid(stateToCheck);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return getPreviousState(gameStates);
        }
        return stateToCheck;
    }
    private void areGameRulesValid(List<GameState> gameStates) throws GameRuleUnfullfilledException {
        for (GameRule gameRule : gameRules) {
            if (!gameRule.isGameRuleValid(gameStates)) {
                throw new GameRuleUnfullfilledException(gameRule.getGameRuleName());
            }
        }
    }
    private List<GameState> getPreviousState(List<GameState> gameStates){
        return new ArrayList<>(gameStates.subList(0, gameStates.size() - 1));
    }



    private List<GameState> getGameStateToCheck(List<GameState> initialState){
        List<GameState> stateToCheck = initialState;
        for (OptionalGameRule optionalGameRule : optionalGameRules) {
            stateToCheck = optionalGameRule.isOptionalConditionulfilled(stateToCheck);
        }
        return stateToCheck;
    }
    @Override
    public List<GameState> getInitialState(Board board, Player teamAPlayer, Player teamBPlayer, TeamColor initialTurn) {
        GameState initialState = new GameState(board, GameStatus.InProgress, teamAPlayer, teamBPlayer, initialTurn);
        List<GameState> state = new ArrayList<>();
        state.add(initialState);
        return Collections.unmodifiableList(state);
    }
}
