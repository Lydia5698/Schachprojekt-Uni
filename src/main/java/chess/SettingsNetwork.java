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
 * <p>
 * The settingsNetwork is the settings class for the network
 *
 * @author Lydia Günther
 */
@SuppressWarnings("PMD.TooManyFields")
public class SettingsNetwork {
    /**
     * own color of the player
     */
    protected boolean black = false;

    /**
     * reference to the active board
     */
    protected Board board;

    /**
     * move of client
     */
    protected Move clientMove = new Move("A0-A0");

    /**
     * move of server
     */
    protected Move serverMove = new Move("A0-A0");

    /**
     * status if move was received
     */
    protected boolean moveReceived = false;

    /**
     * value of port to connect
     */
    private int port;

    /**
     * value of ip to connect
     */
    private String ip;

    /**
     * status if game plays as server
     */
    private final boolean server = false;

    /**
     * reference of own socket, can be NetwCli or NetwSvr
     */
    private NetwCon connection;

    /**
     * status if network-mode is activated
     */
    protected boolean network_active = false;

    /**
     * reference to active ActiveGameController to apply moves in GUI
     */
    private ActiveGameController activeGameController;

    /**
     * creates a SettingsNetwork instance and gets the current board
     *
     * @param board the current board
     */
    public SettingsNetwork(Board board) {
        this.board = board;
    }

    /**
     * creates the Network server
     *
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
     *
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

    /**
     * Setter for ip
     *
     * @param ip ip of server
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Setter for port
     *
     * @param port port of the connection
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * initialize the connection for the Network Game
     */
    public void initCon() {
        connection.startConnection();
    }

    /**
     * stops the connection for the Network Game
     *
     * @throws Exception in case the connection cant be stopped
     */
    public void stopCon() throws Exception {
        connection.closeConnection();
    }

    /**
     * Getter for connection
     *
     * @return reference for the game's connection
     */
    public NetwCon getConnection() {
        return connection;
    }

    /**
     * Setter for connection
     *
     * @param connection current connection
     */
    public void setConnection(NetwCon connection) {
        this.connection = connection;
    }

    /**
     * Getter for status black
     *
     * @return color of the player of the game
     */
    public boolean isBlack() {
        return black;
    }

    /**
     * Getter for status moveReceived
     *
     * @return move from client received
     */
    public boolean isMoveReceived() {
        return moveReceived;
    }  //zug wird empfangen und verarbeitet

    /**
     * Getter for clientMove
     *
     * @return move of the client
     */
    public Move getClientMove() {
        return clientMove;
    }

    /**
     * Getter for severMove
     *
     * @return move of the server
     */
    public Move getServerMove() {
        return serverMove;
    }

    /**
     * Getter for status isServer
     *
     * @return game plays as server
     */
    public boolean isServer() {
        return server;
    }

    /**
     * Setter for color of the player of the game
     *
     * @param black color of the player
     */
    public void setBlack(boolean black) {
        this.black = black;
    }

    /**
     * Getter for status network_active
     *
     * @return network-mode activated
     */
    public boolean isNetwork_active() {
        return network_active;
    }

    /**
     * Setter for status network_active
     *
     * @param network_active status for network-mode activated
     */
    public void setNetwork_active(boolean network_active) {
        this.network_active = network_active;
    }

    /**
     * Setter for the reference of the active ActiveGameController
     *
     * @param activeGameController current ActiveGameController
     */
    public void setActiveGameController(ActiveGameController activeGameController) {
        this.activeGameController = activeGameController;
    }
}
