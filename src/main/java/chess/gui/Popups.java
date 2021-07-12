package chess.gui;

import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;


/**
 * Creates all the Popups for the Gui
 */
public class Popups extends MainController {
    Gui gui;


    /**
     * Pops up the popup for check
     */
    @FXML
    void popupCheck() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "82")));
        if (gui.getSettings().getBoard().isBlackIsTurn()) {
            alert.setContentText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "80")));
        } else {
            alert.setContentText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "81")));
        }

        alert.show();
    }

    /**
     * Pops up the popup for check Mate
     */
    @FXML
    void popupCheckMate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "92")));
        if (gui.getSettings().getBoard().isBlackIsTurn()) {
            alert.setContentText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "90")));
        } else {
            alert.setContentText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "91")));

        }
        alert.show();
    }

    /**
     * Pops up the popup for move not allowed
     */
    @FXML
    void popupMoveNotAllowed() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "70")));
        alert.setContentText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "71")));
        alert.show();
    }

    /**
     * Pops up the popup for double click not allowed
     */
    @FXML
    void popupDoubleClick(String firstMinionClickedBlack, String firstMinionClickedWhite) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "83")));
        if (gui.getSettings().getBoard().isBlackIsTurn()) {
            alert.setContentText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "84")) + firstMinionClickedBlack);
        } else {
            alert.setContentText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getLanguageNumber() + "84")) + firstMinionClickedWhite);
        }
        alert.show();
    }

    @Override
    public void setGui(Gui gui) {
        this.gui = gui;

    }


}
