package chess.gui;


import chess.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;




/**
 * Starting point of the JavaFX GUI
 */
public class Gui extends Application {
    /**
     * This method is called by the Application to start the GUI.
     *
     * @param primaryStage The initial root stage of the application.
     */


    @Override
    public void start(Stage primaryStage) throws Exception {


        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/spielauswahl.fxml"));
            Parent root = (Parent) loader.load();
            Scene gameScene = new Scene(root, 1000, 1000);
            primaryStage.setScene(gameScene);
            primaryStage.show();

        }  catch (IOException e) {

        e.printStackTrace();
    }
    }
    /*
    Für die Combobox on Spielauswahl
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    comboBox.getItems().removeAll(comboBox.getItems());
    comboBox.getItems().addAll("Schwarz", "Weiß", "Zufall");
    comboBox.getSelectionModel().select("Option B");
    }
     */


    /**
     * The entry point of the GUI application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
