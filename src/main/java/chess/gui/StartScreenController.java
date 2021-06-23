package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartScreenController extends MainController {

    @FXML
    private Button btnSpielstart;

    @FXML
    private Button btnOptions;

    @FXML
    private Button btnCredits;

    @FXML
    private Button btnAnleitung;

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void showAnleitung(MouseEvent event) {
        Stage stage = (Stage) btnAnleitung.getScene().getWindow();
        getGui().show_FXML("anleitung.fxml", stage);
    }

    @FXML
    void showCredits(MouseEvent event) {
        Stage stage = (Stage) btnCredits.getScene().getWindow();
        getGui().show_FXML("credits.fxml", stage);
    }

    @FXML
    void showOptions(MouseEvent event) {
        Stage stage = (Stage) btnOptions.getScene().getWindow();
        getGui().show_FXML("options.fxml", stage);
    }

    @FXML
    void showSpielauswahl(MouseEvent event) {
        Stage stage = (Stage) btnSpielstart.getScene().getWindow();
        getGui().show_FXML("spielauswahl.fxml", stage);
    }

}
