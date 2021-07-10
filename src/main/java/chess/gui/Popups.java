package chess.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Popups {
    ActiveGameController activeGameController;
    public Popups(ActiveGameController activeGameController){
        this.activeGameController = activeGameController;
    }

    @FXML
    void popupCheck() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber()+"82")));
        if(activeGameController.board.isBlackIsTurn()){
            alert.setContentText(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber()+"80")));
        }
        else {
            alert.setContentText(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber()+"81")));
        }

        alert.show();
    }

    @FXML
    void popupCheckMate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber()+"92")));
        if (activeGameController.board.isBlackIsTurn()) {
            alert.setContentText(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber()+"90")));
        } else {
            alert.setContentText(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber()+"91")));

        }
        alert.show();
    }

    @FXML
    void popupMoveNotAllowed() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber() + "70")));
        alert.setContentText(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber() + "71")));
        alert.show();
    }

    @FXML
    void popupDoubleClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber() + "83")));
        if(activeGameController.board.isBlackIsTurn()){
            alert.setContentText(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber() + "84")) + activeGameController.firstMinionClickedBlack);
        }
        else {
            alert.setContentText(activeGameController.getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(activeGameController.getGui().getSettings().getLanguageNumber() + "84")) + activeGameController.firstMinionClickedWhite);
        }
        alert.show();
    }




}
