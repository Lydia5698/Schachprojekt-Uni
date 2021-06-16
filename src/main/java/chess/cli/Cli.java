package chess.cli;


//import chess.gui.Gui;

import chess.Settings;
import chess.model.AI;
import chess.model.Board;
import chess.model.Manuals;
import chess.model.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Starting point of the console game
 *
 * @author Jasmin Wojtkiewicz
 */
public class Cli {
    protected static Board board = new Board();
    protected static Manuals manuals = new Manuals();
    protected static Settings settings = new Settings();
    protected static AI ai;
    //protected boolean inSettingsMode = true;
    // protected AI ai = new AI(); //TODO later integrate this bit
    //TODO: integrate integer that counts the moves the ai made

    /**
     * The entry point of the console input application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        boolean simple = Arrays.asList(args).contains("--simple");
        board.setSimple(simple);
        boolean inSettingsMode = true;
        boolean inAISettingsMode = false;

        //TODO: set settings
        BufferedReader brs = new BufferedReader(new InputStreamReader(System.in));
        while(inSettingsMode) {
            try {
                System.out.println("Yoe are in the settings menu. To exit the menu type 'exit'. But first you have to choose. ");
                System.out.println("Do you want to play against a human, type 'human', want to play against an ai, type 'ai': ");
                String inputSetting = brs.readLine();
                if (inputSetting.contains("exit")){
                    inSettingsMode = false;
                }
                if (inputSetting.matches("human")){
                    settings.setAi_active(false);
                    inSettingsMode = false;

                }
                else if (inputSetting.matches("ai")){
                    settings.setAi_active(true);
                    inAISettingsMode = true;
                    inSettingsMode = false;
                }
                else if (inputSetting.contains("exit")){
                    inSettingsMode = false;
                }
                else{
                    //wait
                    System.out.println("You have to type the right word!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while(inAISettingsMode && settings.isAi_active()){
            System.out.println("What colour do you want to play, 'black' or 'white'?");
            try {
                String inputSetting = brs.readLine();
                if (inputSetting.matches("black")){
                    settings.setAi_colour(false);
                    System.out.println("You play black, the ai plays white.");
                    inAISettingsMode = false;
                    //create ai
                    ai = new AI(false);
                }
                else if (inputSetting.matches("white")){
                    settings.setAi_colour(true);
                    System.out.println("You play white, the ai plays black.");
                    inAISettingsMode = false;
                    //create ai
                    ai = new AI(true);
                }
                else{
                    System.out.println("You have to choose a colour for yourself!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //create ai again

        //TODO: with AI or without AI ? What colour do you wanna play? open settings with settings button
        String validInput = "[a-h][1-8]-[a-h][1-8]\\D?"; // Sind eingaben nach e2-e4 egal? Also als bsp: e2-e4uiei soll trotzdem den move e2-e4 ausführen?

        String output = board.showBoard();
        System.out.println(output);
        while (!board.isGameEnd()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            if (settings.isAi_active() && board.isBlackIsTurn() == settings.isAi_colour()){// wenn ai aktiviert, dann schauen, ob ai an der reihe
                System.out.print("AI moves");
                if (board.isBlackIsTurn()) { // ai is black and its turn it is
                    //look in movelist // get move at point
                    Move move = ai.getNextMove(board);
                    //apply move
                    System.out.println(move.getStart()+ "-"+ move.getEnd());
                    board.applyMove(move);
                    //TODO: count ai count integer 1 up, if ai-move
                    ai.increaseTurnNumber();
                    output = board.showBoard();
                    System.out.println(output);
                } else if (!board.isBlackIsTurn()) { // ai is white and its turn it is
                    Move move = ai.getNextMove(board);
                    // apply move
                    System.out.println(move.getStart()+ "-"+ move.getEnd());
                    board.applyMove(move);
                    //TODO: count ai count integer 1 up, if ai-move
                    ai.increaseTurnNumber();
                    output = board.showBoard();
                    System.out.println(output);
                }
            }
            else {
                System.out.print("Enter Move");
                if (board.isBlackIsTurn()) {
                    System.out.print(" for black\n");
                } else if (!board.isBlackIsTurn()) {
                    System.out.print(" for white\n");
                }
            }
            if (settings.isAi_active() == false || settings.isAi_active() && settings.isAi_colour() != board.isBlackIsTurn()) {
                try {
                    //TODO: see if ai is active, look if ai's move, else check input
                    // next move ai(current checkerBoard)
                    // possibleNextMoves(board)
                    // apply nextMove
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
        }
    }

}
