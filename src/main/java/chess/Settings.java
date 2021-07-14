package chess;

import chess.gui.ActiveGameController;
import chess.model.AI;
import chess.model.Board;


/**
 * The Settings for the Gui and the Cli
 */
public class Settings {
    //felder
    AI ai = new AI(false);
    Board board = new Board();
    //Language language = new Language();
    protected boolean gui_active = false;
    protected SettingsNetwork settingsNetwork;
    protected SettingsLanguage settingsLanguage = new SettingsLanguage();
    protected boolean isInCheck = false;
    protected boolean gameEnd = false;
    protected boolean ai_active = false;
    protected boolean ai_colour = false;
    protected boolean rotateBoard = false;
    protected boolean lightPossibleMoves = false;
    protected boolean checkVisible = false;
    protected boolean doubleClick = false;
/*    protected boolean languageGerman = false;
    protected boolean languageEnglish = true;
    protected boolean languageKlingon = false;
    protected boolean black = false;
    protected Move clientMove = new Move("A0-A0");
    protected Move serverMove = new Move("A0-A0");
    protected boolean moveReceived = false;
    private int port;
    private String ip;
    private final boolean server = false;
    private String languageNumber = "1";
    private NetwCon connection;
    private ActiveGameController activeGameController;
    String languageNumber = "1";*/
    /**
     * Sets the setting in the Board
     */
    //constructor
    public Settings() {
        board.setSettings(this);
        setSettingsNetwork(new SettingsNetwork(board));
    }

    //methoden


    public ActiveGameController getActiveGameController() {
        return activeGameController;
    }

    public void setActiveGameController(ActiveGameController activeGameController) {
        this.activeGameController = activeGameController;
    }

    public boolean isAi_active() {
        return ai_active;
    }

    public boolean isAi_colour() {
        return ai_colour;
    }

    public void setAi_active(boolean ai_active) {
        this.ai_active = ai_active;
    }

    public void setAi_colour(boolean ai_colour) {
        this.ai_colour = ai_colour;
    }

    public AI getAi() {
        return ai;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    public boolean isRotateBoard() {
        return rotateBoard;
    }

    public void setRotateBoard(boolean rotateBoard) {
        this.rotateBoard = rotateBoard;
    }

    public boolean isHighlightPossibleMoves() {
        return lightPossibleMoves;
    }

    public void setHighlightPossibleMoves(boolean lightPossibleMoves) {
        this.lightPossibleMoves = lightPossibleMoves;
    }

    public boolean isCheckVisible() {
        return checkVisible;
    }

    public void setCheckVisible(boolean checkVisible) {
        this.checkVisible = checkVisible;
    }

    public boolean isDoubleClick() {
        return doubleClick;
    }

    public void setDoubleClick(boolean doubleClick) {
        this.doubleClick = doubleClick;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }



    public boolean isGui_active() {
        return gui_active;
    }

    public void setGui_active(boolean gui_active) {
        this.gui_active = gui_active;
    }

    public boolean getIsInCheck() {
        return isInCheck;
    }

    public void setInCheck(boolean inCheck) {
        isInCheck = inCheck;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }


    public SettingsNetwork getSettingsNetwork() {
        return settingsNetwork;
    }
    public void setSettingsNetwork(SettingsNetwork settingsNetwork) {
        this.settingsNetwork = settingsNetwork;
    }
    public SettingsLanguage getSettingsLanguage() {
        return settingsLanguage;
    }

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
                    if (activeGameController.getGui().getSettings().getIsInCheck() && activeGameController.getGui().getSettings().isCheckVisible()) {
                        activeGameController.getPopups().popupCheck(activeGameController.getGui());
                        activeGameController.getGui().getSettings().setInCheck(false);
                    }
                }
            });
        });
    }

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
                    if (activeGameController.getGui().getSettings().getIsInCheck() && activeGameController.getGui().getSettings().isCheckVisible()) {
                        activeGameController.getPopups().popupCheck(activeGameController.getGui());
                        activeGameController.getGui().getSettings().setInCheck(false);
                    }
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

    public void setSettingsLanguage(SettingsLanguage settingsLanguage) {
        this.settingsLanguage = settingsLanguage;
    }
}
