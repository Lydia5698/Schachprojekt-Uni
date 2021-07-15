package chess.model;

import chess.gui.Gui;
import java.io.*;

/**
 * Parser class to load the chess txt files.
 * @author Jasmin Wojtkiewicz
 */

public class Parser {


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

    /**
     * Loads a game for the gui. Applys moves, then sets the settings.
     * @param saved the chess txt file that will be loaded.
     * @param board the board.
     */
    public static void parserLoadCli(File saved, Board board) {

        try{
            // Open the file
            FileInputStream fileInputStream = new FileInputStream(saved);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            board.setSettings(board.getSettings());
            Boolean movesOver = false;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {

                if(strLine.contains("|")){
                    movesOver = true;
                }
                if(!movesOver){
                    Move move = new Move(strLine);
                    board.applyMove(move);
                    System.out.println("Made move :" + strLine);
                }
                else{
                    System.out.println(strLine);
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

    /**
     * Saves chess games for the Cli as a txt file.
     * @param selectedFile is the file with the filename
     * @param board the chessboard
     * @param turnNumber the turnnumber of the ai
     */
    public static void parserSaveCli(File selectedFile, Board board, int turnNumber ){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile)); //Erzeugen eines effizienten Writers für Textdateien
            for (Move move : board.getMoveList()){
                //move in string
                String moveString = move.getStart() + "-" + move.getEnd();
                writer.write(moveString);
                writer.newLine();
            }
            writer.write("|");
            writer.newLine();
            writer.write("AI-active ");
            if(board.settings.isAi_active()){
                writer.write("t");
            }
            else{
                writer.write("f");
            }
            writer.newLine();
            writer.write("AI-colour ");
            if(board.settings.isAi_colour()){
                writer.write("t");
            }
            else{
                writer.write("f");
            }
            writer.newLine();
            if(board.settings.isAi_active()){
                writer.write("AI-turnnumber ");
                int number = turnNumber;
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
