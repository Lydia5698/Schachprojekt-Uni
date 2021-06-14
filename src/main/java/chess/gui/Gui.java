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
    /**
     * This method is called by the Application to start the GUI.
     *
     * @param primaryStage The initial root stage of the application.
     */

    protected Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
    show_FXML("startScreen.fxml", primaryStage);
    }

    public void show_FXML(String filename, Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Gui.class.getResource(filename));
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


    /*public void update{
        boolean simple = Arrays.asList(args).contains("--simple");
        board.setSimple(simple);
        String validInput = "[a-h][1-8]-[a-h][1-8]\\D?"; // Sind eingaben nach e2-e4 egal? Also als bsp: e2-e4uiei soll trotzdem den move e2-e4 ausführen?

        String output = board.showBoard();
        System.out.println(output);
        while (!board.isGameEnd()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Move");
            if (board.isBlackIsTurn()) {
                System.out.print(" for black\n");
            } else {
                System.out.print(" for white\n");
            }
            try {
                String input = br.readLine();
                if (input.matches(validInput)) {
                    Move move = new Move(input);
                    if (manuals.moveOfRightColour(move, board)) {
                        board.applyMove(move);
                        output = board.showBoard();
                        System.out.println(output);
                    } else {
                        System.out.println("!Move not allowed");
                    }
                } else if (input.equals("beaten")) {
                    String beatenString = "Beaten Figures";
                    for (String beatenMinion : board.getBeaten()) {
                        beatenString = String.join(",", beatenString, beatenMinion);
                    }
                    System.out.println(beatenString);
                } else {
                    System.out.println("!Invalid move");
                }


            } catch (IOException e) {
                e.printStackTrace();
                break;

            }
        }
    }*/



}


