package chess;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * We are suppressing the PMD in this one, because we are setting the values of the Dictionary and it is unreasonable
 * to make many methods just to set the values. The values are always the same so we dont think that it is necessary
 * to make many methods

 * This class has Dictionary for the different Languages
 */
@SuppressWarnings("PMD.NcssCount")
public class Language {
    final Dictionary<Integer, String> dic = new Hashtable<>();

    /**
     * Creates a new Language instance and sets the languages in the Dictionary
     */
    public Language() {
        setDicGerman();
        setDicEnglish();
    }

    public Dictionary<Integer, String> getDic() {
        return dic;
    }

    /**
     * German: first number 2 for German, second number for the scene and
     * last number for the texts from left to right and up to bottom
     */

    private void setDicGerman() {
        // Start Screen
        dic.put(200, "Schach");
        dic.put(201, "Spiel start");
        dic.put(202, "Anleitung");
        dic.put(203, "Flags/UnitedKingdomFlag.png");
        dic.put(204, "Spiel laden");
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
        dic.put(233, "Zeigt an, ob der König im Schach steht");
        dic.put(234, "Kein Mehrfachklicken erlaubt");
        dic.put(235, "Das Spielfeld wird automatisch nach jedem Zug gedreht");
        dic.put(236, "Spiel beenden");
        // manual
        dic.put(240, "Zurück");
        dic.put(241, "Die Figuren in diesem Schachspiel werden per click verschoben. " +
                "Das heißt, dass zuerst das Feld mit der Figur angeklickt werden muss und dann das Feld auf das die Figur ziehen soll.\n" +
                "\n" +
                "Optionen\n" +
                "Standardmäßig ist die Ausgabe ob jemand im Schach steht unterdrückt. Dies kann in den Optionen geändert werden. " +
                "Außerdem kann in den Optionen eingestellt werden, ob die möglichen Züge der angeklickten Figur angezeigt werden sollen. " +
                "Die Rotation des Bordes kann auch in den hier aktiviert werden. Dann Rotiert das Board immer mit dem aktiven Spieler. " +
                "Die letzte Option ist das Mehrfachklicken zu verbieten. Dann kann der Spieler nur die Figur ziehen, die zuerst angeklickt wurde\n" +
                "\n" +
                "Die Optionen sind erreichbar sobald man das Spiel gestartet hat\n" +
                "\n" +
                "Spiel starten\n" +
                "Am Anfang jedes spiels kann man sich zwischen drei Spielarten entscheiden\n" +
                "\n" +
                "Normales Spiel\n" +
                "Hier wird ein normales Schachspiel gespielt. Es findet alles lokal auf deinem Computer statt. Also schnapp dir einen Freund/-in um diesen Spielmodus zu spielen. \n" +
                "\n" +
                "Spiel gegen die KI\n" +
                "Bei diesem Spielmodus kannst du gegen den Computer spielen. Du kannst vor dem Spiel wählen, ob welche Farbe du spielen möchtest. Der Computer wird dann die andere Farbe spielen.\n" +
                "\n" +
                "Netzwerkspiel\n" +
                "Bei diesem Spielmodus kannst du mit deinen Freund/-innen übers Netzwerk spielen. Um das Spiel zu starten muss eine Person das Spiel hosten und der andere muss dem Spiel beitreten\n" +
                "Derjenige, der das Spiel hostet kann sich aussuchen, welche Farbe er spielen möchte. Der andere Spieler muss die IP-Addresse des Gegners eintagen." + "\n" +
                "\n" +
                "Dieses Schachspiel folgt den Allgemeinen Regeln des deutschen Schachbundes."); // Anleitung Text
        dic.put(242, "Anleitung");
        // promote
        dic.put(250, "Wähle eine Figur um deinen Pawn zu befördern");
        // colourSelect
        dic.put(260, "Wähle eine Farbe um gegen die KI zu spielen");
        dic.put(261, "Schwarz");
        dic.put(262, "Weiß");
        //Cli colour Select
        dic.put(263, "Du befindest dich im Einstellungsmenü. Um die Sprache zu ändern, gib 'deutsch' oder 'englisch' ein, wenn du das Menü verlassen möchtest, gib 'exit' ein. Aber zuerst musst du dich entscheiden.");
        dic.put(264, "Willst du gegen einen Menschen spielen, tippe 'mensch', willst gegen eine KI spielen, tippe 'ki':");
        dic.put(265, "Bitte überprüfe deine Eingabe");
        dic.put(266, "Welche Farbe möchtest du Spielen?, 'schwarz' or 'weiß'?");
        dic.put(267, "Du spielst schwarz, die KI wird weiß spielen.");
        dic.put(268, "Du spielst weiß, die KI wird schwarz spielen.");
        dic.put(269, "Bitte wähle eine Farbe.");
        // move not allowed
        dic.put(270, "Dieser Zug ist nicht erlaubt");
        dic.put(271, "Dieser Zug ist nicht erlaubt");
        //AI
        dic.put(272, "Die KI zieht: ");
        dic.put(273, "Gib einen Zug ein");
        dic.put(274, " für Schwarz\n");
        dic.put(275, " für Weiß\n");
        dic.put(276, "!Ungültiger Zug");
        // check
        dic.put(280, "Schwarz steht im Schach");
        dic.put(281, "Weiß steht im Schach");
        dic.put(282, "Schach!");
        dic.put(283, "Kein Mehrfachklicken");
        dic.put(284, "Mehrfachklicken ist nicht erlaubt. Bitte bewege den zuerst angeklickte Figur auf dem Feld: ");
        // checkMate
        dic.put(290, "Schwarz steht im Schachmatt");
        dic.put(291, "Weiß steht im Schachmatt");
        dic.put(292, "Schachmatt!");
        dic.put(293, "Das Spiel ist vorbei");
        dic.put(294, "Patt");
        // Network Game
        dic.put(295,"Spiel hosten");
        dic.put(296,"Spiel beitreten");
        dic.put(297,"Spiel mit schwarz");
        dic.put(298,"IP-Addresse");
        dic.put(299,"Bestätigen");

    }

