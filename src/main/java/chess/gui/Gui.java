package chess.gui;

import chess.Settings;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Starting point of the JavaFX GUI
 */
public class Gui extends Application {


    protected Stage stage;
    Settings settings = new Settings();

    /**
     * This method is called by the Application to start the GUI.
     *
     * @param primaryStage The initial root stage of the application.
     */

    @Override
    public void start(Stage primaryStage) throws Exception { //TODO oberklasse mit allen setter methoden, Menue controller , gamecontroller, bedienen das interface, in allen controllen soll die gui gesetzt werden
        stage = primaryStage;
        MainController.show_FXML("startScreen.fxml", primaryStage, this);
    }


    /**
     * The entry point of the GUI application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Settings getSettings() {
        return settings;
    }

}


