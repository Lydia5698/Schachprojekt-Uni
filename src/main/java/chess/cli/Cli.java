package chess.cli;

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

    /**
     * The entry point of the console input application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        boolean simple = Arrays.asList(args).contains("--simple");
        board.setSimple(simple);
        String validInput = "[a-h][1-8]-[a-h][1-8]\\D?"; // Sind eingaben nach e2-e4 egal? Also als bsp: e2-e4uiei soll trotzdem den move e2-e4 ausführen?

        String output = board.showBoard();
        System.out.println(output);
        while (!board.isGameEnd()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Move");
            if (board.isBlackIsTurn()) {
                System.out.print(" for black\n");
            } else {
                System.out.print(" for white\n");
            }
            try {
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
