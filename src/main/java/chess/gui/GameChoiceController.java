package chess.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The GameChoiceController is the Controller for the gameChoice.fxml
 * The player can decide which Game(pvp,AI,Network) he wants to play
 */
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

    /**
     * Ends the Application
     */
    @FXML
    void exit() {
        System.exit(0);
    }

    /**
     * Changes the Stage to the activeGame.fxml when the button pvp is pushed
     */
    @FXML
    void showGui() {
        Stage stage = (Stage) btnChessBoard.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    /**
     * Changes the Stage to the activeGame.fxml when the button AgainstAI is pushed
     * and pops the popup for the Colour Choice
     *
     * @throws IOException when the popup isnt getting closed
     */
    @FXML
    void showKIGui() throws IOException {
        popupColour();
        Stage stage = (Stage) btnChessKI.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    /**
     * Changes the Stage to the networkGui.fxml when the button Network Game is pushed
     */
    @FXML
    void showNetworkGame() {
        Stage stage = (Stage) btnChessKI.getScene().getWindow();
        show_FXML("networkGui.fxml", stage, getGui());
    }


    /**
     * The popup shows the colourSelect.fxml and waits until the colour is selected
     *
     * @throws IOException in case the popup isnt getting closed
     */
    @FXML
    void popupColour() throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("colourSelect.fxml"));
        Parent root = loader.load();
        Scene secondScene = new Scene(root);
        ((MainController) loader.getController()).setGui(getGui());
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(getGui().stage);
        newWindow.showAndWait();
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
        title.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "10")));
        title.setAlignment(Pos.TOP_CENTER);
        btnChessBoard.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "11")));
        btnChessKI.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "12")));
        btnNetwork.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "13")));
    }

    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        changeToLanguage();
    }

}
