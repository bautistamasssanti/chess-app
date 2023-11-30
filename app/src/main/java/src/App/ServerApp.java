package src.App;

import src.App.service.ServerService;
import src.engine.GameEngineFactory;

public class ServerApp {

    public static void main(String[] args){
        ServerService serverService = new ServerService(8090, new GameEngineFactory().capablancaChessGameEngine());
        serverService.init();
    }
}

