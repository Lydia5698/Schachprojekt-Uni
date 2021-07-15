package chess.model;

import chess.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parserLoadCli() {


    }

    @Test
    void parserSaveCliHH() {
        //make board
        Board bo = new Board();
        Settings settings = new Settings();
        bo.setSettings(settings);
        //make moves
        String[] ml = {"e2-e4", "e7-e5", "g1-f3", "b8-c6", "f1-c4", "f8-e7"};
        for(String m:ml){
            Move mov = new Move(m);
            bo.applyMove(mov);
        }
        //getsettings
        //save file
        File selectedFile = new File("src/testFile.txt");    //creates a new file instance
        Parser.parserSaveCli(selectedFile, bo, 0);
        //load file/read file line for line
        try{
            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            bo.setSettings(bo.getSettings());
            int counter=0;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                if(counter<ml.length){
                    assertTrue(ml[counter].contains(strLine));
                }
                else if(strLine.contains("AI-active")||strLine.contains("AI-colour")){
                    String[] splitI = strLine.split(" ");
                    boolean bool;
                    if(splitI[1].contains("t")){
                        bool = true;
                    }else{
                        bool = false;
                    }
                    assertEquals(bo.getSettings().isAi_active(), bool);
                }
                else if(strLine.contains("AI-turnnumber")){
                    String[] splitI = strLine.split(" ");
                    int number = Integer.parseInt(splitI[1]);
                    assertEquals(bo.getSettings().getAi().turnNumber, number);
                }
                counter +=1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parserSaveCliAI() {
        //make board
        Board bo = new Board();
        Settings settings = new Settings();
        bo.setSettings(settings);
        AI ai = new AI(true);
        List<Move> openingMoveL = ai.aiOpening.getOpeningMoveList();
        //make moves
        List<String> ml = new ArrayList<>();
        ml.add("e2-e4");
        ml.add(openingMoveL.get(0).getStart()+"-"+openingMoveL.get(0).getEnd());
        ai.increaseTurnNumber();
        ml.add("g1-f3");
        ml.add(openingMoveL.get(1).getStart()+"-"+openingMoveL.get(1).getEnd());
        ai.increaseTurnNumber();
        ml.add("f1-c4");
        for(String m:ml){
            Move mov = new Move(m);
            bo.applyMove(mov);
        }
        //setsettings
        settings.setAi_active(true);
        settings.setAi_colour(true);
        ai.setTurnNumber(2);
        bo.setSettings(settings);
        //save file
        File selectedFile = new File("src/testFileAI.txt");    //creates a new file instance
        Parser.parserSaveCli(selectedFile, bo, ai.turnNumber);
        //load file/read file line for line
        try{
            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            bo.setSettings(bo.getSettings());
            int counter=0;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                if(counter<ml.size()){
                    Assertions.assertTrue(ml.get(counter).contains(strLine));
                }
                else if(strLine.contains("|")){
                    assertTrue(strLine.contains("|"));
                }
                else if(strLine.contains("AI-active")||strLine.contains("AI-colour")){
                    String[] splitI = strLine.split(" ");
                    boolean bool = false;
                    if(splitI[1].contains("t")){
                        bool = true;
                    }
                    assertEquals(bo.getSettings().isAi_active(), bool);
                }
                else if(strLine.contains("AI-turnnumber")){
                    String[] splitI = strLine.split(" ");
                    int number = Integer.parseInt(splitI[1]);
                    assertEquals(ai.turnNumber, number);
                }
                counter +=1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}