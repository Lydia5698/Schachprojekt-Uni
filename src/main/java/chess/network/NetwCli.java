package chess.network;

import java.util.function.Consumer;

/**
 * network client for joining game
 */
public class NetwCli extends NetwCon {

    /**
     * ip to connect
     */
    private final String ip;

    /**
     * port to connect
     */
    private final int port;

    /**
     * Constructor of NetwCli
     *
     * @param ip   value of ip to connect
     * @param port value of port to connect
     */
    public NetwCli(String ip, int port, Consumer<String> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    /**
     * Getter for status server
     *
     * @return false because instance of client
     */
    @Override
    protected boolean isServer() {
        return false;
    }

    /**
     * Getter for ip
     *
     * @return value of ip to connect
     */
    @Override
    protected String getIP() {
        return ip;
    }

    /**
     * Getter for port
     *
     * @return value of port to connect
     */
    @Override
    protected int getPort() {
        return port;
    }
}
