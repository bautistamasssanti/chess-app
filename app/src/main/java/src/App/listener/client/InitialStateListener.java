package src.App.listener.client;

import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;
import src.App.service.ClientService;

public class InitialStateListener implements MessageListener<InitialState> {
    private ClientService clientService;
    public InitialStateListener(ClientService clientService){
        this.clientService = clientService;
        }
        @Override
        public void handleMessage(@NotNull Message<InitialState> message) {
        clientService.handleInitialState(message.getPayload());
        }
}
