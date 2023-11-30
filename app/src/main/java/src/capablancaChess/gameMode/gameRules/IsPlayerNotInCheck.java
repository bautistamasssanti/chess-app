package src.capablancaChess.gameMode.gameRules;

import src.capablancaChess.gameState.CapablancaGameStateFactory;
import src.logic.Player;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameMode.GameRule;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.piece.PieceType;

import java.util.List;

public class IsPlayerNotInCheck implements GameRule {
    private final CapablancaGameStateFactory capablancaGameStateFactory = new CapablancaGameStateFactory();
    private final Player playerToReview;

    public IsPlayerNotInCheck(Player playerToReview) {
        this.playerToReview = playerToReview;
    }
    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        Tile targetTile = board.getOccupiedTileFromPieceTypeAndColor(PieceType.KING, playerToReview.getColor()).get(0);
        Player opponentPlayer = getOpponentPlayer(gameStates, playerToReview);
        List<GameState> auxiliarGameStates = capablancaGameStateFactory.changeTurnColor(opponentPlayer.getColor(), gameStates);
        List<Tile> attackingTiles = board.getTeamTiles(opponentPlayer.getColor());
        for (Tile attackingTile : attackingTiles) {
            if (opponentPlayer.canMovePiece(attackingTile, targetTile, auxiliarGameStates) != MoveType.INVALID) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getGameRuleName() {
        return "IsPlayerNotInCheck";
    }
    private Player getOpponentPlayer(List<GameState> gameStates, Player playerToReview){
        if (playerToReview.getColor() == gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor()) {
            return gameStates.get(gameStates.size() - 1).getTeamBPlayer();
        } else {
            return gameStates.get(gameStates.size() - 1).getTeamAPlayer();
        }
    }
}
