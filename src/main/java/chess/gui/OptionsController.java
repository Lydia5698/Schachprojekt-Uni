package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OptionsController extends MainController {

    @FXML
    private CheckBox lightPossibleMoves;

    @FXML
    private Button btnStartScreen;

    @FXML
    private CheckBox checkVisible;

    @FXML
    private CheckBox rotateBoard;

    @FXML
    private Button btnChessBoard;

    @FXML
    void KingCheck(ActionEvent event) {

    }

    @FXML
    void doubleClick(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void possibleMoves(ActionEvent event) {

    }

    @FXML
    void rotateBoard(ActionEvent event) {

    }

    @FXML
    void showStartScreen(MouseEvent event) {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        getGui().show_FXML("startScreen.fxml", stage); //TODO unterscheidung wenn spiel gestartet zur Gui weiterleiten
    }

}

