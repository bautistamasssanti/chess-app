package src.checkersGames.internationalCheckers.moveRules.specialRules;

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

public class ManEatingMove implements MoveRule {
    private final IsMovingInYAxisByMaxNTiles isMovingInYAxisByMaxNTiles = new IsMovingInYAxisByMaxNTiles(2);
    private final IsMovingInYAxisByMinNTiles isMovingInYAxisByMinNTiles = new IsMovingInYAxisByMinNTiles(2);
    private final IsDiagonalMove isDiagonalMove = new IsDiagonalMove();
    private final IsDestinationTileFree isDestinationTileFree = new IsDestinationTileFree();
    private final IsDiagonalPathFreeToCross isDiagonalPathFreeToCross = new IsDiagonalPathFreeToCross();
    private final IsRivalPieceOnDestination isRivalPieceOnDestination = new IsRivalPieceOnDestination();
    private final ArithmethicOperation arithmethicOperation = new ArithmethicOperation();
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
        if(isRivalPieceOnDestination.isValidMove(origin, getTileThatShouldBeOccupied(origin,destination),gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isDiagonalPathFreeToCross.isValidMove(origin, destination,gameStates) != MoveType.INVALID)
            return MoveType.INVALID;
        List<Tile> possibleAttackTiles = getPossibleAttackTiles(destination, gameStates);
        if(!possibleAttackTiles.isEmpty()){
            if(arePossibleAttacks(origin,destination, possibleAttackTiles, gameStates)){
                return MoveType.SPECIAL2;
            }
        }
        return MoveType.SPECIAL1;
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
    private boolean arePossibleAttacks(Tile realOrigin,Tile origin, List<Tile> possibleDestinations, List<GameState> gameStates){
        for(Tile possibleDestination : possibleDestinations){
            if(arePossibleAttacksHelper(realOrigin,origin,possibleDestination,gameStates) != MoveType.INVALID){
                return true;
            }
        }
        return false;
    }
    private MoveType arePossibleAttacksHelper(Tile realOrigin,Tile origin, Tile destination, List<GameState> gameStates){
        if (isMovingInYAxisByMaxNTiles.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
            return MoveType.INVALID;
        if (isMovingInYAxisByMinNTiles.isValidMove(origin, destination,gameStates) == MoveType.INVALID)
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
    private Tile getTileThatShouldBeOccupied(Tile origin, Tile destination) {
        if (arithmethicOperation.getXMovement(origin, destination) > 0 && arithmethicOperation.getYMovement(origin, destination) > 0) {
            return new Tile(destination.getX() - 1, destination.getY() - 1);
        } else if (arithmethicOperation.getXMovement(origin, destination) > 0 && arithmethicOperation.getYMovement(origin, destination) < 0) {
            return new Tile(destination.getX() - 1, destination.getY() + 1);
        } else if (arithmethicOperation.getXMovement(origin, destination) < 0 && arithmethicOperation.getYMovement(origin, destination) > 0) {
            return new Tile(destination.getX() + 1, destination.getY() - 1);
        } else return new Tile(destination.getX() + 1, destination.getY() + 1);
    }
}
