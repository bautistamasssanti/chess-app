package src.chessGames.commons;

import src.logic.Tile;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;

import java.util.List;

public interface GameStateFactory {
    List<GameState> movePiece(MoveType type, Tile originalTile, Tile targetTile, List<GameState> gameStates);
}
