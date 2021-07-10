package chess;

import chess.model.AI;
import chess.model.Board;
import chess.model.Move;
import chess.network.Netw_Cli;
import chess.network.Netw_Svr;
import javafx.application.Platform;

/**
 * The Settings for the Gui and the Cli
 */
public class Settings {
    //felder
    AI ai = new AI(false);
    Board board = new Board();
    Language language = new Language();
    protected boolean gui_active = false;
    protected boolean isInCheck = false;
    protected boolean gameEnd = false;
    protected boolean ai_active = false;
    protected boolean ai_colour = false;
    protected boolean rotateBoard = false;
    protected boolean lightPossibleMoves = false;
    protected boolean checkVisible = false;
    protected boolean doubleClick = false;
    protected boolean languageGerman = false;
    protected boolean languageEnglish = true;
    protected boolean black = false;
    protected Move clientMove = new Move("A0-A0");
    protected Move serverMove = new Move ("A0-A0");
    protected boolean moveReceived = false;
    private int port;
    private String ip;
    private boolean server = false;
    String languageNumber = "1";

    /**
     * Sets the setting in the Board
     */
    //constructor
    public Settings() { board.setSettings(this); }

    //methoden


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

    public Language getLanguage() {
        return language;
    }

    public boolean isLanguageGerman() {
        return languageGerman;
    }

    public void setLanguageGerman(boolean languageGerman) {
        this.languageGerman = languageGerman;
    }

    public boolean isLanguageEnglish() {
        return languageEnglish;
    }

    public void setLanguageEnglish(boolean languageEnglish) {
        this.languageEnglish = languageEnglish;
    }

    public String getLanguageNumber() {
        return languageNumber;
    }

    public void setLanguageNumber(String languageNumber) {
        this.languageNumber = languageNumber;
    }

    public boolean isGui_active() {
        return gui_active;
    }

    public void setGui_active(boolean gui_active) {
        this.gui_active = gui_active;
    }

    public boolean isInCheck() {
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


    /**
     * Changes the booleans for the Language and updates the Language number so the right Language is load from the Dictionary
     */
    public void changeLanguage(){
        if(isLanguageEnglish()){
            setLanguageEnglish(false);
            setLanguageGerman(true);
            setLanguageNumber("2");
        }
        else {
            setLanguageEnglish(true);
            setLanguageGerman(false);
            setLanguageNumber("1");
        }
    }

    private Netw_Svr createServer(){
        System.out.println("server starting");
        return new Netw_Svr(port, data->{
            Platform.runLater(()->{
                if (board.isBlackIsTurn() != black){
                    System.out.println(data);
                    this.clientMove = new Move(data);
                    moveReceived = true;
                    board.applyMove(clientMove);
                }
            });
        });
    }
    private Netw_Cli createClient(){
        return new Netw_Cli(ip, port, data->{
            Platform.runLater(()->{
                if (board.isBlackIsTurn() != black){
                    System.out.println(data);
                    this.clientMove = new Move(data);
                    moveReceived = true;
                    board.applyMove(clientMove);
                }
            });
        });
    }

    public boolean isMoveReceived() { return moveReceived; }
    public Move getClientMove() { return clientMove; }
    public Move getServerMove() { return serverMove; }
    public boolean isServer() { return server; }

}
