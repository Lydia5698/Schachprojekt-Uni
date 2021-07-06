package chess.gui;

import chess.model.AI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class ColourSelectController extends MainController {

    @FXML
    private Button btnBlack;

    @FXML
    private Button btnWhite;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private Label title;


    @FXML
    void colourBlack(MouseEvent event) {
        //White AI
        getGui().getSettings().setAi(new AI(false));
        getGui().getSettings().setAi_active(true);
        getGui().getSettings().setAi_colour(false);
        Stage stage = (Stage) btnBlack.getScene().getWindow();
        stage.close();
    }

    @FXML
    void colourWhite(MouseEvent event) {
        // Black AI
        getGui().getSettings().setAi(new AI(true));
        getGui().getSettings().setAi_active(true);
        getGui().getSettings().setAi_colour(true);
        Stage stage = (Stage) btnWhite.getScene().getWindow();
        stage.close();
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
        title.setText(gui.getSettings().getLanguage().getDic().get(260));
        btnBlack.setText(gui.getSettings().getLanguage().getDic().get(261));
        btnWhite.setText(gui.getSettings().getLanguage().getDic().get(262));
    }

    private void changeToEnglish(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/GermanFlag.png")).toExternalForm()));
        title.setText(gui.getSettings().getLanguage().getDic().get(160));
        btnBlack.setText(gui.getSettings().getLanguage().getDic().get(161));
        btnWhite.setText(gui.getSettings().getLanguage().getDic().get(162));
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        if(gui.getSettings().isLanguageGerman()){
            changeToGerman();
        }
    }


}
