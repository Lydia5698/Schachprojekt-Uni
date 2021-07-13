package chess.network;

import java.util.function.Consumer;

public class NetwSvr extends NetwCon {

    private final int port;

    public NetwSvr(int port, Consumer<String> callBackWhenReceived) {
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

