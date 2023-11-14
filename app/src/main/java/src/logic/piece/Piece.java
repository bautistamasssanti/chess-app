package src.logic.piece;

import src.logic.TeamColor;
import src.logic.moveRules.MoveRule;

public class Piece {
    private final int id;
    private final PieceType type;
    private final TeamColor color;
    private final MoveRule[] moveRules;

    public Piece(PieceType type, TeamColor color, MoveRule[] moveRules, int id) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.moveRules = moveRules;
    }

    public int getId() {
        return id;
    }

    public PieceType getType() {
        return type;
    }

    public TeamColor getColor() {
        return color;
    }

    public MoveRule[] getMoveRules() {
        return moveRules;
    }
}
