package chess.network;

import java.util.function.Consumer;

public class NetwCli extends NetwCon {

    private final String ip;
    private final int port;

    public NetwCli(String ip, int port, Consumer<String> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }
}
