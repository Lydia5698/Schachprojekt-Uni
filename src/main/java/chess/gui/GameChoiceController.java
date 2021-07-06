package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameChoiceController extends MainController {


    @FXML
    private Button btnChessKI;

    @FXML
    private Button btnChessBoard;

    @FXML
    private Label title;

    @FXML
    private Button btnNetwork;

    @FXML
    private ImageView btnLanguage;

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void showGui(MouseEvent event) {
        Stage stage = (Stage) btnChessBoard.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    @FXML
    void showKIGui(MouseEvent event) throws IOException {
        popupColour();
        Stage stage = (Stage) btnChessKI.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    @FXML
    void showNetworkGame(MouseEvent event) {

    }


    @FXML
    void popupColour() throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("colourSelect.fxml"));
        Parent root = loader.load();
        Scene secondScene = new Scene(root);
        ((MainController)loader.getController()).setGui(getGui());
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(getGui().stage);
        newWindow.showAndWait();

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
        title.setText(gui.getSettings().getLanguage().getDic().get(210));
        btnChessBoard.setText(gui.getSettings().getLanguage().getDic().get(211));
        btnChessKI.setText(gui.getSettings().getLanguage().getDic().get(212));
        btnNetwork.setText(gui.getSettings().getLanguage().getDic().get(213));

    }

    private void changeToEnglish(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/GermanFlag.png")).toExternalForm()));
        title.setText(gui.getSettings().getLanguage().getDic().get(110));
        btnChessBoard.setText(gui.getSettings().getLanguage().getDic().get(111));
        btnChessKI.setText(gui.getSettings().getLanguage().getDic().get(112));
        btnNetwork.setText(gui.getSettings().getLanguage().getDic().get(113));

    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        if(gui.getSettings().isLanguageGerman()){
            changeToGerman();
        }
    }

}
