package chess.gui;


import chess.Main;
import chess.Settings;
import chess.model.Move;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;




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
     * The entry point ogit f the GUI application.
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


