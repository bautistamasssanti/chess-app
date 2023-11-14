package src.Chess.gameMode.WinConditions;

import src.Chess.factories.ChessGameStateFactory;
import src.Chess.gameMode.gameRules.IsPlayerNotInCheck;
import src.logic.Player;
import src.logic.Tile;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;
import src.logic.piece.PieceType;

import java.util.List;

public class Checkmate implements WinCondition {
    ChessGameStateFactory chessGameStateFactory = new ChessGameStateFactory();
    @Override
    public boolean isGameWonByPlayer(Player attackingPlayer, List<GameState> gameStates) {
        Player playerToReview = getOpponentTeam(attackingPlayer, gameStates);
        List<GameState> updatedGameStates = chessGameStateFactory.changeTurnColor(attackingPlayer.getColor(), gameStates);
        IsPlayerNotInCheck isPlayerNotInCheck = new IsPlayerNotInCheck(playerToReview);
        if(isPlayerNotInCheck.isGameRuleValid(updatedGameStates)){
            return false;
        }
        Tile kingTile = updatedGameStates.get(updatedGameStates.size() - 1).getBoard().getOccupiedTileFromPieceTypeAndColor(PieceType.KING, playerToReview.getColor()).get(0);
        if(canKingMoveToANonCheckedPosition(kingTile, playerToReview, attackingPlayer, updatedGameStates)){
            return false;
        }
        return !canTeamPieceUncheck(playerToReview, gameStates);
    }
    private Player getOpponentTeam(Player player, List<GameState> gameStates) {
        if (player.getColor() == gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor()) {
            return gameStates.get(gameStates.size() - 1).getTeamBPlayer();
        } else {
            return gameStates.get(gameStates.size() - 1).getTeamAPlayer();
        }
    }
    private boolean canKingMoveToANonCheckedPosition(Tile KingTile,Player playerToReview,Player attackingTeam,List<GameState> gameStates){
        List<Tile> attackingTiles= gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(attackingTeam.getColor());
        for (int i = 0; i < gameStates.get(gameStates.size() - 1).getBoard().getWidth(); i++) {
            for (int j = 0; j < gameStates.get(gameStates.size() - 1).getBoard().getLength(); j++) {
                if (gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(new Tile(i, j)) == null || gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(new Tile(i, j)).getColor() != playerToReview.getColor()) {
                    if(playerToReview.CanMovePiece(KingTile,new Tile(i,j),gameStates) != MoveType.INVALID){
                        int piecesThatCanAttack = 0;
                        for(Tile attackingTile : attackingTiles){
                            if(playerToReview.CanMovePiece(attackingTile,new Tile(i,j),gameStates) != MoveType.INVALID){
                                piecesThatCanAttack++;
                            }
                        }
                        if(piecesThatCanAttack == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    private boolean canTeamPieceUncheck(Player playerToReview,List<GameState> gameStates){
        IsPlayerNotInCheck isPlayerNotInCheck = new IsPlayerNotInCheck(playerToReview);
        List<Tile> teamTiles = gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(playerToReview.getColor());
        for (Tile teamTile : teamTiles) {
            if(gameStates.get(gameStates.size()-1).getBoard().getBoard().get(teamTile).getType() != PieceType.KING){
                for(int x = 0; x < gameStates.get(gameStates.size() - 1).getBoard().getWidth(); x++){
                    for(int y = 0; y < gameStates.get(gameStates.size() - 1).getBoard().getLength(); y++){
                        Tile destination = new Tile(x,y);
                        MoveType moveType= playerToReview.CanMovePiece(teamTile,destination,gameStates);
                        if(moveType != MoveType.INVALID){
                            List<GameState> updatedGameStates = chessGameStateFactory.movePiece(moveType,teamTile,destination,gameStates);
                            if(isPlayerNotInCheck.isGameRuleValid(updatedGameStates)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
