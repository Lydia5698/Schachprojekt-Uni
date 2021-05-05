package chess.cli;


import chess.model.Board;
import chess.model.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cli {
    protected static Board board = new Board();

    public static void main(String[] args) {
        String validInput = "[a-h][1-8]-[a-h][1-8].*"; // Sind eingaben nach e2-e4 egal? Also als bsp: e2-e4uiei soll trotzdem den move e2-e4 ausführen?

        String output = board.showBoard();
        System.out.println(output);
        while (true) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Move\n");
            try {
                String input = br.readLine();
                if(input.matches(validInput)){
                    Move move = new Move(input);
                    board.applyMove(move);
                    output = board.showBoard();
                    System.out.println(output);
                }
                else if(input.equals("quit")){
                    break;
                }
                else{
                    System.out.println("!Invalid move");
                }


            } catch (IOException e) {
                e.printStackTrace();
                break;

            }
        }
    }

}