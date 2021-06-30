package chess.gui;

import chess.model.Board;
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
        getGui().getSettings().setBoard(new Board());
        // set game false oder game end
    }

    @FXML
    void showChessBoard(MouseEvent event) {
        Stage stage = (Stage) btnChessBoard.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    void checkBoxes(){
        rotateBoard.setSelected(getGui().getSettings().isRotateBoard());
        lightPossibleMoves.setSelected(getGui().getSettings().isLightPossibleMoves());
        doubleClick.setSelected(getGui().getSettings().isDoubleClick());
        checkVisible.setSelected(getGui().getSettings().isCheckVisible());

    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        checkBoxes();
    }

}

