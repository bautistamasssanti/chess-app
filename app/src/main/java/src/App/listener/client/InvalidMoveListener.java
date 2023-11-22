package src.App.listener.client;

import edu.austral.dissis.chess.gui.InvalidMove;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;
import src.App.service.ClientService;

public class InvalidMoveListener implements MessageListener<InvalidMove> {

    private ClientService clientService;
    public InvalidMoveListener(ClientService clientService ){
        this.clientService = clientService;
    }
    @Override
    public void handleMessage(@NotNull Message<InvalidMove> message) {
        clientService.updateView(message.getPayload());
    }
}
