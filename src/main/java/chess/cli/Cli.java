package chess.cli;


import chess.model.Board;
import chess.model.Move;

public class Cli {
    protected static Board board = new Board();

    public static void main(String[] args) {
        while (true) {
            System.out.println(board.showBoard());
            String input = "e2-e4";
            Move move = new Move(input);
            board.applyMove(move);
            System.out.println(board.showBoard());
            break; //todo playerInput
        }
    }

}