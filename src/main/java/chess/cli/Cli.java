package chess.cli;


import chess.model.Board;
import chess.model.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cli {
    protected static Board board = new Board();

    public static void main(String[] args) {

        while (true) {
            String output = board.showBoard();
            System.out.println(output);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Move\n");
            try {
                String input = br.readLine();
                Move move = new Move(input);
                board.applyMove(move);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

}