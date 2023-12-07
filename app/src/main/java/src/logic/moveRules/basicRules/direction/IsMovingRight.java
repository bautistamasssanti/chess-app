package src.logic.moveRules.basicRules.direction;

import src.logic.gameState.GameState;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;

import java.util.List;

public class IsMovingRight implements MoveRule {
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        TeamColor pieceColor = gameStates.get(gameStates.size() - 1).getBoard().getPiece(origin).getColor();
        TeamColor teamAColor = gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor();
        if(pieceColor == teamAColor){
            if(destination.getX() > origin.getX()){
                return MoveType.BASIC;
            }
            return MoveType.INVALID;
        } else {
            if(destination.getX() < origin.getX()){
                return MoveType.BASIC;
            }
            return MoveType.INVALID;
        }
    }
}
