package chess.gui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadController{

    public void loadFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.showOpenDialog(stage);
    }


    /*
    final FileChooser fileChooser = new FileChooser();

    final Button openButton = new Button("Open a Picture...");
    final Button openMultipleButton = new Button("Open Pictures...");

        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent e) {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                openFile(file);
            }
        }
    });

        openMultipleButton.setOnAction(
                new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent e) {
            List<File> list =
                    fileChooser.showOpenMultipleDialog(stage);
            if (list != null) {
                for (File file : list) {
                    openFile(file);
                }
            }
        }
    });*/
}
