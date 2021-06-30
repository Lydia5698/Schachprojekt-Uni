package chess;

import chess.model.AI;

public class Settings {
    //felder
    AI ai = new AI(false);
    protected boolean ai_active = false;
    protected boolean ai_colour = false;
    protected boolean rotateBoard = false;
    protected boolean lightPossibleMoves = false;
    protected boolean checkVisible = false;
    protected boolean doubleClick = false;
    // TODO in settings die options von der Gui einfügen
    //gegen gegner
    //schach anzeigen lassen
    //mehrfach klicken
    //spielfeld mitdrehen


    //constructor
    public Settings() {
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

    public boolean isLightPossibleMoves() {
        return lightPossibleMoves;
    }

    public void setLightPossibleMoves(boolean lightPossibleMoves) {
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


}
