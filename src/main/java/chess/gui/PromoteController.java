package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class PromoteController extends MainController{

    ActiveGameController activeGameController;

    @FXML
    private Button btnBishop;

    @FXML
    private Button btnKnight;

    @FXML
    private Button btnRook;

    @FXML
    private Button btnQueen;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private Label header;


    @FXML
    void promoteMinionBishop(MouseEvent event) {
        activeGameController.setPromoteTo("B");
        Stage stage = (Stage) btnBishop.getScene().getWindow();
        stage.close();


    }

    @FXML
    void promoteMinionKnight(MouseEvent event) {
        activeGameController.setPromoteTo("N");
        Stage stage = (Stage) btnKnight.getScene().getWindow();
        stage.close();

    }

    @FXML
    void promoteMinionQueen(MouseEvent event) {
        activeGameController.setPromoteTo("Q");
        Stage stage = (Stage) btnQueen.getScene().getWindow();
        stage.close();

    }

    @FXML
    void promoteMinionRook(MouseEvent event) {
        activeGameController.setPromoteTo("R");
        Stage stage = (Stage) btnRook.getScene().getWindow();
        stage.close();

    }

    public void setController(ActiveGameController activeGameController){
        this.activeGameController = activeGameController;
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
        header.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"50")));
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        changeToLanguage();
    }

}
