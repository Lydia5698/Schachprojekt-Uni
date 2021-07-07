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
        Stage stage = (Stage) btnChessKI.getScene().getWindow();
        show_FXML("networkGui.fxml", stage, getGui());
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
        title.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"10")));
        btnChessBoard.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"11")));
        btnChessKI.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"12")));
        btnNetwork.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"13")));

    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        changeToLanguage();
    }

}
