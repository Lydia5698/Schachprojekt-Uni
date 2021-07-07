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
        titel.setText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"00")));
        btnSpielstart.setText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"01")));
        btnAnleitung.setText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"02")));
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        changeToLanguage();
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(203))).toExternalForm()));
    }

}
