package chess.cli;

import chess.Settings;
import chess.model.*;

import java.io.*;
import java.util.Arrays;

//CyclomaticComplexity for the whole class is 80 and it would be very unnecessary to make a new class for the reduction below 80.
@SuppressWarnings({"PMD.CyclomaticComplexity"})

/**
 * Starting point of the console game.
 *
 * @author Jasmin Wojtkiewicz
 */
public class Cli {
    protected static Board board = new Board();
    protected static Manuals manuals = new Manuals();
    protected static Settings settings = new Settings();
    protected static AI ai;
    static boolean inAISettingsMode = false;

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
        board.setSettings(settings);
        boolean loadGame= false;
        boolean saveGame = false;

        if (!simple) {
            BufferedReader brs = getBufferedReaderWhileInSettingsMode(inSettingsMode, loadGame);

            while (inAISettingsMode && settings.isAi_active()) {
                System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "66")));
                try {
                    String inputSetting = brs.readLine();
                    languageIsTyped(inputSetting);
                    if (isBlackTyped(inputSetting)) {
                        settings.setAi_colour(false);
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "67")));
                        inAISettingsMode = false;
                        //create ai
                        ai = new AI(false);
                    } else if (isWhiteTyped(inputSetting)) {
                        settings.setAi_colour(true);
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "68")));
                        inAISettingsMode = false;
                        //create ai
                        ai = new AI(true);
                    } else {
                        System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "69")));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } // bis hier hin normale ausgaben weil --simple false ist

        //create ai again
        String validInput = "[a-h][1-8]-[a-h][1-8]\\D?";

        String output = board.showBoard();
        System.out.println(output);
        while (!settings.isGameEnd()) {
            BufferedReader br = getBufferedReaderAndMakeAIMoves(settings.getSettingsLanguage().getLanguageNumber());
            saveGame = makeMoveFromInputOutputBeatenFiguresAndSaveGame(saveGame, validInput, br);
        }
    }

    private static boolean isWhiteTyped(String inputSetting){
        Boolean whiteIsTyped = false;
        if(inputSetting.matches("white")||inputSetting.matches("weiss")||inputSetting.matches("chIS")){
            whiteIsTyped=true;
        }
        return whiteIsTyped;
    }

    private static boolean isBlackTyped(String inputSetting){
        Boolean blackIsTyped = false;
        if(inputSetting.matches("black")||inputSetting.matches("schwarz")||inputSetting.matches("qlj")){
            blackIsTyped = true;
        }
        return blackIsTyped;
    }

    private static boolean isInputTurn(){
        boolean inputTurn = false;
        if(!settings.isAi_active() || settings.isAi_active() && settings.isAi_colour() != board.isBlackIsTurn()){
            inputTurn = true;
        }
        return inputTurn;
    }

    private static boolean makeMoveFromInputOutputBeatenFiguresAndSaveGame(boolean sGame, String validInput, BufferedReader br) {//TODO: cyccomplex von 11 auf 9
        boolean saveGame = sGame;
        if (isInputTurn()) {
            try {
                String input = br.readLine();
                makeMoveFromInputAndOutputBeatenFigures(input, validInput);

                if(input.contains("save game")){
                    saveGame = true;
                    System.out.println("Type a file name:");
                }
                else if(input.contains("Spiel speichern")){
                    saveGame = true;
                    System.out.println("Gib einen Dateinamen ein:");
                }
                else if (input.contains("choq")){
                    saveGame = true;
                    System.out.println("'enlIl 'enlIl");
                }
                else if(saveGame){
                    File selectedFile = new File("src/" + input);    //creates a new file instance
                    if(ai != null){
                        Parser.parserSaveCli(selectedFile, board, ai.turnNumber);
                    }else{
                        Parser.parserSaveCli(selectedFile, board, 0);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return saveGame;
    }

    private static void makeMoveFromInputAndOutputBeatenFigures(String input, String validInput) {
        languageIsTyped(input);
        if (input.matches(validInput)) {
            Move move = new Move(input);
            if (manuals.moveOfRightColour(move, board)) {
                board.applyMove(move);
                System.out.println(board.showBoard());
            } else {
                System.out.println("!" + settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "70")));
            }
        } else if (input.equals("beaten")) {
            String beatenString = "Beaten Figures";
            for (String beatenMinion : board.getBeaten()) {
                beatenString = String.join(",", beatenString, beatenMinion);
            }
            System.out.println(beatenString);
        }else {
            System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "76")));
        }
    }

    private static boolean isGermanTyped(String inputSetting){
        boolean germanTyped=false;
        if(inputSetting.matches("german") || inputSetting.contains("deutsch") || inputSetting.contains("chen")){
            germanTyped=true;
        }
        return germanTyped;
    }

    private static boolean isEnglishTyped(String inputSetting){
        boolean englishTyped=false;
        if(inputSetting.matches("english") || inputSetting.contains("englisch") || inputSetting.contains("Te ra'")){
            englishTyped=true;
        }
        return englishTyped;
    }

    private static boolean isKlingonTyped(String inputSetting){
        boolean klingonTyped=false;
        if(inputSetting.matches("klingon") || inputSetting.contains("klingonisch") || inputSetting.contains("tlhIngan")){
            klingonTyped=true;
        }
        return klingonTyped;
    }

    private static BufferedReader getBufferedReaderWhileInSettingsMode(boolean inSMode, boolean lGame) {
        boolean inSettingsMode = inSMode;
        boolean loadGame= lGame;
        BufferedReader brs = new BufferedReader(new InputStreamReader(System.in));
        while (inSettingsMode) {
            //languageNumber = "1";
            try {
                System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "63")));
                System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "64")));
                String inputSetting = brs.readLine();
                if (inputSetting.contains("exit")) {
                    inSettingsMode = false;
                }
                languageIsTyped(inputSetting);
                inSettingsMode = playerModeIsTyped(inputSetting, inSettingsMode);
                loadGame=loadingGame(inputSetting, loadGame);

                if(inputSetting.contains("txt")){
                    File file = new File("src/" + inputSetting);
                    System.out.println("file was read, now give it to parser");
                    Parser.parserLoadCli(file, board);
                    System.out.println("file was parsed");
                    loadGame = false;
                    if(board.getSettings().isAi_active()){
                        ai = board.getSettings().getAi();
                    }
                    inSettingsMode = false;
                }
                else {
                    //wait
                    System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "65")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return brs;
    }

    private static boolean playerModeIsTyped(String inputSetting, boolean inSMode) {
        boolean inSettingsMode = inSMode;
        if (isHumanTyped(inputSetting)) {
            settings.setAi_active(false);
            inSettingsMode = false;

        } else if (isAITyped(inputSetting)) {
            settings.setAi_active(true);
            inAISettingsMode = true;
            inSettingsMode = false;
        }
        return inSettingsMode;
    }

    private static void languageIsTyped(String inputSetting) {
        if (isGermanTyped(inputSetting)) {
            settings.getSettingsLanguage().setLanguageEnglish(false);
            settings.getSettingsLanguage().setLanguageGerman(true);
            settings.getSettingsLanguage().setLanguageKlingon(false);
            settings.getSettingsLanguage().setLanguageNumber("2");
        }
        if (isEnglishTyped(inputSetting)) {
            settings.getSettingsLanguage().setLanguageEnglish(true);
            settings.getSettingsLanguage().setLanguageGerman(false);
            settings.getSettingsLanguage().setLanguageKlingon(false);
            settings.getSettingsLanguage().setLanguageNumber("1");
        }
        if (isKlingonTyped(inputSetting)) {
            settings.getSettingsLanguage().setLanguageEnglish(false);
            settings.getSettingsLanguage().setLanguageGerman(false);
            settings.getSettingsLanguage().setLanguageKlingon(true);
            settings.getSettingsLanguage().setLanguageNumber("3");
        }
    }

    private static boolean loadingGame(String inputSetting, Boolean lGame){
        Boolean loadGame = lGame;
        if (inputSetting.contains("load game")){
            loadGame = true;

            System.out.println("Choose one of the following chess games to load by typing in the correct name of the file and clicking enter:");
        }
        else if(inputSetting.contains("Spiel laden")){
            loadGame = true;


            System.out.println("Waehle aus den folgenden Dateien durch Angabe des genauen Dateinamens und druecke enter:");
        }
        else if (inputSetting.contains("lIS")){
            loadGame = true;


            System.out.println("pa'QIS wa'DIch yI'el je lomqa'Vo'lI tlha'teywI'chegh teQ lI pupSIbI':");

        }
        if(loadGame){
            File folder = new File("src");
            File[] listOfFiles = folder.listFiles();

            for (File file: listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(file.getName());
                }
            }
        }
        return loadGame;
    }

    private static boolean isAITyped(String inputSetting) {
        boolean aiTyped=false;
        if(inputSetting.matches("ai") || inputSetting.contains("ki")|| inputSetting.contains("Qeq")){
            aiTyped=true;
        }
        return aiTyped;
    }

    private static boolean isHumanTyped(String inputSetting) {
        boolean humanTyped= false;
        if(inputSetting.matches("human") || inputSetting.contains("mensch") || inputSetting.contains("loD")){
            humanTyped=true;
        }
        return humanTyped;
    }

    private static BufferedReader getBufferedReaderAndMakeAIMoves(String languageNumber) {
        String output;
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
        return br;
    }

}
