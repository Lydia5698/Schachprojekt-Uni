package chess.gui;

import chess.model.AI;

public class MainController {
    private String promoteTo = "Q";
    private boolean checkIsVisible = false;
    private boolean rotation = false;
    public AI ai; // = new AI(true); // black ai

    public AI getAi() {
        return ai;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    public boolean isRotation() {
        return rotation;
    }

    public void setRotation(boolean rotation) {
        this.rotation = rotation;
    }


    public boolean isCheckIsVisible() {
        return checkIsVisible;
    }

    public void setCheckIsVisible(boolean checkIsVisible) {
        this.checkIsVisible = checkIsVisible;
    }

    public String getPromoteTo() {
        return promoteTo;
    }

    public void setPromoteTo(String promoteTo) {
        this.promoteTo = promoteTo;
    }

    public Gui getGui() {
        return gui;
    }
    public void setGui(Gui gui){
        this.gui = gui;
    }



}
