package chess;

import chess.gui.ActiveGameController;
import chess.model.Board;
import chess.model.Move;
import chess.network.NetwCli;
import chess.network.NetwCon;
import chess.network.NetwSvr;
import javafx.application.Platform;

/**
 * We are suppressing the PMD in this one, because we have 11 Fields this is only 2 Fields above the Requirement.
 * To avoid the too Many Fields PMD we have to create another class and this would be too confusing, because we
 * already split the settings.
 *
 * The settingsNetwork is the settings class for the network
 *
 *  @author Lydia Günther
 */
@SuppressWarnings("PMD.TooManyFields")
public class SettingsNetwork{
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
    private ActiveGameController activeGameController;

    /**
     * creates a SettingsNetwork instance and gets the current board
     *
     * @param board the current board
     */
    public SettingsNetwork(Board board){
        this.board = board;
    }

    /**
     * creates the Network server
     * @return Network Server
     */
    public NetwSvr createServer() {
        System.out.println("server starting");
        return new NetwSvr(port, data -> {
            Platform.runLater(() -> {
                if (board.isBlackIsTurn() != black) {
                    System.out.println(data);
                    this.clientMove = new Move(data);
                    moveReceived = true;
                    board.applyMove(clientMove);
                    activeGameController.history();
                    activeGameController.beatenMinionOutput();
                    activeGameController.updateBoard();
                    if (activeGameController.getGui().getSettings().isPlayerInCheck() && activeGameController.getGui().getSettings().isCheckVisible()) {
                        activeGameController.getPopups().popupCheck(activeGameController.getGui());
                        activeGameController.getGui().getSettings().setPlayerInCheck(false);
                    }
                }
            });
        });
    }

    /**
     * creates the Network client
     * @return Network client
     */
    public NetwCli createClient() {
        return new NetwCli(ip, port, data -> {
            Platform.runLater(() -> {
                if (board.isBlackIsTurn() != black) {
                    System.out.println(data);
                    this.clientMove = new Move(data);
                    moveReceived = true;
                    board.applyMove(clientMove);
                    activeGameController.history();
                    activeGameController.beatenMinionOutput();
                    activeGameController.updateBoard();
                    if (activeGameController.getGui().getSettings().isPlayerInCheck() && activeGameController.getGui().getSettings().isCheckVisible()) {
                        activeGameController.getPopups().popupCheck(activeGameController.getGui());
                        activeGameController.getGui().getSettings().setPlayerInCheck(false);
                    }
                }
            });
        });
    }

    public void setIp(String ip) { this.ip = ip; }
    public void setPort(int port) { this.port = port; }

    /**
     * initialize the connection for the Network Game
     * @throws Exception in case the connection fails
     */
    public void initCon () throws Exception{ connection.startConnection(); }

    /**
     * stops the connection for the Network Game
     * @throws Exception in case the connection cant be stopped
     */
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

    public void setActiveGameController(ActiveGameController activeGameController) {
        this.activeGameController = activeGameController;
    }
}
