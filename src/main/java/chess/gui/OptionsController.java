package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OptionsController {

    private final Gui gui = new Gui();
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
    void exit(ActionEvent event) {

    }

    @FXML
    void showStartScreen(MouseEvent event) {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        gui.show_FXML("startScreen.fxml", stage); // wenn wir zur gui gehen müssen wir uns die Positionen von den Figuren merken

    }
    @FXML
    void KingCheck(ActionEvent event) {
      /*  if(checkVisible.isSelected()){
            FXMLController.checkIsVisible = true;
        }*/
    }

    @FXML
    void doubleClick(ActionEvent event) {

    }


    @FXML
    void possibleMoves(ActionEvent event) {

    }

    @FXML
    void rotateBoard(ActionEvent event) {

    }

}
