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
    private CheckBox doubleClick;

    @FXML
    private Button btnStartScreen;

    @FXML
    private Button btnChessBoard;

    @FXML
    private CheckBox checkVisible;

    @FXML
    private CheckBox rotateBoard;

    //TODO checkboxen checken wenn options geöffnet wird mit initialize?

    @FXML
    public void initialize(){
        //checkBoxes();
    }


    @FXML
    void KingCheck(ActionEvent event) {
        getGui().getSettings().setCheckVisible(checkVisible.isSelected());
    }

    @FXML
    void doubleClick(ActionEvent event) {
        getGui().getSettings().setDoubleClick(doubleClick.isSelected());
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void possibleMoves(ActionEvent event) {
        getGui().getSettings().setLightPossibleMoves(lightPossibleMoves.isSelected());
    }

    @FXML
    void rotateBoard(ActionEvent event) {
        getGui().getSettings().setRotateBoard(rotateBoard.isSelected());
    }

    @FXML
    void showStartScreen(MouseEvent event) {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        show_FXML("startScreen.fxml", stage, getGui());
        // set game false oder game end
    }

    @FXML
    void showChessBoard(MouseEvent event) {
        Stage stage = (Stage) btnChessBoard.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    void checkBoxes(){
        boolean rotate = getGui().getSettings().isRotateBoard();
        System.out.println(rotate);
        rotateBoard.setSelected(rotate);
    }

}

