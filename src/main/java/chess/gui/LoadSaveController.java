package chess.gui;

import chess.model.Board;
import chess.model.Move;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import static chess.gui.MainController.show_FXML;

/**
 *
 * Controller class for loading and saving games.
 *  @author Lydia Günther
 */

public class LoadSaveController {

    private final Gui gui;


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
        parserLoadGui(selectedFile, gui);
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

    /**
     * Loads a game for the gui. Applys moves, then sets the settings.
     * @param saved the chess txt file that will be loaded.
     * @param gui the gui.
     */

    public static void parserLoadGui(File saved, Gui gui) {
        Board board = gui.getSettings().getBoard();

        try{
            // Open the file
            FileInputStream fileInputStream = new FileInputStream(saved);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            board.setSettings(gui.getSettings());
            Boolean movesOver = false;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                if(strLine.contains("|")){//seperator between moves and settings
                    movesOver = true;
                }
                if(!movesOver){
                    Move move = new Move(strLine);
                    board.applyMove(move);
                    //System.out.println("Made move :" + strLine);
                }
                else{
                    //set settings
                    if(strLine.equals("AI-active t")) {
                        board.getSettings().setAi_active(true);
                    }
                    if(strLine.equals("AI-colour t")){
                        board.getSettings().setAi_colour(true);
                    }
                    if(strLine.contains("AI-turnnumber ")){
                        String[] splitInput = strLine.split(" ");
                        String turnnumber = splitInput[1];
                        board.getSettings().getAi().setTurnNumber(Integer.parseInt(turnnumber));
                    }
                }
            }
            //Close the input stream
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
