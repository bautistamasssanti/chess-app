package src.app;

import src.app.service.ServerService;
import src.chess.ChessGameEngine;

public class ServerApp {

    public static void main(String[] args){
        ServerService serverService = new ServerService(8090, new ChessGameEngine());
        serverService.init();
    }
}

