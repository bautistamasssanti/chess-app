package src.Chess.gameMode.WinConditions;

import src.Chess.factories.ChessGameStateFactory;
import src.Chess.gameMode.gameRules.IsPlayerNotInCheck;
import src.logic.Player;
import src.logic.Tile;
import src.logic.gameMode.WinCondition;
import src.logic.gameState.GameState;
import src.logic.moveRules.MoveType;

import java.util.List;

public class HasAttackerCheckmatedOpponent implements WinCondition {
    ChessGameStateFactory chessGameStateFactory = new ChessGameStateFactory();
    @Override
    public boolean isGameWonByPlayer(Player attackingPlayer, List<GameState> gameStates) {
        Player defendingPlayer = getOpponent(attackingPlayer, gameStates);
        if(new IsPlayerNotInCheck(defendingPlayer).isGameRuleValid(gameStates)){
            return false;
        }
        return !CanDefenderUncheck(attackingPlayer, defendingPlayer, gameStates);

    }
    private Player getOpponent(Player player, List<GameState> gameStates){
        if(player.getColor() == gameStates.get(0).getTeamAPlayer().getColor())
            return gameStates.get(0).getTeamBPlayer();
        else
            return gameStates.get(0).getTeamAPlayer();
    }
    private boolean CanDefenderUncheck(Player attacker, Player defender, List<GameState> gameStates){
        List<Tile> defenderTiles= gameStates.get(gameStates.size() - 1).getBoard().getTeamTiles(defender.getColor());
        for(Tile defenderTile : defenderTiles){
            for(int x = 0; x < gameStates.get(gameStates.size() - 1).getBoard().getWidth(); x++){
                for(int y = 0; y < gameStates.get(gameStates.size() - 1).getBoard().getLength(); y++){
                    Tile targetTile = new Tile(x, y);
                    MoveType moveType = defender.CanMovePiece(defenderTile, targetTile, gameStates);
                    if(moveType != MoveType.INVALID){
                        List<GameState> auxiliarGameStates =chessGameStateFactory.movePiece(moveType,defenderTile, targetTile, gameStates);
                        System.out.println("checking if defender is unchecked with the move");
                        if(new IsPlayerNotInCheck(defender).isGameRuleValid(auxiliarGameStates)){
                            System.out.println("player can uncheck");
                            return true;
                        } else System.out.println("player can not uncheck");
                    }
                }
            }
        }
        System.out.println("false");
        return false;
    }


}
