package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class StartScreenController extends MainController {

    @FXML
    private Button btnSpielstart;

    @FXML
    private Button btnCredits;

    @FXML
    private Button btnAnleitung;

    @FXML
    private Label titel;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private void initialize(){
        //btnAnleitung.setText("LALA");
        //btnAnleitung.setText(getGui().getSettings().getLanguage().settings());

    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void showAnleitung(MouseEvent event) {
        Stage stage = (Stage) btnAnleitung.getScene().getWindow();
        show_FXML("anleitung.fxml", stage, getGui());
    }

    @FXML
    void showCredits(MouseEvent event) {
        Stage stage = (Stage) btnCredits.getScene().getWindow();
        show_FXML("credits.fxml", stage, getGui());
    }

    @FXML
    void showSpielauswahl(MouseEvent event) {
        Stage stage = (Stage) btnSpielstart.getScene().getWindow();
        show_FXML("gameChoice.fxml", stage, getGui());
    }

    @FXML
    void changeLanguage(MouseEvent event) {
        if(getGui().getSettings().isLanguageEnglish()){
            getGui().getSettings().setLanguageEnglish(false);
            getGui().getSettings().setLanguageGerman(true);
            changeToGerman();
            //btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/UnitedKingdomFlag.png")).toExternalForm()));
        }
        else {
            getGui().getSettings().setLanguageEnglish(true);
            getGui().getSettings().setLanguageGerman(false);
            changeToEnglish();
            //btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/GermanFlag.png")).toExternalForm()));

        }
    }

    private void changeToGerman(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/UnitedKingdomFlag.png")).toExternalForm()));
        titel.setText(getGui().getSettings().getLanguage().getDic().get(200));
        btnSpielstart.setText(getGui().getSettings().getLanguage().getDic().get(201));
        btnAnleitung.setText(getGui().getSettings().getLanguage().getDic().get(202));
    }

    private void changeToEnglish(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/GermanFlag.png")).toExternalForm()));
        titel.setText(getGui().getSettings().getLanguage().getDic().get(100));
        btnSpielstart.setText(getGui().getSettings().getLanguage().getDic().get(101));
        btnAnleitung.setText(getGui().getSettings().getLanguage().getDic().get(102));
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/GermanFlag.png")).toExternalForm()));
        if(gui.getSettings().isLanguageGerman()){
            changeToGerman();
        }

    }

}
