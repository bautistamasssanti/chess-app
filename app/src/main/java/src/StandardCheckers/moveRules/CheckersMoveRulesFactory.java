package src.StandardCheckers.moveRules;

import src.StandardCheckers.moveRules.specialRules.ManEatingMove;
import src.StandardCheckers.moveRules.specialRules.QueenEatingMove;
import src.logic.moveRules.ComposedMoveRule;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.direction.IsMovingForward;
import src.logic.moveRules.basicRules.length.IsMovingInYAxisByMaxNTiles;
import src.logic.moveRules.basicRules.length.IsMovingInYAxisByMinNTiles;
import src.logic.moveRules.basicRules.path.IsDestinationTileFree;
import src.logic.moveRules.basicRules.path.IsDiagonalPathFreeToCross;
import src.logic.moveRules.basicRules.shape.IsDiagonalMove;

public class CheckersMoveRulesFactory {
    public MoveRule manStandardMoveRule(){
        IsMovingForward isMovingForward = new IsMovingForward();
        IsMovingInYAxisByMaxNTiles isMovingInYAxisByMaxNTiles = new IsMovingInYAxisByMaxNTiles(1);
        IsMovingInYAxisByMinNTiles isMovingInYAxisByMinNTiles = new IsMovingInYAxisByMinNTiles(1);
        IsDiagonalMove isDiagonalMove = new IsDiagonalMove();
        IsDestinationTileFree isDestinationTileFree = new IsDestinationTileFree();
        MoveRule[] moveRules = {isMovingForward, isMovingInYAxisByMaxNTiles, isMovingInYAxisByMinNTiles, isDiagonalMove, isDestinationTileFree};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule manJump(){
        return new ManEatingMove();
    }
    public MoveRule queenStandardMoveRule(){
        IsDiagonalMove isDiagonalMove = new IsDiagonalMove();
        IsDiagonalPathFreeToCross isDiagonalPathFreeToCross = new IsDiagonalPathFreeToCross();
        IsDestinationTileFree isDestinationTileFree = new IsDestinationTileFree();
        IsMovingInYAxisByMinNTiles isMovingInYAxisByMinNTiles = new IsMovingInYAxisByMinNTiles(1);
        MoveRule[] moveRules = {isDiagonalMove, isDiagonalPathFreeToCross, isDestinationTileFree, isMovingInYAxisByMinNTiles};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule queenJump(){
        return new QueenEatingMove();
    }
}
