package chess.network;

import java.util.function.Consumer;

public class Netw_Svr extends Netw_Con {

    private int port;
    public Netw_Svr(int port, Consumer<String> callBackWhenReceived) {
        super(callBackWhenReceived);
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return true;
    }

    @Override
    protected String getIP() {
        return null;
    }

    @Override
    protected int getPort() {
        return port;
    }
}

