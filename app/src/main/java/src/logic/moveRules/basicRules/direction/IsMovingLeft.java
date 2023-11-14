package src.logic.moveRules.basicRules.direction;

import src.logic.gameState.GameState;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;

import java.util.List;

public class IsMovingLeft implements MoveRule {
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        TeamColor pieceColor = gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(origin).getColor();
        if(pieceColor == gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor()){
            if(destination.getX() < origin.getX()){
                return MoveType.BASIC;
            }
            return MoveType.INVALID;
        } else {
            if(destination.getX() > origin.getX()){
                return MoveType.BASIC;
            }
            return MoveType.INVALID;
        }
    }
}
