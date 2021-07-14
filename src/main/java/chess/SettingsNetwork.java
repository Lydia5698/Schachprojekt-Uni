package chess;

import chess.model.Board;
import chess.model.Move;
import chess.network.NetwCli;
import chess.network.NetwCon;
import chess.network.NetwSvr;
import javafx.application.Platform;

public class SettingsNetwork{
    public SettingsNetwork(Board board){
        this.board = board;
    }

    protected boolean black = false;
    protected Board board;
    protected Move clientMove = new Move("A0-A0");
    protected Move serverMove = new Move("A0-A0");
    protected boolean moveReceived = false;
    private int port;
    private String ip;
    private final boolean server = false;
    private NetwCon connection;
    protected boolean network_active = false;

    public NetwSvr createServer() {
        //System.out.println("server starting");
        return new NetwSvr(port, data -> {
            Platform.runLater(() -> {
                if (board.isBlackIsTurn() != black) {
                    //System.out.println(data);
                    this.clientMove = new Move(data);
                    moveReceived = true;
                    board.applyMove(clientMove);
                }
            });
        });
    }

    public NetwCli createClient() {
        return new NetwCli(ip, port, data -> {
            Platform.runLater(() -> {
                if (board.isBlackIsTurn() != black) {
                    //System.out.println(data);
                    this.clientMove = new Move(data);
                    moveReceived = true;
                    board.applyMove(clientMove);

                }
            });
        });
    }

    public void setIp(String ip) { this.ip = ip; }
    public void setPort(int port) { this.port = port; }
    public void initCon () throws Exception{ connection.startConnection(); }
    public void stopCon () throws Exception{ connection.closeConnection(); }
    public NetwCon getConnection() { return connection;    }
    public void setConnection(NetwCon connection) { this.connection = connection; }
    public boolean isBlack() { return black; }
    public boolean isMoveReceived() {
        return moveReceived;
    }  //zug wird empfangen und verarbeitet
    public Move getClientMove() {
        return clientMove;
    }
    public Move getServerMove() {
        return serverMove;
    }
    public boolean isServer() {
        return server;
    }
    public void setBlack(boolean black) {
        this.black = black;
    }
    public boolean isNetwork_active() {
        return network_active;
    }
    public void setNetwork_active(boolean network_active) {
        this.network_active = network_active;
    }


}