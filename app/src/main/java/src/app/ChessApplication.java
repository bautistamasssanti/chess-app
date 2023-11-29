package src.app;

import edu.austral.dissis.chess.gui.CachedImageResolver;
import edu.austral.dissis.chess.gui.DefaultImageResolver;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.ImageResolver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.app.listener.client.GameStateListenerImplementation;
import src.app.service.ClientService;

public class ChessApplication extends Application {
    private ImageResolver imageResolver = new CachedImageResolver(new DefaultImageResolver());
    private ClientService clientService = new ClientService(8090);
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess");
        GameView gameView = Adapter.createGameView(imageResolver, clientService);
        primaryStage.setScene(new Scene(gameView));
        primaryStage.show();

        clientService.addGameStateListener(new GameStateListenerImplementation(gameView));
        clientService.init_connection();
    }
}
