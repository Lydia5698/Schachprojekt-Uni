package chess.model;

import chess.gui.Gui;
import java.io.*;
import java.util.ArrayList;

/**
 * Parser class to load the chess txt files.
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
                // Print the content on the console
                //System.out.println (strLine);
                if(strLine.contains("|")){
                    movesOver = true;
                    //System.out.println("seperator is here");
                }
                if(!movesOver){
                    Move move = new Move(strLine);
                    board.applyMove(move);
                    //System.out.println("Made move :" + strLine);
                }
                else{
                    //System.out.println("set settings here for the game");
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
            //TODO fuer spaeter : activeGameController.history();

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
                // Print the content on the console
                System.out.println (strLine);
                if(strLine.contains("|")){
                    movesOver = true;
                    System.out.println("seperator is here");
                }
                if(!movesOver){
                    Move move = new Move(strLine);
                    board.applyMove(move);
                    System.out.println("Made move :" + strLine);
                }
                else{
                    System.out.println("set settings here for the game");
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


    public static void parserSave(File txtList, ArrayList moveList ){

    }



}
