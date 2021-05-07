package chess.cli;


import chess.Game;
import chess.Settings;
import chess.model.Minions;
import chess.model.Position;
import chess.model.Vector;

import java.util.Scanner;

public class Cli {
    protected static Game game = new Game(false); //weil mit weiß begonnen werden soll
    private static Settings settings = new Settings();

    public static void main(String[] args) {
        System.out.println("Qapla' Klingon Player 1\nPvP menu  1\nPvE menu  2");
        Scanner scan = new Scanner(System.in);
        int menu = scan.nextInt();
        if (menu == 1) {    //optionsmenu settings
            settings.options(0, true);
        } else {
            settings.options(0, false);
        }
        while (true) {
            print_Board();
            String userInput="";
            while (scan.hasNextLine()) {
                userInput=scan.nextLine();
                if(!userInput.equals("")){
                    break;
                }
            }
            Vector currentMove=convertInput(userInput);
            if(currentMove.getOrigin().getHorizont() >=0){
                game.updateBoard(currentMove);
            }
        }
    }

    private static Vector convertInput(String unserInput){
        String[] inputPart=unserInput.split("-");
        Position origin = new Position(-1,-1);
        Position nextPos = new Position(-1,-1);
        int posHorizont = 0, posVertical = 0;
        if(inputPart[0].equals("beaten")) {
            System.out.println("beaten minions-list:");
        }
        else if(inputPart.length ==2){
                for(int i=0;i<2; i++){
                    switch (inputPart[i].charAt(0)){
                        case 'a': posHorizont=0; break;
                        case 'b': posHorizont=1; break;
                        case 'c': posHorizont=2; break;
                        case 'd': posHorizont=3; break;
                        case 'e': posHorizont=4; break;
                        case 'f': posHorizont=5; break;
                        case 'g': posHorizont=6; break;
                        case 'h': posHorizont=7; break;
                        default:
                        System.out.println("!Invalid Move");
                    }
                    switch (inputPart[i].charAt(1)){
                        case '8': posHorizont=0; break;
                        case '7': posHorizont=1; break;
                        case '6': posHorizont=2; break;
                        case '5': posHorizont=3; break;
                        case '4': posHorizont=4; break;
                        case '3': posHorizont=5; break;
                        case '2': posHorizont=6; break;
                        case '1': posHorizont=7; break;
                        default:
                        System.out.println("!Invalid Move");
                    }
                    if(i==0){
                        origin.setHorizont(posHorizont);
                        origin.setVertical(posVertical);
                    }
                    else{
                        nextPos.setHorizont(posHorizont);
                        nextPos.setVertical(posVertical);
                    }
                }
        }
        else{
            System.out.println("!Invalid Move");
        }
        return new Vector(origin, nextPos);
    }




    private static void print_Board() {
        int horizontNum = 8;
        for (Minions[] horizont : game.getBoard()) {
            System.out.print(horizontNum + " ");
            horizontNum--;
            for (Minions minions : horizont) {
                if (minions != null) {
                    System.out.print(minions.print_minions() + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("  a b c d e f g h \n");
    }
}