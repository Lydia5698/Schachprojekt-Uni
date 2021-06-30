package chess;

public class Language {

    public String check(){ //gibt black is turn mit?
        if(true){//board.isBlackIsTurn()){
            return ("Schwarz steht im Schach");
        }
        else {
            return ("Weiß steht im Schach");
        }

    }

    public String checkMate(){
        if(true){//board.isBlackIsTurn()){
            return ("Schwarz steht im Schachmatt");
        }
        else {
            return ("Weiß steht im Schachmatt");
        }
    }

    public String moveNotAllowed(){
        return "Dieser Zug ist nicht erlaubt";
    }


    public String options(){
        return "Optionen";
    }

    public String manuals(){
        return "Anleitung";
    }

    public String credits(){
        return "?";
    }

    public String promote(){
        return "Wähle eine Figur um deinen Pawn zu befördern";
    }

    public String gameStart(){
        return "Spiel start";
    }

    public String exit(){
        return "Programm schließen";
    }

    public String endGame(){
        return "Spiel beenden";
    }

    public String back(){
        return "zurück";
    }

    public String GameChoice(){
        return "Wähle eine Spielart aus";
    }

    public String coop(){
        return "Normales Spiel";
    }

    public String againstAI(){
        return "Gegen die KI";
    }

    public String networkGame(){
        return "Netzwerkspiel";
    }

    public String possibleMovesOption(){
        return "Zeigt mögliche Züge an";
    }

    public String showCheckOption(){
        return "Zeigt an ob der König im Schach steht";
    }

    public String figureOption(){
        return "Kein Mehrfachklicken erlaubt";
    }

    public String rotateOption(){
        return "Das Spielfeld wird automatisch nach jedem Zug gedreht";
    }

    public String colourSelect(){
        return "Wähle eine Farbe um gegen die KI zu spielen";
    }

    public String whiteOption(){
        return "Weiß";
    }

    public String blackOption(){
        return "Schwarz";
    }
    // TODO Manuals übersetzten

}