    /**
     * English: first number 1 for English, second number for the scene and last number
     * for the texts from left to right and up to bottom
     */
    private void setDicEnglish() {
        // Start Screen
        dic.put(100, "Chess");
        dic.put(101, "Game Start");
        dic.put(102, "Manual");
        dic.put(103, "Flags/GermanFlag.png");
        dic.put(104, "Load Game");
        // GameChoice
        dic.put(110, "Choose your Game");
        dic.put(111, "pvp");
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
        dic.put(141, "The pieces in this chess game are moved with a click.\n" +
                "This means that first the field with the figure must be clicked and then the field on which the figure is to be dragged.\n" +
                "  \n" +
                "Options\n" +
                "By default, the output whether someone is in check is suppressed. This can be changed in the options.\n" +
                "In addition, you can set in the options whether the possible moves of the clicked figure should be displayed.\n" +
                "The rotation of the board can also be activated here. Then the board always rotates with the active player.\n" +
                "The last option is to prohibit multiple clicks. Then the player can only move the figure that was clicked first\n" +
                "\n" +
                " The options are available as soon as you have started the game\n" +
                "\n" +
                "Start Game\n" +
                "At the beginning of each game you can choose between three types of game\n" +
                "\n" +
                "pvp\n" +
                "A normal game of chess is played here. It all takes place locally on your computer. So grab a friend to play this game mode.\n" +
                "\n" +
                "AgainstAI\n" +
                "In this game mode you can play against the computer. Before starting the game, you can choose which color you want to play. The computer will then play the other color.\n" +
                "\n" +
                "Network Game\n" +
                "In this game mode you can play with your friends over the network. To Play one must Host a Game. This Person can choose which colour he wants to play." + "\n" +
                "The second Player must Join a Game. He must enter the IP-Address of the Hosting Person" + "\n" +
                "\n" +
                "This chess game follows the general rules of the German Chess Federation."); // Anleitung Text
        dic.put(142, "Manual");
        // promote
        dic.put(150, "Choose a Figure to Promote your Pawn");
        // colourSelect
        dic.put(160, "Which color do you want to play against the AI?");
        dic.put(161, "Black");
        dic.put(162, "White");
        //Cli colour Select
        dic.put(163, "You are in the settings menu. To change the Language type 'german' or 'english' if you want to exit the menu type 'exit'. But first you have to choose.");
        dic.put(164, "Do you want to play against a human, type 'human', want to play against an ai, type 'ai':");
        dic.put(165, "Please correct your input");
        dic.put(166, "What colour do you want to play, 'black' or 'white'?");
        dic.put(167, "You play black, the ai plays white.");
        dic.put(168, "You play white, the ai plays black.");
        dic.put(169, "Please choose a colour.");
        // move not allowed
        dic.put(170, "Move Not Allowed");
        dic.put(171, "The Move you just made is not allowed.");
        // AI Cli
        dic.put(172, "AI moves: ");
        dic.put(173, "Enter Move");
        dic.put(174, " for black\n");
        dic.put(175, " for white\n");
        dic.put(176, "!Invalid move");
        // check
        dic.put(180, "Black is in check");
        dic.put(181, "White is in check");
        dic.put(182, "Check");
        dic.put(183, "No Double click");
        dic.put(184, "It is only allowed to move the first clicked Figure. Please move the Figure on: ");
        // checkMate
        dic.put(190, "Black is in check Mate");
        dic.put(191, "White is in check Mate");
        dic.put(192, "checkMate!");
        dic.put(193, "The Game has ended");
        dic.put(194, "Stalemate");
        // Network Game
        dic.put(195,"Host Game");
        dic.put(196,"Join Game");
        dic.put(197,"Play with Black");
        dic.put(198,"IP-Address");
        dic.put(199,"Submit");

    }
}

