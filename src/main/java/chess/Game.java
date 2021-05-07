package chess;

import chess.model.Minions;
import chess.model.Position;
import chess.model.*;

import java.util.ArrayList;


public class Game {
    private Minions[][] board=new Minions[8][8]; //feldgröße
    private char[] officerline = "RNBQKBNR".toCharArray();
    private char[] frontline = "PPPPPPPP".toCharArray();
    private Manuals basicManuals = new Manuals(this);
    private ArrayList<Minions>  beatenMinions = new ArrayList<>();

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


    public void updateBoard(Vector currentMove) {
        Minions selectedMinion = board[currentMove.getOrigin().getHorizont()][currentMove.getOrigin().getVertical()];
        ArrayList<Position> updatedPositions = basicManuals.updateValidPositions(currentMove.getOrigin(), selectedMinion.isBlack(), selectedMinion.getMinion_type());
        selectedMinion.setPosition_valid(updatedPositions);
        updatedPositions.forEach(position -> {
            if (position.getHorizont() == currentMove.getNextPos().getHorizont() && position.getVertical() == currentMove.getNextPos().getVertical()) {
                System.out.println("Valid Move");
                selectedMinion.setCurrentPosition(currentMove.getNextPos());
                Minions oldMinion = board[currentMove.getNextPos().getHorizont()][currentMove.getNextPos().getVertical()];
                if (oldMinion != null) {
                    this.beatenMinions.add(oldMinion);
                }
                board[currentMove.getNextPos().getHorizont()][currentMove.getNextPos().getVertical()] = selectedMinion;
                board[currentMove.getOrigin().getHorizont()][currentMove.getOrigin().getVertical()] = null;
            }
        });
    }

    public Minions[][] getBoard() { return board; }

    public boolean checkPosition(Position pos) {
        try {
            if (board[pos.getHorizont()][pos.getVertical()] == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}