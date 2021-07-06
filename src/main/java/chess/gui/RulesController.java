package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class RulesController extends MainController{

    @FXML
    private Button btnStartScreen;

    @FXML
    private ImageView btnLanguage;

    @FXML
    void showStartScreen(MouseEvent event) {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        show_FXML("startScreen.fxml", stage, getGui());
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
        btnStartScreen.setText(gui.getSettings().getLanguage().getDic().get(240));
    }

    private void changeToEnglish(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/GermanFlag.png")).toExternalForm()));
        btnStartScreen.setText(gui.getSettings().getLanguage().getDic().get(140));
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        if(gui.getSettings().isLanguageGerman()){
            changeToGerman();
        }

    }

}