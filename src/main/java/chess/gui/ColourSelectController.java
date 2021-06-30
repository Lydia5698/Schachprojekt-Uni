package chess.gui;

import chess.model.AI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ColourSelectController extends MainController {

    @FXML
    private Button btnBlack;

    @FXML
    private Button btnWhite;

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

}
