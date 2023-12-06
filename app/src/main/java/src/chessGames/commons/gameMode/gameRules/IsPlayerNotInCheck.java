package src.chessGames.commons.gameMode.gameRules;

import src.logic.Player;
import src.logic.TeamColor;
import src.logic.Tile;
import src.logic.board.Board;
import src.logic.gameMode.GameRule;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.piece.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IsPlayerNotInCheck implements GameRule {
    private final Player playerToReview;

    public IsPlayerNotInCheck(Player playerToReview) {
        this.playerToReview = playerToReview;
    }

    @Override
    public boolean isGameRuleValid(List<GameState> gameStates) {
        Board board = gameStates.get(gameStates.size() - 1).getBoard();
        Tile targetTile = board.getOccupiedTilesFromPieceTypeAndColor(PieceType.KING, playerToReview.getColor()).get(0);
        Player opponentPlayer = getOpponentPlayer(gameStates, playerToReview);
        List<GameState> auxiliarGameStates = changeTurnColor(opponentPlayer.getColor(), gameStates);
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
    private List<GameState> changeTurnColor(TeamColor colorTurn, List<GameState> gameStates){
        List<GameState> newHistory = new ArrayList<>(gameStates.subList(0, gameStates.size() - 1)) ;
        GameState newGameState = new GameState(gameStates.get(gameStates.size() - 1).getBoard(), gameStates.get(gameStates.size() - 1).getGameStatus(), gameStates.get(gameStates.size() - 1).getTeamAPlayer(), gameStates.get(gameStates.size() - 1).getTeamBPlayer(), colorTurn);
        newHistory.add(newHistory.size(), newGameState);
        return Collections.unmodifiableList(newHistory);
    }
}
