package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

}
