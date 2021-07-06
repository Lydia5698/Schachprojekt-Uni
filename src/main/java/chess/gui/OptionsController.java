package chess.gui;

import chess.model.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class OptionsController extends MainController {

    @FXML
    private CheckBox HighlightPossibleMoves;

    @FXML
    private CheckBox doubleClick;

    @FXML
    private Button btnStartScreen;

    @FXML
    private Button btnChessBoard;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private CheckBox checkVisible;

    @FXML
    private CheckBox rotateBoard;

    @FXML
    private Label title;


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
        getGui().getSettings().setLightPossibleMoves(HighlightPossibleMoves.isSelected());
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
        HighlightPossibleMoves.setSelected(getGui().getSettings().isLightPossibleMoves());
        doubleClick.setSelected(getGui().getSettings().isDoubleClick());
        checkVisible.setSelected(getGui().getSettings().isCheckVisible());

    }
    @FXML
    void changeLanguage(MouseEvent event) {
        if(getGui().getSettings().isLanguageEnglish()){
            getGui().getSettings().setLanguageEnglish(false);
            getGui().getSettings().setLanguageGerman(true);
            changeToGerman();
        }
        else {
            getGui().getSettings().setLanguageEnglish(true);
            getGui().getSettings().setLanguageGerman(false);
            changeToEnglish();

        }
    }

    private void changeToGerman(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/UnitedKingdomFlag.png")).toExternalForm()));
        btnChessBoard.setText(gui.getSettings().getLanguage().getDic().get(230));
        title.setText(gui.getSettings().getLanguage().getDic().get(231));
        HighlightPossibleMoves.setText(gui.getSettings().getLanguage().getDic().get(232));
        checkVisible.setText(gui.getSettings().getLanguage().getDic().get(233));
        doubleClick.setText(gui.getSettings().getLanguage().getDic().get(234));
        rotateBoard.setText(gui.getSettings().getLanguage().getDic().get(235));
        btnStartScreen.setText(gui.getSettings().getLanguage().getDic().get(236));
    }

    private void changeToEnglish(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/GermanFlag.png")).toExternalForm()));
        btnChessBoard.setText(gui.getSettings().getLanguage().getDic().get(130));
        title.setText(gui.getSettings().getLanguage().getDic().get(131));
        HighlightPossibleMoves.setText(gui.getSettings().getLanguage().getDic().get(132));
        checkVisible.setText(gui.getSettings().getLanguage().getDic().get(133));
        doubleClick.setText(gui.getSettings().getLanguage().getDic().get(134));
        rotateBoard.setText(gui.getSettings().getLanguage().getDic().get(135));
        btnStartScreen.setText(gui.getSettings().getLanguage().getDic().get(136));
    }


    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        checkBoxes();
        if(gui.getSettings().isLanguageGerman()){
            changeToGerman();
        }
    }

}

