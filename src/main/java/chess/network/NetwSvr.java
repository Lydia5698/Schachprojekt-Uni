package chess.network;

import java.util.function.Consumer;

/**
 * Network server for hosting game
 */
public class NetwSvr extends NetwCon {

    /**
     * port to connect
     */
    private final int port;

    /**
     * Constructor of NetwSvr
     *
     * @param port value of port to connect
     */
    public NetwSvr(int port, Consumer<String> callBackWhenReceived) {
        super(callBackWhenReceived);
        this.port = port;
    }

    /**
     * Getter for status server
     *
     * @return true because instance of server
     */
    @Override
    protected boolean isServer() {
        return true;
    }

    /**
     * Getter for ip
     *
     * @return null - no ip needed to connect to
     */
    @Override
    protected String getIP() {
        return null;
    }

    /**
     * Getter for port
     *
     * @return value of port server hosts
     */
    @Override
    protected int getPort() {
        return port;
    }
}

