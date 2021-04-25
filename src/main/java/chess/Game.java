package chess;

import chess.model.Minions;
import chess.model.Position;
import chess.model.*;

public class Game {
    private Minions[][] board=new Minions[8][8]; //feldgröße
    private char[] officerline = "RNBQKBNR".toCharArray();
    private char[] frontline = "PPPPPPPP".toCharArray();

    public Game(boolean black){
        initHorizont(0, !black);
        initHorizont(1, !black);
        initHorizont(6, black);
        initHorizont(7, black);
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
                case 'K': board[horizont][i]=new King(new Position(i,horizont), black);
                break;
                default: board[horizont][i]=new Pawn(new Position(i,horizont), black);
                break;
            }
        }
    }
    public void updaterBoard(String userCommands){}
    public Minions[][] getBoard() { return board; }
}
