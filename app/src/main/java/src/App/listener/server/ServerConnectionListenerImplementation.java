package src.App.listener.server;

import edu.austral.ingsis.clientserver.ServerConnectionListener;
import org.jetbrains.annotations.NotNull;
import src.App.service.ServerService;

public class ServerConnectionListenerImplementation implements ServerConnectionListener {

    private ServerService serverService;
    public ServerConnectionListenerImplementation(ServerService serverService){
        this.serverService = serverService;
    }
    @Override
    public void handleClientConnection(@NotNull String s) {
        serverService.sendActualState(s);
    }

    @Override
    public void handleClientConnectionClosed(@NotNull String s) {

    }
}
