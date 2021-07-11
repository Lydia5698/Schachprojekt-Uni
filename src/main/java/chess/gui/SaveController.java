package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class SaveController extends MainController {

    @FXML
    private TextField txtField;

    @FXML
    private Label txtDateName;

    @FXML
    private Button btnSubmit;

    @FXML
    void saveName(ActionEvent event) {

    }

    @FXML
    void saveData(ActionEvent event) {

    }

    UnaryOperator<TextFormatter.Change> filter = t -> {

        if (t.isReplaced()) {
            if (!t.getText().matches("\\w")) {
                t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));

            }
        }

        if (t.isAdded()) {
            if (!t.getText().matches("\\w")) {
                t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
            }
        }

        return t;
    };

    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        txtField.setTextFormatter(new TextFormatter<>(filter));
    }


}
