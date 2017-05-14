package thedhakadigital.digibulk.model;

/**
 * Created by y34h1a on 3/11/17.
 */

public class ServerResponseEvent {
    private ServerResponse serverResponse;

    public ServerResponseEvent(ServerResponse ServerResponse) {
        this.serverResponse = ServerResponse;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerResponse ServerResponse) {
        this.serverResponse = ServerResponse;
    }
}
