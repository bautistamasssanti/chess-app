package src.checkersGames.commons.gameMode.gameRules;

import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameMode.GameRule;
import src.logic.gameState.GameState;

import java.util.List;

public class EndOfDoubleJumpWithLessPieces implements GameRule {
    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        if(gameStates.size() < 3)
            return true;
        GameState currentState = gameStates.get(gameStates.size() - 1);
        GameState previousState = gameStates.get(gameStates.size() - 2);
        GameState previousPreviousState = gameStates.get(gameStates.size() - 3);
        if(currentState.getColorTurn() != previousState.getColorTurn())
            if(previousState.getColorTurn() == previousPreviousState.getColorTurn())
                return isPreviousTurnOpponentWithLessPieces(gameStates, previousState.getColorTurn());
        return true;
    }
    private boolean isPreviousTurnOpponentWithLessPieces(List<GameState> gameStates, TeamColor color){
        Board currentBoard = gameStates.get(gameStates.size() - 1).getBoard();
        Board previousBoard = gameStates.get(gameStates.size() - 2).getBoard();
        TeamColor opponentColor = getOpponentColor(color, gameStates);
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
        return "EndOfDoubleJumpWithLessPieces";
    }
}
