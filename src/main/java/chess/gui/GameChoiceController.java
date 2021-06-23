package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameChoiceController extends MainController {

    @FXML
    private Button btnOptions;

    @FXML
    private Button btnChessKI;

    @FXML
    private Button btnChessBoard;

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void showGui(MouseEvent event) {
        Stage stage = (Stage) btnChessBoard.getScene().getWindow();
        getGui().show_FXML("activeGame.fxml", stage);
    }

    @FXML
    void showKIGui(MouseEvent event) throws IOException {
        popupColour();
        Stage stage = (Stage) btnChessBoard.getScene().getWindow();
        getGui().show_FXML("activeGame.fxml", stage);
    }

    @FXML
    void showOptions(MouseEvent event) {
        Stage stage = (Stage) btnOptions.getScene().getWindow();
        getGui().show_FXML("options.fxml", stage);
    }

    @FXML
    void popupColour() throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("colourSelect.fxml"));
        Parent root = loader.load();
        Scene secondScene = new Scene(root);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(getGui().stage);
        newWindow.showAndWait();

    }

}
