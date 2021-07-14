package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The StartScreenController is the Controller for the startScreen.fxml
 * The first Stage where the player can access the rules or the game choice
 */
public class StartScreenController extends MainController {
    //Popups popups = new Popups(getGui());

    @FXML
    private Button btnGameStart;

    @FXML
    private Button btnRules;

    @FXML
    private Button btnloadGame;

    @FXML
    private Label titel;

    @FXML
    private ImageView btnLanguage;

    /**
     * Ends the Application
     */
    @FXML
    void exit() {
        System.exit(0);
    }

    /**
     * Changes the Stage to the manual.fxml when the button rules is pushed
     */
    @FXML
    void showRules() {
        Stage stage = (Stage) btnRules.getScene().getWindow();
        show_FXML("manual.fxml", stage, getGui());
    }

    /**
     * Changes the Stage to the gameChoice.fxml when the button Game Start is pushed
     */
    @FXML
    void showGameChoice() {
        Stage stage = (Stage) btnGameStart.getScene().getWindow();
        show_FXML("gameChoice.fxml", stage, getGui());
    }

    /**
     * The language is changed in the settings when the Image btnLanguage is pushed
     */
    @FXML
    void changeLanguage() {
        getGui().getSettings().changeLanguage();
        changeToLanguage();
    }

    /**
     * Changes all buttons and text fields to the selected language
     */
    private void changeToLanguage() {
        btnLanguage.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "03")))).toExternalForm())));
        titel.setText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "00")));
        btnGameStart.setText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "01")));
        btnRules.setText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "02")));
        btnloadGame.setText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "04")));
    }

    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        changeToLanguage();
        getGui().getSettings().setGui_active(true);
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(103))).toExternalForm()));
    }

    /**
     * Opens the FileChooser and gives the choice to load a txt File
     */
    @FXML
    void loadGame() {
        Stage stage = (Stage) btnloadGame.getScene().getWindow();
        loadSaveController.loadFile(stage);
    }

}
