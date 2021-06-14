package chess.gui;

import chess.gui.Gui;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FXMLController {

    private final Gui gui; //conbtroller f. game

    @FXML
    private void b_neuesSpiel(){

    }
    public FXMLController(Gui gui){
        this.gui = gui;
    }

    public void showStartScreen(){
        gui.show_FXML("startScreen.fxml", gui.stage);
    }
    public void showAnleitung(){
        gui.show_FXML("anleitung.fxml", gui.stage);
    }
    public void showCredits(){
        gui.show_FXML("credits.fxml", gui.stage);

    }
    public void showGui(){
        gui.show_FXML("gui.fxml", gui.stage);
    }
    public void showOptions(){
        gui.show_FXML("options.fxml", gui.stage);
    }
    public void showSpielauswahl(){
        gui.show_FXML("spielauswahl.fxml", gui.stage);
    }

    public void exit(){
        System.exit(0);

    }
}
