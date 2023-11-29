package src.chess.moveRules;

import src.logic.moveRules.ComposedMoveRule;
import src.logic.moveRules.MoveRule;
import src.logic.moveRules.basicRules.state.IsPieceWithoutPreviousMovement;
import src.logic.moveRules.basicRules.direction.IsMovingForward;
import src.logic.moveRules.basicRules.length.IsMovingInXAxisByMaxNTiles;
import src.logic.moveRules.basicRules.length.IsMovingInYAxisByMaxNTiles;
import src.logic.moveRules.basicRules.length.IsMovingInYAxisByMinNTiles;
import src.logic.moveRules.basicRules.path.*;
import src.logic.moveRules.basicRules.shape.IsDiagonalMove;
import src.logic.moveRules.basicRules.shape.IsLShapedMove;
import src.logic.moveRules.basicRules.shape.IsStraightMove;
import src.chess.moveRules.specialRules.CastledKingsideRule;
import src.chess.moveRules.specialRules.CastledQueensideRule;

public class ChessRuleFactory {
    public MoveRule RookStraightMoveRule(){
        IsDestinationFreeOfTeamPiece isDestinationFreeOfTeamPiece = new IsDestinationFreeOfTeamPiece();
        IsStraightPathFreeToCross isStraightPathFreeToCross = new IsStraightPathFreeToCross();
        IsStraightMove isStraightMove = new IsStraightMove();
        MoveRule[] moveRules = {isDestinationFreeOfTeamPiece, isStraightMove, isStraightPathFreeToCross};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule BishopDiagonalMoveRule(){
        IsDiagonalMove isDiagonalMove = new IsDiagonalMove();
        IsDiagonalPathFreeToCross isDiagonalPathFreeToCross = new IsDiagonalPathFreeToCross();
        IsDestinationFreeOfTeamPiece isDestinationFreeOfTeamPiece = new IsDestinationFreeOfTeamPiece();
        MoveRule[] moveRules = {isDiagonalMove, isDiagonalPathFreeToCross, isDestinationFreeOfTeamPiece};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule QueenStraightMoveRule(){
        IsDestinationFreeOfTeamPiece isDestinationFreeOfTeamPiece = new IsDestinationFreeOfTeamPiece();
        IsStraightPathFreeToCross isStraightPathFreeToCross = new IsStraightPathFreeToCross();
        IsStraightMove isStraightMove = new IsStraightMove();
        MoveRule[] moveRules = {isDestinationFreeOfTeamPiece, isStraightMove, isStraightPathFreeToCross};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule QueenDiagonalMoveRule(){
        IsDiagonalMove isDiagonalMove = new IsDiagonalMove();
        IsDiagonalPathFreeToCross isDiagonalPathFreeToCross = new IsDiagonalPathFreeToCross();
        IsDestinationFreeOfTeamPiece isDestinationFreeOfTeamPiece = new IsDestinationFreeOfTeamPiece();
        MoveRule[] moveRules = {isDiagonalMove, isDiagonalPathFreeToCross, isDestinationFreeOfTeamPiece};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule KnightMoveRule(){
        IsLShapedMove isLShapedMove = new IsLShapedMove(2,1);
        IsDestinationFreeOfTeamPiece isDestinationFreeOfTeamPiece = new IsDestinationFreeOfTeamPiece();
        MoveRule[] moveRules = {isLShapedMove, isDestinationFreeOfTeamPiece};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule KingMoveRule(){
        IsMovingInYAxisByMaxNTiles isMovingInYAxisByMaxNTiles = new IsMovingInYAxisByMaxNTiles(1);
        IsMovingInXAxisByMaxNTiles isMovingInXAxisByMaxNTiles = new IsMovingInXAxisByMaxNTiles(1);
        IsDestinationFreeOfTeamPiece isDestinationFreeOfTeamPiece = new IsDestinationFreeOfTeamPiece();
        MoveRule[] moveRules = {isMovingInYAxisByMaxNTiles, isMovingInXAxisByMaxNTiles, isDestinationFreeOfTeamPiece};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule CastledKingSideRule(){
        return new CastledKingsideRule();
    }
    public MoveRule CastledQueenSideRule(){
        return new CastledQueensideRule();
    }
    public MoveRule PawnMoveRule(){
        IsDestinationTileFree isDestinationTileFree = new IsDestinationTileFree();
        IsMovingForward isMovingForward = new IsMovingForward();
        IsMovingInYAxisByMaxNTiles isMovingInYAxisByMaxNTiles = new IsMovingInYAxisByMaxNTiles(1);
        IsMovingInXAxisByMaxNTiles isMovingInXAxisByMaxNTiles = new IsMovingInXAxisByMaxNTiles(0);
        MoveRule[] moveRules = {isDestinationTileFree, isMovingForward, isMovingInYAxisByMaxNTiles, isMovingInXAxisByMaxNTiles};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule DoubleMovePawnMoveRule(){
        IsDestinationTileFree isDestinationTileFree = new IsDestinationTileFree();
        IsStraightPathFreeToCross isStraightPathFreeToCross = new IsStraightPathFreeToCross();
        IsMovingForward isMovingForward = new IsMovingForward();
        IsMovingInYAxisByMaxNTiles isMovingInYAxisByMaxNTiles = new IsMovingInYAxisByMaxNTiles(2);
        IsMovingInXAxisByMaxNTiles isMovingInXAxisByMaxNTiles = new IsMovingInXAxisByMaxNTiles(0);
        IsPieceWithoutPreviousMovement isPieceWithoutPreviousMovement = new IsPieceWithoutPreviousMovement();
        MoveRule[] moveRules = {isDestinationTileFree, isMovingForward, isMovingInYAxisByMaxNTiles, isMovingInXAxisByMaxNTiles, isPieceWithoutPreviousMovement, isStraightPathFreeToCross};
        return new ComposedMoveRule(moveRules);
    }
    public MoveRule DiagonalPawnMoveRule(){
        IsRivalPieceOnDestination isRivalPieceOnDestination = new IsRivalPieceOnDestination();
        IsMovingForward isMovingForward = new IsMovingForward();
        IsMovingInYAxisByMaxNTiles isMovingInYAxisByMaxNTiles = new IsMovingInYAxisByMaxNTiles(1);
        IsMovingInYAxisByMinNTiles isMovingInYAxisByMinNTiles = new IsMovingInYAxisByMinNTiles(1);
        IsDiagonalMove isDiagonalMove = new IsDiagonalMove();
        MoveRule[] moveRules = {isRivalPieceOnDestination, isMovingForward, isMovingInYAxisByMaxNTiles, isMovingInYAxisByMinNTiles, isDiagonalMove};
        return new ComposedMoveRule(moveRules);
    }
}
