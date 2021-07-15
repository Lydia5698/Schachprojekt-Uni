package chess.gui;

import chess.model.Move;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import chess.model.Parser;

import static chess.gui.MainController.show_FXML;

/**
 *
 * Controller class for loading and saving games.
 *  @author Lydia Günther
 */

public class LoadSaveController {

    private final Gui gui;
    private Parser parser;


    void showGui(Stage stage) {
        show_FXML("activeGame.fxml", stage, gui);
    }

    /**
     * creates a LoadSaveController instance to load or save files. The controller gets the
     * current gui
     * @param gui current gui
     */
    public LoadSaveController(Gui gui) {
        this.gui = gui;
    }

    /**
     * Opens the FileChooser. The Player can choose a Txt file to load
     * @param stage the current stage
     */

    public void loadFile(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Check File");
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        parser.parserLoadGui(selectedFile, gui);

        //zur active gamecontroller
        showGui(stage);

    }

    /**
     * Opens the FileChooser. The Player can choose where the Txt file is saved
     * Method to save a running chess game. Saves every move in a new line.
     * @param stage the current stage
     */
    public void saveFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        //richtiges directory
        fileChooser.setTitle("Save Check File");
        fileChooser.setInitialDirectory(new File("src"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile)); //Erzeugen eines effizienten Writers für Textdateien
            for (Move move : gui.settings.getBoard().getMoveList()){
                //move in string
                String moveString = move.getStart() + "-" + move.getEnd();
                writer.write(moveString);
                writer.newLine();
            }


            writer.write("|");
            writer.newLine();
            //TODO: evtl mehr eigenschaften speichern, netzwerkspiel?
            writer.write("AI-active ");
            if(gui.settings.isAi_active()){
                writer.write("t");
            }
            else{
                writer.write("f");
            }
            writer.newLine();
            writer.write("AI-colour ");
            if(gui.settings.isAi_colour()){
                writer.write("t");
            }
            else{
                writer.write("f");
            }
            writer.newLine();
            if(gui.settings.isAi_active()){
                writer.write("AI-turnnumber ");
                int number = gui.settings.getAi().getTurnNumber();
                writer.write(String.valueOf(number));
            }
            writer.flush();
            writer.close();
        }
        catch(IOException ioe) {
            System.err.println(ioe);
        }
    }

}
