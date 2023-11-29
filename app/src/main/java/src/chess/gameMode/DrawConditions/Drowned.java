package src.chess.gameMode.DrawConditions;

import src.chess.factories.ChessGameStateFactory;
import src.chess.gameMode.gameRules.IsPlayerNotInCheck;
import src.logic.Player;
import src.logic.Tile;
import src.logic.gameMode.DrawCondition;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;

import java.util.ArrayList;
import java.util.List;

public class Drowned implements DrawCondition {
    ChessGameStateFactory chessGameStateFactory = new ChessGameStateFactory();
    @Override
    public boolean isGameADraw(List<GameState> gameStates) {
        List<Player> players = getPlayersList(gameStates);
        for(Player player : players){
            System.out.println(player.getColor());
            List<GameState> updatedGameStates = updatedGameState(gameStates, player);
            IsPlayerNotInCheck isPlayerNotInCheck = new IsPlayerNotInCheck(player);
            if(isPlayerNotInCheck.isGameRuleValid(updatedGameStates)){
                if(!canKingMove(player, gameStates, isPlayerNotInCheck)){
                    if(!canPlayerMove(player, updatedGameStates, isPlayerNotInCheck)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private List<Player> getPlayersList(List<GameState> gameStates){
        List<Player> players = new ArrayList<>();
        players.add(gameStates.get(gameStates.size() - 1).getTeamAPlayer());
        players.add(gameStates.get(gameStates.size() - 1).getTeamBPlayer());
        return players;
    }
    private List<GameState> updatedGameState(List<GameState> gameStates, Player player){
        if(!gameStates.get(gameStates.size() - 1).getColorTurn().equals(player.getColor())){
            return gameStates;
        }
        else return chessGameStateFactory.changeTurnColor(getOppositePlayer(player, gameStates).getColor(), gameStates);
    }
    private Player getOppositePlayer(Player player, List<GameState> gameStates){
        if(player.getColor() == gameStates.get(gameStates.size() - 1).getTeamAPlayer().getColor()){
            return gameStates.get(gameStates.size() - 1).getTeamBPlayer();
        }
        else return gameStates.get(gameStates.size() - 1).getTeamAPlayer();
    }
    private boolean canKingMove(Player teamToReview, List<GameState> gameStates, IsPlayerNotInCheck isPlayerNotInCheck){
        Tile kingTile = gameStates.get(gameStates.size() - 1).getBoard().getOccupiedTileFromPieceTypeAndColor(src.logic.piece.PieceType.KING, teamToReview.getColor()).get(0);
        List<Tile> adjacentTiles = getAdjacentTiles(kingTile, gameStates);
        for(Tile adjacentTile : adjacentTiles){
            if(teamToReview.canMovePiece(kingTile, adjacentTile, gameStates) != src.logic.moveRules.MoveType.INVALID){
                List<GameState> updatedGameState = chessGameStateFactory.movePiece(teamToReview.canMovePiece(kingTile, adjacentTile, gameStates), kingTile, adjacentTile, gameStates);
                updatedGameState = updatedGameState(updatedGameState, teamToReview);
                if(isPlayerNotInCheck.isGameRuleValid(updatedGameState)){
                    return true;
                }
            }
        }
        return false;
    }
    private List<Tile> getAdjacentTiles(Tile tile, List<GameState> gameStates){
        List<Tile> adjacentTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        int width = gameStates.get(gameStates.size() - 1).getBoard().getWidth();
        int length = gameStates.get(gameStates.size() - 1).getBoard().getLength();
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                Tile adjacentTile = new Tile(x + i, y + j);
                if(x + i >= 0 && x + i < width && y + j >= 0 && y + j < length && !adjacentTile.equals(tile) && gameStates.get(gameStates.size() - 1).getBoard().getBoard().get(adjacentTile) == null){
                    adjacentTiles.add(adjacentTile);
                }
            }
        }
        return adjacentTiles;
    }
    private boolean canPlayerMove(Player player, List<GameState> gameStates, IsPlayerNotInCheck isPlayerNotInCheck){
        List<Tile> teamTiles = gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(player.getColor());
        int length = gameStates.get(gameStates.size() - 1).getBoard().getLength();
        int width = gameStates.get(gameStates.size() - 1).getBoard().getWidth();
        List<GameState> updatedGameStates = chessGameStateFactory.changeTurnColor(player.getColor(), gameStates);
        for(Tile teamTile : teamTiles){
            for(int i = 0; i < length; i++){
                for(int j = 0; j < width; j++){
                    Tile targetTile = new Tile(i, j);
                    MoveType moveType = player.canMovePiece(teamTile, targetTile, updatedGameStates);
                    if(moveType != src.logic.moveRules.MoveType.INVALID){
                        updatedGameStates = chessGameStateFactory.movePiece(moveType, teamTile, targetTile, gameStates);
                        updatedGameStates = updatedGameState(updatedGameStates, player);
                        if (isPlayerNotInCheck.isGameRuleValid(updatedGameStates)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
