package src.app.listener.client;

import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;
import src.app.service.ClientService;

public class NewGameStateListener implements MessageListener<NewGameState> {

    private ClientService clientService;
    public NewGameStateListener(ClientService clientService){
        this.clientService = clientService;
    }
    @Override
    public void handleMessage(@NotNull Message<NewGameState> message) {
        clientService.updateView(message.getPayload());
    }
}