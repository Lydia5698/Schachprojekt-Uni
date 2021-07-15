package chess.gui;

import chess.model.Board;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * We suppress the CPD rules in this controller because we cannot set the language differently.
 * We always have to access the buttons directly. We also need the FXML method with onAction. So
 * when you click on the picture the language changes.
 *
 * The OptionsController is the Controller for the options.fxml
 * The player can adjust the options (checkVisible,HighlightPossibleMoves,rotateBoard and doubleClick)
 *
 *  @author Lydia Günther
 */
@SuppressWarnings({"PMD.CPD"})
public class OptionsController extends MainController {

    @FXML
    private CheckBox HighlightPossibleMoves;

    @FXML
    private CheckBox cbDoubleClick;

    @FXML
    private Button btnStartScreen;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnChessBoard;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private CheckBox checkVisible;

    @FXML
    private CheckBox cbRotateBoard;

    @FXML
    private Label title;


    /**
     * The Checkbox for the Check Option
     * sets the option in the settings as selected/not selected
     */
    @FXML
    void KingCheck() {
        getGui().getSettings().setCheckVisible(checkVisible.isSelected());
    }

    /**
     * The Checkbox for the doubleClick Option
     * sets the option in the settings as selected/not selected
     */
    @FXML
    void doubleClick() {
        getGui().getSettings().setDoubleClick(cbDoubleClick.isSelected());
    }

    /**
     * Ends the Application
     */
    @FXML
    void exit() throws Exception {
        System.exit(0);
    }

    /**
     * The Checkbox for the possible Moves highlighting Option
     * sets the option in the settings as selected/not selected
     */
    @FXML
    void possibleMoves() {
        getGui().getSettings().setHighlightPossibleMoves(HighlightPossibleMoves.isSelected());
    }

    /**
     * The Checkbox for the rotate Board Option
     * sets the option in the settings as selected/not selected
     */
    @FXML
    void rotateBoard() {
        getGui().getSettings().setRotateBoard(cbRotateBoard.isSelected());
    }

    /**
     * Changes the Stage to the startScreen.fxml when the button back is pushed
     */
    @FXML
    void showStartScreen() throws Exception {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        show_FXML("startScreen.fxml", stage, getGui());
        Board board = new Board();
        board.setSettings(gui.settings);
        getGui().getSettings().setBoard(board);
        getGui().settings.getSettingsNetwork().setNetwork_active(false);
        getGui().getSettings().setAi_active(false);
        getGui().getSettings().setGameEnd(false);
        getGui().getSettings().setHighlightPossibleMoves(false);
        getGui().getSettings().setCheckVisible(false);
        getGui().getSettings().setRotateBoard(false);
        getGui().getSettings().setDoubleClick(false);
    }

    /**
     * Changes the Stage to the activeGame.fxml when the button end Game is pushed
     */
    @FXML
    void showChessBoard() {
        Stage stage = (Stage) btnChessBoard.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    /**
     * sets the checkboxes as active/not active
     */
    void checkBoxes() {
        cbRotateBoard.setSelected(getGui().getSettings().isRotateBoard());
        HighlightPossibleMoves.setSelected(getGui().getSettings().isHighlightPossibleMoves());
        cbDoubleClick.setSelected(getGui().getSettings().isDoubleClick());
        checkVisible.setSelected(getGui().getSettings().isCheckVisible());

    }

    /**
     * The language is changed in the settings when the Image btnLanguage is pushed
     */
    @SuppressWarnings("CPD-START")
    @FXML
    void changeLanguage() {
        getGui().getSettings().getSettingsLanguage().changeLanguage();
        changeToLanguage();
    }

    /**
     * Changes all buttons and text fields to the selected language
     */
    private void changeToLanguage() {
        btnLanguage.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "03")))).toExternalForm())));
        btnChessBoard.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "30")));
        title.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "31")));
        HighlightPossibleMoves.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "32")));
        checkVisible.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "33")));
        cbDoubleClick.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "34")));
        cbRotateBoard.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "35")));
        btnStartScreen.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "36")));
    }
    @SuppressWarnings("CPD-END")

    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        checkBoxes();
        changeToLanguage();
    }

}

