package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreditsController extends MainController {

    @FXML
    private Button btnStartScreen;

    @FXML
    void showStartScreen(MouseEvent event) {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        show_FXML("startScreen.fxml", stage, getGui());

    }

}
