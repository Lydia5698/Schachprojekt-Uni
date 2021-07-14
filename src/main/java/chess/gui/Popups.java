package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;


/**
 * Creates all the Popups for the Gui
 */
public class Popups extends MainController {
    Gui gui;


    /**
     * Pops up the popup for check
     */
    @FXML
    public void popupCheck(Gui gui) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "82")));
        if (gui.getSettings().getBoard().isBlackIsTurn()) {
            alert.setContentText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "80")));
        } else {
            alert.setContentText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "81")));
        }
        getGui().settings.setIsInCheck(false);
        alert.show();
    }

    /**
     * Pops up the popup for check Mate
     */
    @FXML
    void popupCheckMate(Gui gui) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "92")));
        if (gui.getSettings().getBoard().isBlackIsTurn()) {
            alert.setContentText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "90")));
        } else {
            alert.setContentText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "91")));

        }
        alert.show();
    }

    /**
     * Pops up the popup for move not allowed
     */
    @FXML
    void popupMoveNotAllowed(Gui gui) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "70")));
        alert.setContentText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "71")));
        alert.show();
    }

    /**
     * Pops up the popup for double click not allowed
     */
    @FXML
    void popupDoubleClick(String firstMinionClickedBlack, String firstMinionClickedWhite, Gui gui) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "83")));
        if (gui.getSettings().getBoard().isBlackIsTurn()) {
            alert.setContentText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "84")) + firstMinionClickedBlack);
        } else {
            alert.setContentText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(gui.getSettings().getSettingsLanguage().getLanguageNumber() + "84")) + firstMinionClickedWhite);
        }
        alert.show();
    }

    @Override
    public void setGui(Gui gui) {
        this.gui = gui;

    }


}
