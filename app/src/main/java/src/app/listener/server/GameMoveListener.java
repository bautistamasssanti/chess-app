package src.app.listener.server;

import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;
import src.app.service.ServerService;

public class GameMoveListener implements MessageListener<Move> {

    private ServerService serverService;
    public GameMoveListener(ServerService serverService){
        this.serverService = serverService;
    }

    @Override
    public void handleMessage(@NotNull Message<Move> message) {
        serverService.verifyMovement(message.getPayload());
    }
}
