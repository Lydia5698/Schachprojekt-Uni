package chess;

import java.util.Dictionary;
import java.util.Hashtable;

public class Language { //TODO unterklasse für die deutsche Sprache und oberklasse and english?
    Dictionary<Integer, String> dic = new Hashtable<>();

    public Language(){
        setDicGerman();
        setDicEnglish();
    }
    public Dictionary<Integer, String> getDic() {
        return dic;
    }

    // German: all numbers with first number 2 for German, second number for the scene and last number for the texts from left to right and up to bottom
    private void setDicGerman(){
        // Start Screen
        dic.put(200, "Schach");
        dic.put(201, "Spiel start");
        dic.put(202, "Anleitung");
        // GameChoice
        dic.put(210, "Wähle eine Spielart aus");
        dic.put(211, "Normales Spiel");
        dic.put(212, "Gegen die KI");
        dic.put(213, "Netzwerkspiel");
        // active Game
        dic.put(220, "Optionen");
        // options
        dic.put(230, "Zurück");
        dic.put(231, "Optionen");
        dic.put(232, "Zeigt mögliche Züge an");
        dic.put(233, "Zeigt an ob der König im Schach steht");
        dic.put(234, "Kein Mehrfachklicken erlaubt");
        dic.put(235, "Das Spielfeld wird automatisch nach jedem Zug gedreht");
        dic.put(236, "Spiel beenden");
        // manual
        dic.put(240, "Zurück");
        dic.put(241, "Zurück"); // Anleitung Text
        // promote
        dic.put(250, "Wähle eine Figur um deinen Pawn zu befördern");
        // colourSelect
        dic.put(260, "Wähle eine Farbe um gegen die KI zu spielen");
        dic.put(261, "Schwarz");
        dic.put(262, "Weiß");
        // move not allowed
        dic.put(270, "Dieser Zug ist nicht erlaubt");
        // check
        dic.put(280, "Schwarz steht im Schach");
        dic.put(281, "Weiß steht im Schach");
        // checkMate
        dic.put(290, "Schwarz steht im Schachmatt");
        dic.put(291, "Weiß steht im Schachmatt");
    }

    // English: all numbers with first number 1 for English, second number for the scene and last number for the texts from left to right and up to bottom
    private void setDicEnglish(){
        // Start Screen
        dic.put(100, "Chess");
        dic.put(101, "Game Start");
        dic.put(102, "Manual");
        // GameChoice
        dic.put(110, "Choose your Game");
        dic.put(111, "co-op");
        dic.put(112, "Against AI");
        dic.put(113, "Network Game");
        // active Game
        dic.put(120, "Options");
        // options
        dic.put(130, "Back");
        dic.put(131, "Options");
        dic.put(132, "Highlight Possible Moves");
        dic.put(133, "Show if King is in Check");
        dic.put(134, "You can only click ones one Figure");
        dic.put(135, "Rotate the Chessboard automatically");
        dic.put(136, "End Game");
        // manual
        dic.put(140, "Back");
        dic.put(141, "Zurück"); // Anleitung Text
        // promote
        dic.put(150, "Choose a Figure to Promote your Pawn");
        // colourSelect
        dic.put(160, "Which color do you want to play against the AI?");
        dic.put(161, "Black");
        dic.put(162, "White");
        // move not allowed
        dic.put(170, "Move Not Allowed");
        dic.put(171, "The Move you just made is not allowed.");
        // check
        dic.put(180, "Black is in check");
        dic.put(181, "White is in check");
        // checkMate
        dic.put(190, "Black is in check Mate");
        dic.put(191, "White is in check Mate");
    }
}

