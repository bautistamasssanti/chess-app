package src.logic.moveRules.basicRules.path;

import src.logic.Tile;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;

import java.util.List;

public class IsRivalPieceNotInDestination implements MoveRule {
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        if(!gameStates.get(gameStates.size() - 1).getBoard().getBoard().containsKey(destination)){
            return MoveType.BASIC;
        }
        if(gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(destination).getColor() != gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(origin).getColor()){
            return MoveType.BASIC;
        }
        return MoveType.INVALID;
    }
}
