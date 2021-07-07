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
        getGui().getSettings().changeLanguage();
        changeToLanguage();
    }

    private void changeToLanguage(){
        if(getGui().getSettings().isLanguageEnglish()){
            btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt("203"))).toExternalForm())));
        }
        else {
            btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt("103"))).toExternalForm())));
        }
        btnChessBoard.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"30")));
        title.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"31")));
        HighlightPossibleMoves.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"32")));
        checkVisible.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"33")));
        doubleClick.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"34")));
        rotateBoard.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"35")));
        btnStartScreen.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"36")));
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        checkBoxes();
        changeToLanguage();
    }

}

