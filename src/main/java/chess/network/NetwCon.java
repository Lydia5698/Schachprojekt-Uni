package chess.network;

import java.io.*;
import java.net.*;
import java.util.function.Consumer;

/**
 * Network connection, can be server or client
 */
public abstract class NetwCon {
    /**
     * Thread for managing Socket
     */
    private final ConnectionThread thread_con = new ConnectionThread();
    private final Consumer<String> callBackWhenReceived;

    /**
     * Constructor for NetwCon
     */
    public NetwCon(Consumer<String> callBackWhenReceived) {
        this.callBackWhenReceived = callBackWhenReceived;
        thread_con.setDaemon(true);
    }

    /**
     * method to start connectionThread
     */
    public void startConnection() {
        thread_con.start();
    }

    /**
     * method to close connectionThread
     *
     * @throws Exception when connectionThread can't be closed
     */
    public void closeConnection() throws Exception {
        thread_con.socket.close();
    }

    /**
     * method to set package to send
     *
     * @param data data to send
     */
    public void send(String data) {
        thread_con.out.println(data);
    }

    /**
     * Getter for server
     *
     * @return status if NetwCon is NetwSvr or NetwCli
     */
    protected abstract boolean isServer();

    /**
     * Getter for ip
     *
     * @return value of ip to connect to
     */
    protected abstract String getIP();

    /**
     * Getter for port
     *
     * @return value of port to connect to if client or port to host on if server
     */
    protected abstract int getPort();

    /**
     * Inner class ConnectionThread to keep sockets and connection alive
     */
    private class ConnectionThread extends Thread {
        private Socket socket;
        private PrintWriter out;

        /**
         * run of ConnectionThread to keep connection alive and accept data
         */
        @Override
        public void run() {
            try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                 Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);
                while (true) {
                    String data = in.readLine();
                    callBackWhenReceived.accept(data);
                }
            } catch (Exception e) {
                callBackWhenReceived.accept("No Connection");
            }
        }
    }
}
