package chess;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * We are suppressing the PMD in this one, because we are setting the values of the Dictionary and it is unreasonable
 * to make many methods just to set the values. The values are always the same so we dont think that it is necessary
 * to make many methods

 * This class has Dictionary for the different Languages
 *
 *  @author Lydia Günther
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
        setDicKlingon();
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
        dic.put(203, "Flags/Klingon.png");
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
                "Bei diesem Spielmodus kannst du mit deinen Freund/-innen übers Netzwerk spielen. Um das Spiel zu starten muss eine Person das Spiel hosten dieser wird dann die Farbe schwarz spielen" +
                "und muss die CheckBox Spiel mit schwarz auswählen." +
                "Die andere Person muss dem Spiel beitreten und wird dann weiß spielen.\n" +
                "Außerdem muss der Spieler die IP-Addresse des Gegners eintagen." + "\n" +
                "\n" +
                "Spiel Laden\n"+
                "Um ein Spiel zu laden, musst du auf den Button Spiel laden klicken. Danach öffnet sich dein Datei-Explorer und du kannst eine zuvor gespeicherte Datei laden.\n"+
                "Save Game\n"+
                "Um ein Spiel zu speichern musst du während des Spiels auf die Diskette neben der Flagge klicken. Danach wird dein Datei-Explorer geöffnet und du kannst dir aussuchen,\n " +
                "wie die Datei heißen und wo sie gespeichert werden soll. Um das Spiel später auch auserhalb der Gui starten zu können muss der vorgeählte Ordner zum speichern ausgewählt werden.\n"+
                "Dieses Schachspiel folgt den Allgemeinen Regeln des deutschen Schachbundes."); // Anleitung Text
        dic.put(242, "Anleitung");
        // promote
        dic.put(250, "Wähle eine Figur um deinen Pawn zu befördern");
        // colourSelect
        dic.put(260, "Wähle eine Farbe um gegen die KI zu spielen");
        dic.put(261, "Schwarz");
        dic.put(262, "Weiß");
        //Cli colour Select
        dic.put(263, "Du befindest dich im Einstellungsmenue. Um die Sprache zu aendern, gib 'deutsch', 'klingonisch' oder 'englisch' ein, wenn du das Menue verlassen möchtest, gib 'exit' ein. Aber zuerst musst du dich entscheiden.");
        dic.put(264, "Willst du gegen einen Menschen spielen, tippe 'mensch', willst gegen eine KI spielen, tippe 'ki', möchtest du uebers Netzwerk spielen, tippe 'netzwerk':");
        dic.put(265, "Bitte ueberprüfe deine Eingabe");
        dic.put(266, "Welche Farbe moechtest du Spielen?, 'schwarz' or 'weiss'?");
        dic.put(267, "Du spielst schwarz, die KI wird weiss spielen.");
        dic.put(268, "Du spielst weiß, die KI wird schwarz spielen.");
        dic.put(269, "Bitte waehle eine Farbe.");
        // move not allowed
        dic.put(270, "Dieser Zug ist nicht erlaubt");
        dic.put(271, "Dieser Zug ist nicht erlaubt");
        //AI
        dic.put(272, "Die KI zieht: ");
        dic.put(273, "Gib einen Zug ein");
        dic.put(274, " für Schwarz\n");
        dic.put(275, " für Weiss\n");
        dic.put(276, "!Ungueltiger Zug");
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
        dic.put(111, "PvP");
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
                "In this game mode you can play with your friends over the network. In order to start the game a person has to host the game - this Person will then play the color black" +
                "and must check the CheckBox Play with Black" + "\n" +
                "The other person has to join the game and will then play white.\n" +
                "In addition, the player must enter the opponent's IP address." + "\n" +
                "\n" +
                "Load Game\n"+
                "To load a game, click the Load Game button. Your file explorer will then open and you can load a previously saved file.\n"+
                "Save Game\n"+
                "To save a game you have to click on the floppy disk next to the flag during the game. Then your file explorer will open and you can choose what the file should be called and where it will be saved.\n" +
                "In order to be able to start the game later outside of the GUI, the preselected folder needs to be selected.\n"+
                "This chess game follows the general rules of the German Chess Federation."); // Anleitung Text
        dic.put(142, "Manual");
        // promote
        dic.put(150, "Choose a Figure to Promote your Pawn");
        // colourSelect
        dic.put(160, "Which color do you want to play against the AI?");
        dic.put(161, "Black");
        dic.put(162, "White");
        //Cli colour Select
        dic.put(163, "You are in the settings menu. To change the Language type 'german', 'klingon' or 'english' if you want to exit the menu type 'exit'. If you want to save a game type 'save game'. But first you have to choose.");
        dic.put(164, "Do you want to play against a human, type 'human', want to play against an ai, type 'ai':");
        dic.put(165, "Please correct your input");
        dic.put(166, "What colour do you want to play, 'black' or 'white'?");
        dic.put(167, "You play black, the ai plays white.");
        dic.put(168, "You play white, the ai plays black.");
        dic.put(169, "Please choose a colour.");
        // move not allowed
        dic.put(170, "Move not allowed");
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

    private void setDicKlingon() {
        // Start Screen (klingon latin)
        dic.put(300, "che'ron");
        dic.put(301, "bI'reS");
        dic.put(302, "ghojmoH");
        dic.put(303, "Flags/UnitedKingdomFlag.png");
        dic.put(304, "lIS");
        // GameChoice
        dic.put(310, "mInDu'");
        dic.put(311, "motlh");
        dic.put(312, "ngeb");
        dic.put(313, "rar");
        // active Game
        dic.put(320, "Duh");
        // options
        dic.put(330, "chap");
        dic.put(331, "Duh");
        dic.put(332, "'ach nav QIbHa'");
        dic.put(333, "SepDaj veng jIvHa'wI'");
        dic.put(334, "muqIchdaj Sovbe'chugh, vaj DaH ghotvam");
        dic.put(335, "HoSchoHpu' chepmoHlu'");
        dic.put(336, "bertlham");
        // manual
        dic.put(340, "chap");
        dic.put(341, "ghotvam qorDu' ghotghom. " +
                "vaj, jIHvaD muvchuqlaHmo' tlhoghvam.\n" +
                "\n" +
                "Duh\n" +
                "'ach qaSpu'DI' malja', Qu' cho'mo'. Qo'noS taghchoH Hujvam'e'. " +
                "'ach Hegh QorghlaHbe'bogh QoylaHbe' 'arqon rop'e' 'oghlaHbe' QorghwI', sIbI' 'oH rop ghItlh'e' SIbI' 'oH, 'ach pIm ngoDmey'e' luchenmoHlu'. " +
                "lojmIt DeghwI' 'ach Qoch 'el QoylaHbe' DeghwI' Qoy. wamwI'ghomHom ra'wI' lumuvHa'chuq. " +
                "ngoQvammo' QoQ Huj poS. veng wa'DIchDaq nuvpu' mIv'a' tIn puS.\n" +
                "\n" +
                "qaStaHvIS DaraPbe'bogh\n" +
                "\n" +
                "bI'reS\n" +
                "yapchoH loDnI'wI' DaSovchoHQo'\n" +
                "\n" +
                "motlh\n" +
                "'umbe'law' ghItlhvam. 'ach Hajqu'choH. vItlhobmeH jIH maSuvjaj. \n" +
                "\n" +
                "ngeb\n" +
                "Qo'noS luchenmoHlaHchu' 'e' Datu' 'e' yIchaw'. QumwI'lIjDaq yIloSmoH! chaHvaD Humanpu' chaH\n" +
                "\n" +
                "rar\n" +
                "ngaghwI'na' Dechbogh qo'mey puS. law'ta' ropmo' qechmaj boQ'a' neH.\n" +
                "QuvvamDaq QottaHghach mIw wIje'laHbe'. latlh lut vIlaD. jagh Datuqchugh vaj mu'mey Dalo'taHvIS" + "\n" +
                "\n" +
                "QI'yaH\n"+
                "ghIlghameSvaD jatlh ghogh DapIHmoHchugh, vaj ghIlghameS chaq nIvpu'+ \\ bogh yablIj wanI'meyvam'e'.\n"+
                "qeylIS mInDu'\n"+
                "wo'Daq janvam chovutqa' wIje'meH matlhong wIneH. 'ej chaw'lIjvaD yap nIvqu' chaw'r upchu'.\n ghIlghameSvaD jatlh QOrwI'pu' lutu'lu' 'e' vIpIH.\n"+
                "ghIlghameSvaD ghIlghameS 'e' chaw' ghIlghameS"); // Anleitung Text
        dic.put(342, "ghojmoH");
        // promote
        dic.put(350, "tlhIngan maH");
        // colourSelect
        dic.put(360, "QI'yaH, chaw'wI' quvHa'mo' QIch'e'");
        dic.put(361, "qIj");
        dic.put(362, "chIS");
        //Cli colour Select
        dic.put(363, "naDev DaDalaH. maj, jItlheDchugh, vaj tera'Nganpu' bIH, 'e' vIghoj. yablIjDaq ghob Dapabchugh, vaj yImuvHa'.");
        dic.put(364, "bImuSHa'chugh, pagh, bIqemtaHvIS,:");
        dic.put(365, "tIqlIj yIlo' 'e' DaHar");
        dic.put(366, "nuq DaneH, 'qIj' ghap 'chIS'?");
        dic.put(367, "chu' qIj, ngeb moj chIS.");
        dic.put(368, "chu' chIS, ngeb moj qIj.");
        dic.put(369, "DuqIp 'oH.");
        // move not allowed
        dic.put(370, "qechbe'meH mIwvam'e'");
        dic.put(371, "qechbe'meH mIwvam'e'");
        //AI
        dic.put(372, "ngeb vIH: ");
        dic.put(373, " vIH ngeQ");
        dic.put(374, " qIj\n");
        dic.put(375, " chIS\n");
        dic.put(376, "!Qagh vIH");
        // check
        dic.put(380, "qIj che'ron");
        dic.put(381, "chIS che'ron");
        dic.put(382, "che'ron!");
        dic.put(383, "vaj qoghlIj puS");
        dic.put(384, "bIQaHmeH qoH DaSovbe'. ngaQmoH nuvpu', ghItlhpa' yuQmaj ngoQ: ");
        // checkMate
        dic.put(390, "qIj choz");
        dic.put(391, "chIS chot");
        dic.put(392, "chot!");
        dic.put(393, "bertlham");
        dic.put(394, "tlhol");
        // Network Game
        dic.put(395,"qa'vaQ 'eH, chav Data");
        dic.put(396,"qa'vaQ muv");
        dic.put(397,"qa'vaQ qIj");
        dic.put(398,"SoQ");
        dic.put(399,"lu'");

    }
}

