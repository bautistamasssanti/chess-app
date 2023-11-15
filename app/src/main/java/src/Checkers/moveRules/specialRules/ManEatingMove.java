package src.Checkers.moveRules.specialRules;

import src.logic.Tile;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.length.IsMovingInYAxisByMaxNTiles;
import src.logic.moveRules.basicRules.length.IsMovingInYAxisByMinNTiles;
import src.logic.moveRules.basicRules.path.IsDestinationTileFree;
import src.logic.moveRules.basicRules.path.IsDiagonalPathFreeToCross;
import src.logic.moveRules.basicRules.shape.IsDiagonalMove;

import java.util.ArrayList;
import java.util.List;

public class ManEatingMove implements MoveRule {
    IsMovingInYAxisByMaxNTiles isMovingInYAxisByMaxNTiles = new IsMovingInYAxisByMaxNTiles(2);
    IsMovingInYAxisByMinNTiles isMovingInYAxisByMinNTiles = new IsMovingInYAxisByMinNTiles(2);
    IsDiagonalMove isDiagonalMove = new IsDiagonalMove();
    IsDestinationTileFree isDestinationTileFree = new IsDestinationTileFree();
    IsDiagonalPathFreeToCross isDiagonalPathFreeToCross = new IsDiagonalPathFreeToCross();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        if (isMovingInYAxisByMaxNTiles.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isMovingInYAxisByMinNTiles.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isDiagonalMove.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isDestinationTileFree.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isDiagonalPathFreeToCross.isValidMove(origin, destination,gameStates) != MoveType.INVALID)
            return MoveType.INVALID;
        List<Tile> possibleAttackTiles = getPossibleAttackTiles(destination, gameStates);
        if(!possibleAttackTiles.isEmpty()){
            if(arePossibleAttacks(destination, possibleAttackTiles, gameStates)){
                return MoveType.MULTI_JUMP;
            }
        }
        return MoveType.JUMP;
    }
    private List<Tile> getPossibleAttackTiles(Tile destination, List<GameState> gameStates){
        List<Tile> possibleAttackTiles = new ArrayList<>();
        int x = destination.getX();
        int y = destination.getY();
        if(x-2 >= 0 && y-2 >= 0){
            possibleAttackTiles.add(new Tile(x-2, y-2));
        }
        if(x-2 >= 0 && y+2 < gameStates.get(0).getBoard().getLength()){
            possibleAttackTiles.add(new Tile(x-2, y+2));
        }
        if(x+2 < gameStates.get(0).getBoard().getWidth() && y-2 >= 0){
            possibleAttackTiles.add(new Tile(x+2, y-2));
        }
        if(x+2 < gameStates.get(0).getBoard().getWidth() && y+2 < gameStates.get(0).getBoard().getLength()){
            possibleAttackTiles.add(new Tile(x+2, y+2));
        }
        return possibleAttackTiles;
    }
    private boolean arePossibleAttacks(Tile origin, List<Tile> possibleDestinations, List<GameState> gameStates){
        for(Tile possibleDestination : possibleDestinations){
            if(isValidMove(origin, possibleDestination, gameStates) != MoveType.INVALID){
                return true;
            }
        }
        return false;
    }
}
