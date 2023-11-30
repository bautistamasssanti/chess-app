package src.logic.board;

import src.logic.TeamColor;

public interface BoardFactory {
    Board newBoard(TeamColor color1, TeamColor color2);
}
