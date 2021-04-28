package chess.model;

import chess.model.Figures.*;

public class Board {
    private Minions[][] board=new Minions[8][8]; //feldgröße
    private char[] officerline = "RNBQKBNR".toCharArray();
    private char[] frontline = "PPPPPPPP".toCharArray();

    public Board(){
        initHorizont(0, true);
        initHorizont(1, true);
        initHorizont(6, false);
        initHorizont(7, false);
    }

    public String showBoard() {
        StringBuilder output = new StringBuilder();
        int horizontNum = 8;
        for (Minions[] horizont : board) {
            output.append(horizontNum).append(" ");
            horizontNum--;
            for (Minions minions : horizont) {
                if (minions != null) {
                    output.append(minions.print_minions()).append(" ");
                } else {
                    output.append("  ");
                }
            }
            output.append("\n");
        }
        output.append("  a b c d e f g h\n");
        return output.toString();
    }

    private void initHorizont(int horizont, boolean black) {
        char[] tmp = frontline;
        if(horizont == 0 || horizont == 7){
            tmp = officerline;}
        for (int i=0;i<8;i++){
            switch (tmp[i]){
                case 'R': board[horizont][i]=new Rook(new Position(i,horizont), black);
                break;
                case 'N': board[horizont][i]=new Knight(new Position(i,horizont), black);
                break;
                case 'B': board[horizont][i]=new Bishop(new Position(i,horizont), black);
                break;
                case 'Q': board[horizont][i]=new Queen(new Position(i,horizont), black);
                break;
                case 'K': board[horizont][i]=new Bishop.King(new Position(i,horizont), black);
                break;
                default: board[horizont][i]=new Pawn(new Position(i,horizont), black);
                break;
            }
        }
    }

    public Minions[][] getBoard() { return board; }
}
