package src.StandardCheckers.gameMode.gameRules;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.gameMode.GameRule;
import src.logic.gameState.GameState;

import java.util.List;

public class MoveComplyWithJumpRule implements GameRule {

    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        TeamColor currentTurnColor = gameStates.get(gameStates.size() - 1).getColorTurn();
        TeamColor previousTurnColor = gameStates.get(gameStates.size() - 2).getColorTurn();
        if(currentTurnColor == previousTurnColor){
            List<Tile> currentOpponentTiles = gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(getOpponentColor(currentTurnColor, gameStates));
            List<Tile> previousOpponentTiles = gameStates.get(gameStates.size() - 2).getBoard().getTeamTiles(getOpponentColor(currentTurnColor, gameStates));
            System.out.println(currentOpponentTiles.size() + " " + previousOpponentTiles.size());
            return currentOpponentTiles.size() < previousOpponentTiles.size();
        }
        return true;
    }
    private TeamColor getOpponentColor(TeamColor currentTurnColor, List<GameState> gameStates){
        if(gameStates.get(0).getTeamAPlayer().getColor() == currentTurnColor){
            return gameStates.get(0).getTeamBPlayer().getColor();
        }
        else{
            return gameStates.get(0).getTeamAPlayer().getColor();
        }

    }

    @Override
    public String getGameRuleName() {
        return "MoveComplyWithJumpRule";
    }
}
