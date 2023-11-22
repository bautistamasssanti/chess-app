package src.App;

import src.App.service.ServerService;
import src.Chess.ChessGameEngine;

public class ServerApp {

    public static void main(String[] args){
        ServerService serverService = new ServerService(8090, new ChessGameEngine());
        serverService.init();
    }
}

