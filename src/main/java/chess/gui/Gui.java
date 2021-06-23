package chess.gui;


import chess.Main;
import chess.model.Move;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
    //MainController mainController = new MainController();
    /**
     * This method is called by the Application to start the GUI.
     *
     * @param primaryStage The initial root stage of the application.
     */

    @Override
    public void start(Stage primaryStage) throws Exception { //TODO oberklasse mit allen setter methoden, Menue controller , gamecontroller, bedienen das interface, in allen controllen soll die gui gesetzt werden
        stage = primaryStage;
    show_FXML("startScreen.fxml", primaryStage);
    }

    public void show_FXML(String filename, Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Gui.class.getResource(filename));
            Parent root = (Parent) loader.load();
            Scene gameScene = new Scene(root);
            primaryStage.setScene(gameScene);
            primaryStage.show();
            ((MainController)loader.getController()).setGui(this); //hier noch oberklasse


        }  catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * The entry point ogit f the GUI application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

}


