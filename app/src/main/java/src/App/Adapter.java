package src.App;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.ImageResolver;
import src.App.listener.client.GameEventListenerImplementation;
import src.App.service.ClientService;

public class Adapter {
    public static GameView createGameView(ImageResolver imageResolver, ClientService clientService){
        GameView gameView = new GameView(imageResolver);

        GameEventListener gameEventListener = new GameEventListenerImplementation(clientService);
        gameView.addListener(gameEventListener);

        return gameView;
    }
}
