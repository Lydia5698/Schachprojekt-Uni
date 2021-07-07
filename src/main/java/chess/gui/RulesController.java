package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class RulesController extends MainController{

    @FXML
    private Button btnStartScreen;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private Text text;

    @FXML
    void showStartScreen() {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        show_FXML("startScreen.fxml", stage, getGui());
    }


    @FXML
    void changeLanguage() {
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
        btnStartScreen.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"40")));
        text.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"41")));
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        changeToLanguage();

    }

}