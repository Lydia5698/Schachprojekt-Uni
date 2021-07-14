package chess.gui;

import chess.model.AI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The ColourSelectController is the Controller for the colourSelect.fxml
 * In the game the Player can decide which colour he want to play against the AI
 * ColourSelect is a popup after GameChoice
 */
public class ColourSelectController extends MainController {

    @FXML
    private Button btnBlack;

    @FXML
    private Button btnWhite;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private Label title;


    /**
     * when the Button btnBlack is pushed the white AI gets initialized and the colour is set
     * in the end the stage is closed
     */
    @FXML
    void colourBlack() {
        //White AI
        getGui().getSettings().setAi(new AI(false));
        getGui().getSettings().setAi_active(true);
        getGui().getSettings().setAi_colour(false);
        Stage stage = (Stage) btnBlack.getScene().getWindow();
        stage.close();
    }

    /**
     * when the Button btnWhite is pushed the black AI gets initialized and the colour is set
     * in the end the stage is closed
     */

    @FXML
    void colourWhite() {
        // Black AI
        getGui().getSettings().setAi(new AI(true));
        getGui().getSettings().setAi_active(true);
        getGui().getSettings().setAi_colour(true);
        Stage stage = (Stage) btnWhite.getScene().getWindow();
        stage.close();
    }


    /**
     * The language is changed in the settings when the Image btnLanguage is pushed
     */
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
        title.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "60")));
        btnBlack.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "61")));
        btnWhite.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "62")));
    }


    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        changeToLanguage();
    }


}
