package src.checkersGames.commons.moveRules.specialRules;

import src.logic.Tile;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.MoveType;
import src.logic.moveRules.basicRules.ArithmethicOperation;
import src.logic.moveRules.basicRules.length.IsMovingInYAxisByMaxNTiles;
import src.logic.moveRules.basicRules.length.IsMovingInYAxisByMinNTiles;
import src.logic.moveRules.basicRules.path.IsDestinationTileFree;
import src.logic.moveRules.basicRules.path.IsDiagonalPathFreeToCross;
import src.logic.moveRules.basicRules.path.IsRivalPieceOnDestination;
import src.logic.moveRules.basicRules.shape.IsDiagonalMove;

import java.util.ArrayList;
import java.util.List;

public class QueenEatingMove implements MoveRule {
    ArithmethicOperation arithmethicOperation = new ArithmethicOperation();
    IsDiagonalMove isDiagonalMove = new IsDiagonalMove();
    IsDiagonalPathFreeToCross isDiagonalPathFreeToCross = new IsDiagonalPathFreeToCross();
    IsDestinationTileFree isDestinationTileFree = new IsDestinationTileFree();
    IsMovingInYAxisByMinNTiles isMovingInYAxisByMinNTiles = new IsMovingInYAxisByMinNTiles(1);
    IsMovingInYAxisByMinNTiles isMovingInYAxisByMinNTilesHelper = new IsMovingInYAxisByMinNTiles(2);
    IsMovingInYAxisByMaxNTiles isMovingInYAxisByMaxNTilesHelper = new IsMovingInYAxisByMaxNTiles(2);
    private final IsRivalPieceOnDestination isRivalPieceOnDestination = new IsRivalPieceOnDestination();
    @Override
    public MoveType isValidMove(Tile origin, Tile destination, List<GameState> gameStates) {
        if(!checkInitialConditions(origin, destination, gameStates)) return MoveType.INVALID;
        List<Tile> possibleAttackTiles = getPossibleAttackTiles(destination, gameStates);
        if(!possibleAttackTiles.isEmpty()){
            if(arePossibleAttacks(origin ,destination, possibleAttackTiles, gameStates)){
                return MoveType.SPECIAL2;
            }
        }
        return MoveType.SPECIAL1;
    }
    private boolean checkInitialConditions(Tile origin, Tile destination, List<GameState> gameStates){
        if(isDiagonalMove.isValidMove(origin, destination, gameStates) == MoveType.INVALID)
            return false;
        if(isMovingInYAxisByMinNTiles.isValidMove(origin, destination, gameStates) == MoveType.INVALID)
            return false;
        if(isDestinationTileFree.isValidMove(origin, destination, gameStates) == MoveType.INVALID)
            return false;
        Tile tileThatShouldBeOccupied = getTileThatShouldBeOccupied(origin, destination);
        if(isDestinationTileFree.isValidMove(origin,tileThatShouldBeOccupied, gameStates) != MoveType.INVALID)
            return false;
        if(isDiagonalPathFreeToCross.isValidMove(origin, tileThatShouldBeOccupied, gameStates) == MoveType.INVALID)
            return false;
        if(isRivalPieceOnDestination.isValidMove(origin, tileThatShouldBeOccupied, gameStates) == MoveType.INVALID)
            return false;
        return true;
    }
    private Tile getTileThatShouldBeOccupied(Tile origin, Tile destination) {
        if (arithmethicOperation.getXMovement(origin, destination) > 0) {
            if(arithmethicOperation.getYMovement(origin, destination) > 0)
                return new Tile(destination.getX() - 1, destination.getY() - 1);
        } else if (arithmethicOperation.getXMovement(origin, destination) > 0) {
            if(arithmethicOperation.getYMovement(origin, destination) < 0)
                return new Tile(destination.getX() - 1, destination.getY() + 1);
        } else if (arithmethicOperation.getXMovement(origin, destination) < 0) {
            if(arithmethicOperation.getYMovement(origin, destination) > 0)
                return new Tile(destination.getX() + 1, destination.getY() - 1);
        }
        return new Tile(destination.getX() + 1, destination.getY() + 1);
    }
    private List<Tile> getPossibleAttackTiles(Tile destination, List<GameState> gameStates){
        List<Tile> possibleAttackTiles = new ArrayList<>();
        int x = destination.getX();
        int y = destination.getY();
        if(getPossibleAttacksFirstCheck(x,y))
            possibleAttackTiles.add(new Tile(x-2, y-2));
        if(getPossibleAttacksSecondCheck(x,y, gameStates.get(0).getBoard().getLength()))
            possibleAttackTiles.add(new Tile(x-2, y+2));
        if(getPossibleAttacksThirdCheck(x,y, gameStates.get(0).getBoard().getWidth()))
            possibleAttackTiles.add(new Tile(x+2, y-2));
        if(getPossibleAttacksFourthCheck(x,y, gameStates.get(0).getBoard().getWidth(), gameStates.get(0).getBoard().getLength()))
            possibleAttackTiles.add(new Tile(x+2, y+2));
        return possibleAttackTiles;
    }
    private boolean getPossibleAttacksFirstCheck(int x, int y){
        if(x-2 >= 0){
            if(y-2 >=0)
                return true;
        }
        return false;
    }
    private boolean getPossibleAttacksSecondCheck(int x, int y, int length){
        if(x-2 >= 0){
            if(y+2 < length)
                return true;
        }
        return false;
    }
    private boolean getPossibleAttacksThirdCheck(int x, int y, int width){
        if(x+2 < width){
            if(y-2 >= 0)
                return true;
        }
        return false;
    }
    private boolean getPossibleAttacksFourthCheck(int x, int y, int width, int length){
        if(x+2 < width){
            if(y+2 < length)
                return true;
        }
        return false;
    }
    private boolean arePossibleAttacks(Tile realOrigin,Tile origin, List<Tile> possibleDestinations, List<GameState> gameStates){
        for(Tile possibleDestination : possibleDestinations){
            if(arePossibleAttacksHelper(realOrigin, origin, possibleDestination, gameStates) != MoveType.INVALID){
                return true;
            }
        }
        return false;
    }
    private MoveType arePossibleAttacksHelper(Tile realOrigin,Tile origin, Tile destination, List<GameState> gameStates){
        if (isMovingInYAxisByMaxNTilesHelper.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isMovingInYAxisByMinNTilesHelper.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isDiagonalMove.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isDestinationTileFree.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if(isRivalPieceOnDestination.isValidMove(realOrigin, getTileThatShouldBeOccupied(origin,destination),gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isDiagonalPathFreeToCross.isValidMove(origin, destination,gameStates) != MoveType.INVALID)
            return MoveType.INVALID;
        return MoveType.SPECIAL1;
    }
}
