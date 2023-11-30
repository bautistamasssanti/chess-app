package src.App.listener.client;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import org.jetbrains.annotations.NotNull;
import src.App.service.ClientService;

public class GameEventListenerImplementation implements GameEventListener {

    private final ClientService clientService;

    public GameEventListenerImplementation(ClientService clientService){
        this.clientService = clientService;
    }

    @Override
    public void handleMove(@NotNull Move move) {
        clientService.sendMovement(move);
    }



}
