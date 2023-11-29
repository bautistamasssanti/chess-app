package src.logic.moveRules.basicRules;

import src.logic.Tile;

public class ArithmethicOperation {
    public int getXMovement(Tile origin, Tile destination){
        return destination.getX()-origin.getX();
    }
    public int getYMovement(Tile origin, Tile destination){
        return destination.getY()-origin.getY();
    }
    public int getAbsoluteValue(int value){
        return Math.abs(value);
    }
}
