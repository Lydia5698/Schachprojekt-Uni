package chess.cli;


import chess.Game;
import chess.Settings;
import chess.model.Minions;

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
            break; //todo playerInput
        }
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