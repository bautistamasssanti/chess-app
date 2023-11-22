package src.Chess.gameMode.gameRules;

import src.Chess.factories.ChessGameStateFactory;
import src.logic.Player;
import src.logic.Tile;
import src.logic.gameMode.GameRule;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.piece.PieceType;

import java.util.List;

public class IsPlayerNotInCheck implements GameRule {
    private final ChessGameStateFactory chessGameStateFactory = new ChessGameStateFactory();
    private final Player playerToReview;

    public IsPlayerNotInCheck(Player playerToReview) {
        this.playerToReview = playerToReview;
    }

    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        Tile targetTile = gameStates.get(gameStates.size() - 1).getBoard().getOccupiedTileFromPieceTypeAndColor(PieceType.KING, playerToReview.getColor()).get(0);
        Player opponentPlayer = getOpponentPlayer(gameStates, playerToReview);
        List<GameState> auxiliarGameStates = chessGameStateFactory.changeTurnColor(opponentPlayer.getColor(), gameStates);
        List<Tile> attackingTiles = gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(opponentPlayer.getColor());
        for (Tile attackingTile : attackingTiles) {
            if (opponentPlayer.CanMovePiece(attackingTile, targetTile, auxiliarGameStates) != MoveType.INVALID) {
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
