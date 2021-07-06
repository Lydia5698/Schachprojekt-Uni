package chess.network;

import java.util.function.Consumer;

public class Netw_Cli extends Netw_Con {

    private String ip;
    private int port;
    public Netw_Cli(String ip, int port, Consumer<String> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port; }

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
