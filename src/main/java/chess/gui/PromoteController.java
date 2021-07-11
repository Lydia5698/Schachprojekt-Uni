package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The PromoteController is the Controller for the promote.fxml
 * The player can decide which Minion the Pawn is promote to
 */
public class PromoteController extends MainController {

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

    /**
     * Sets the promoteTo in activeGameController to B (Bishop) when the button btnBishop is pushed
     */
    @FXML
    void promoteMinionBishop() {
        activeGameController.setPromoteTo("B");
        Stage stage = (Stage) btnBishop.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the promoteTo in activeGameController to N (Knight) when the button btnKnight is pushed
     */
    @FXML
    void promoteMinionKnight() {
        activeGameController.setPromoteTo("N");
        Stage stage = (Stage) btnKnight.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the promoteTo in activeGameController to Q (Queen) when the button btnQueen is pushed
     */
    @FXML
    void promoteMinionQueen() {
        activeGameController.setPromoteTo("Q");
        Stage stage = (Stage) btnQueen.getScene().getWindow();
        stage.close();

    }

    /**
     * Sets the promoteTo in activeGameController to R (Rook) when the button btnRook is pushed
     */
    @FXML
    void promoteMinionRook() {
        activeGameController.setPromoteTo("R");
        Stage stage = (Stage) btnRook.getScene().getWindow();
        stage.close();

    }

    /**
     * Sets the active Game controller so that the promoteTo gets set in the activeGameController
     *
     * @param activeGameController the activeGameController
     */
    public void setController(ActiveGameController activeGameController) {
        this.activeGameController = activeGameController;
    }

    /**
     * The language is changed in the settings when the Image btnLanguage is pushed
     */
    @FXML
    void changeLanguage() {
        getGui().getSettings().changeLanguage();
        changeToLanguage();
    }

    /**
     * Changes all buttons and text fields to the selected language
     */
    private void changeToLanguage() {
        btnLanguage.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "03")))).toExternalForm())));
        header.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "50")));
    }

    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        changeToLanguage();
    }

}
