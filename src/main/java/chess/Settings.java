package chess;

import chess.gui.ActiveGameController;
import chess.model.AI;
import chess.model.Board;


/**
 *  We are suppressing the PMD in this one, because we have 13 Fields this is 4 Fields above the Requirement.
 *  To avoid the too Many Fields PMD we have to create another class and this would be too confusing, because we
 *  already split the settings. And we would have to put all new settings in these settings so that we
 *  don't have too many fields in the other classes.
 *
 * The Settings for the Gui and the Cli
 */
@SuppressWarnings("PMD.TooManyFields")
public class Settings {
    //felder
    AI ai = new AI(false);
    Board board = new Board();
    protected boolean gui_active = false;
    protected SettingsNetwork settingsNetwork;
    protected SettingsLanguage settingsLanguage = new SettingsLanguage();
    protected boolean playerInCheck = false;
    protected boolean gameEnd = false;
    protected boolean ai_active = false;
    protected boolean ai_colour = false;
    protected boolean rotateBoard = false;
    protected boolean HighlightPossibleMoves = false;
    protected boolean checkVisible = false;
    protected boolean doubleClick = false;

    /**
     * Sets the setting in the Board
     */
    //constructor
    public Settings() {
        board.setSettings(this);
        setSettingsNetwork(new SettingsNetwork(board));
    }

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
        return HighlightPossibleMoves;
    }

    public void setHighlightPossibleMoves(boolean lightPossibleMoves) {
        this.HighlightPossibleMoves = lightPossibleMoves;
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

    public boolean isPlayerInCheck() {
        return playerInCheck;
    }

    public void setPlayerInCheck(boolean inCheck) {
        playerInCheck = inCheck;
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

    /**
     * sets the active Game Controller
     * @param activeGameController the current activeGameController
     */
    public void setActiveGameController(ActiveGameController activeGameController){
        this.settingsNetwork.setActiveGameController(activeGameController);
    }

    public void setSettingsLanguage(SettingsLanguage settingsLanguage) {
        this.settingsLanguage = settingsLanguage;
    }
}
