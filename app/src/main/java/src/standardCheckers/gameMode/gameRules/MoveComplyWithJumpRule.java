package src.standardCheckers.gameMode.gameRules;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameMode.GameRule;
import src.logic.gameState.GameState;

import java.util.List;

public class MoveComplyWithJumpRule implements GameRule {

    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        TeamColor currentTurnColor = gameStates.get(gameStates.size() - 1).getColorTurn();
        TeamColor previousTurnColor = gameStates.get(gameStates.size() - 2).getColorTurn();
        if(areTeamColorEquals(currentTurnColor, previousTurnColor)){
            return isPreviousTurnOpponentWithLessPieces(gameStates, currentTurnColor);
        }
        return true;
    }
    private boolean areTeamColorEquals(TeamColor currentTurnColor, TeamColor previousTurnColor){
        return currentTurnColor == previousTurnColor;
    }
    private boolean isPreviousTurnOpponentWithLessPieces(List<GameState> gameStates, TeamColor currentTurnColor){
        Board currentBoard = gameStates.get(gameStates.size() - 1).getBoard();
        Board previousBoard = gameStates.get(gameStates.size() - 2).getBoard();
        TeamColor opponentColor = getOpponentColor(currentTurnColor, gameStates);
        List<Tile> currentOpponentTiles = currentBoard.getTeamTiles(opponentColor);
        List<Tile> previousOpponentTiles = previousBoard.getTeamTiles(opponentColor);
        return currentOpponentTiles.size() < previousOpponentTiles.size();
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
