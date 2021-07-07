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
        title.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"60")));
        btnBlack.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"61")));
        btnWhite.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"62")));
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        changeToLanguage();
    }


}
