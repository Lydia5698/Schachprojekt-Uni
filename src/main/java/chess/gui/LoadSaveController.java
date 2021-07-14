package chess.gui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LoadSaveController {

    /**
     * Opens the FileChooser. The Player can choose a Txt file to load
     * @param stage the current stage
     */
    public void loadFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
    }

    /**
     * Opens the FileChooser. The Player can choose where the Txt file is saved
     * @param stage the current stage
     */
    public void saveFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(stage);
    }

}
