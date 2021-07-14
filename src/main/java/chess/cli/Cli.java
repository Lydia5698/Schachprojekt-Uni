package chess.cli;


//import chess.gui.Gui;

import chess.Settings;
import chess.model.*;

import java.io.*;
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
    //TODO !!!! aufruf mit --simple und der gui sollte funktionieren, noch eine if einbauen
    //TODO Testcoverage


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
        boolean inNetworkSettingsMode = false;
        boolean join = false;
        String languageNumber = "1";
        board.setSettings(settings);
        boolean loadGame= false;
        boolean saveGame = false;

        if (!simple) {
            BufferedReader brs = new BufferedReader(new InputStreamReader(System.in));
            while (inSettingsMode) {
                try {
                    System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "63")));
                    System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "64")));
                    String inputSetting = brs.readLine();
                    if (inputSetting.contains("exit")) {
                        inSettingsMode = false;
                    }
                    if (inputSetting.matches("german") || inputSetting.contains("deutsch") || inputSetting.contains("chen")) {
                        settings.getSettingsLanguage().setLanguageEnglish(false);
                        settings.getSettingsLanguage().setLanguageGerman(true);
                        settings.getSettingsLanguage().setLanguageKlingon(false);
                        languageNumber = "2";
                    }
                    if (inputSetting.matches("english") || inputSetting.contains("englisch") || inputSetting.contains("Te ra'")) {
                        settings.getSettingsLanguage().setLanguageEnglish(true);
                        settings.getSettingsLanguage().setLanguageGerman(false);
                        settings.getSettingsLanguage().setLanguageKlingon(false);
                        languageNumber = "1";
                    }
                    if (inputSetting.matches("klingon") || inputSetting.contains("klingonisch") || inputSetting.contains("tlhIngan")) {
                        settings.getSettingsLanguage().setLanguageEnglish(false);
                        settings.getSettingsLanguage().setLanguageGerman(false);
                        settings.getSettingsLanguage().setLanguageKlingon(true);
                        languageNumber = "3";
                    }
                    if (inputSetting.matches("human") || inputSetting.contains("mensch") || inputSetting.contains("loD")) {
                        settings.setAi_active(false);
                        inSettingsMode = false;

                    } else if (inputSetting.matches("ai") || inputSetting.contains("ki")|| inputSetting.contains("Qeq")) {
                        settings.setAi_active(true);
                        inAISettingsMode = true;
                        inSettingsMode = false;
                    } else if (inputSetting.matches("network") || inputSetting.contains("netzwerk")|| inputSetting.contains("joq")) {
                        settings.getSettingsNetwork().createClient();
                        inSettingsMode = false;
                        inNetworkSettingsMode = true;
                        // IP Addresse eingabe
                    }
                    else if (inputSetting.contains("exit")) {
                        inSettingsMode = false;
                    }
                    else if (inputSetting.contains("load game")){
                        loadGame= true;

                        //TODO: print files in path
                        System.out.println("Choose one of the following chess games to load by typing in the correct name of the file:");

                        File folder = new File("src");
                        File[] listOfFiles = folder.listFiles();

                        for (int i = 0; i < listOfFiles.length; i++) {
                            File file = listOfFiles[i];
                            if (file.isFile() && file.getName().endsWith(".txt")) {
                                System.out.println(file.getName());
                            }
                        }

                    }
                    else if(inputSetting.contains("Spiel laden")){
                        System.out.println("deutsch spielwahl");

                    }
                    else if(loadGame) {
                        File file = new File(brs.readLine());
                        Parser.parserLoadCli(file, board);
                        loadGame = false;
                        board.showBoard();
                    }
                    else {
                        //wait
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "65")));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            while (inAISettingsMode && settings.isAi_active()) {
                System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "66")));
                try {
                    String inputSetting = brs.readLine();
                    if (inputSetting.matches("black")) {
                        settings.setAi_colour(false);
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "67")));
                        inAISettingsMode = false;
                        //create ai
                        ai = new AI(false);
                    } else if (inputSetting.matches("white")) {
                        settings.setAi_colour(true);
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "68")));
                        inAISettingsMode = false;
                        //create ai
                        ai = new AI(true);
                    } else {
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "69")));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            while (inNetworkSettingsMode) {
                if(!join){
                    System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "05")));
                }

                try {
                    String inputSetting = brs.readLine();
                    if (inputSetting.matches("join")) {
                        join = true;
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "06")));
                    } else if (inputSetting.matches("host")) {
                        settings.getSettingsNetwork().setConnection(settings.getSettingsNetwork().createServer());
                        settings.getSettingsNetwork().setPort(4848);
                        settings.getSettingsNetwork().setNetwork_active(true); // farbwahl
                        inNetworkSettingsMode = false;
                        try { settings.getSettingsNetwork().initCon();}
                        catch (Exception e){
                            System.out.println ("exception when establishing con"); }
                    } else {
                        settings.getSettingsNetwork().setIp(inputSetting);
                        settings.getSettingsNetwork().setConnection(settings.getSettingsNetwork().createClient());
                        settings.getSettingsNetwork().setPort(4848);
                        settings.getSettingsNetwork().setNetwork_active(true);
                        inNetworkSettingsMode = false;
                        try { settings.getSettingsNetwork().initCon();}
                        catch (Exception e){
                            System.out.println ("exception when establishing con"); }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //create ai again

        //TODO: with AI or without AI ? What colour do you wanna play? open settings with settings button
        String validInput = "[a-h][1-8]-[a-h][1-8]\\D?"; // Sind eingaben nach e2-e4 egal? Also als bsp: e2-e4uiei soll trotzdem den move e2-e4 ausführen?

        String output = board.showBoard();
        System.out.println(output);
        while (!settings.isGameEnd()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            if (settings.isAi_active() && board.isBlackIsTurn() == settings.isAi_colour()) {// wenn ai aktiviert, dann schauen, ob ai an der reihe
                System.out.print(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "72")));
                if (board.isBlackIsTurn()) { // ai is black and its turn it is
                    //look in movelist // get move at point
                    Move move = ai.getNextMove(board);
                    //apply move
                    System.out.println(move.getStart() + "-" + move.getEnd());
                    board.applyMove(move);
                    //count ai count integer 1 up, if ai-move
                    ai.increaseTurnNumber();
                    output = board.showBoard();
                    System.out.println(output);
                } else if (!board.isBlackIsTurn()) { // ai is white and its turn it is
                    Move move = ai.getNextMove(board);
                    // apply move
                    System.out.println(move.getStart() + "-" + move.getEnd());
                    board.applyMove(move);
                    //count ai count integer 1 up, if ai-move
                    ai.increaseTurnNumber();
                    output = board.showBoard();
                    System.out.println(output);
                }
            } else {
                System.out.print(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "73")));
                if (board.isBlackIsTurn()) {
                    System.out.print(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "74")));
                } else if (!board.isBlackIsTurn()) {
                    System.out.print(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "75")));
                }
            }
            if (!settings.isAi_active() || settings.isAi_active() && settings.isAi_colour() != board.isBlackIsTurn()) {
                try {
                    String input = br.readLine();
                    if (input.matches(validInput)) {
                        Move move = new Move(input);
                        if (manuals.moveOfRightColour(move, board)) {
                            board.applyMove(move);
                            output = board.showBoard();
                            System.out.println(output);
                        } else {
                            System.out.println("!" + settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "70")));
                        }
                    } else if (input.equals("beaten")) {
                        String beatenString = "Beaten Figures";
                        for (String beatenMinion : board.getBeaten()) {
                            beatenString = String.join(",", beatenString, beatenMinion);
                        }
                        System.out.println(beatenString);
                    }
                    else if(input.contains("save game")){
                        saveGame = true;
                        System.out.println("Type a file name:");
                    }
                    else if(saveGame){
                        String gameName = input;
                        File selectedFile = new File(gameName);    //creates a new file instance
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
                            //TODO: evtl mehr eigenschaften speichern, netzwerkspiel?
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
                                int number = board.settings.getAi().getTurnNumber();
                                writer.write(String.valueOf(number));
                            }
                            writer.flush();
                            writer.close();
                        }
                        catch(IOException ioe) {
                            System.err.println(ioe);
                        }
                    }
                    else {
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(languageNumber + "76")));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }

}
