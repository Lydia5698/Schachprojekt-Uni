package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * We suppress the CPD rules in this controller because we cannot set the language differently.
 * We always have to access the buttons directly. We also need the FXML method with onAction. So
 * when you click on the picture the language changes.
 * The RulesController is the Controller for the manual.fxml
 * The player can read how to play the Game
 *
 *  @author Lydia Günther
 */
@SuppressWarnings({"PMD.CPD"})
public class ManualController extends MainController {

    @FXML
    private Button btnStartScreen;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private Text text;

    @FXML
    private Label title;

    /**
     * Changes the Stage to the startScreen.fxml when the button back is pushed
     */
    @FXML
    void showStartScreen() {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        show_FXML("startScreen.fxml", stage, getGui());
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
        btnStartScreen.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "40")));
        text.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "41")));
        title.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "42")));
    }
    @SuppressWarnings("CPD-END")

    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        changeToLanguage();

    }

}