package chess.model;

import chess.model.Figures.*;

import java.util.Arrays;
import java.util.List;

public class Board {
    Cell[][] checkerBoard = new Cell[8][8]; //feldgröße
    private char[] officerline = "RNBQKBNR".toCharArray();
    private char[] frontline = "PPPPPPPP".toCharArray();
    static List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    public Board() {
        initHorizont(0, true);
        initHorizont(1, true);
        initHorizont(2, false);
        initHorizont(3, false);
        initHorizont(4, false);
        initHorizont(5, false);
        initHorizont(6, false);
        initHorizont(7, false);
    }

    public String showBoard() {
        StringBuilder output = new StringBuilder();
        int horizontNum = 8;
        for (Cell[] horizont : checkerBoard) {
            output.append(horizontNum).append(" ");
            horizontNum--;
            for (Cell cell : horizont) {
                output.append(cell.toString()).append(" ");
            }
            output.append("\n");
        }
        output.append("  ").append(String.join(" ", columns)).append("\n");
        return output.toString();
    }

    private void initHorizont(int horizont, boolean black) {
        char[] tmp = frontline;
        if (horizont == 0 || horizont == 7) {
            tmp = officerline;
        }
        for (int i = 0; i < 8; i++) {
            switch (tmp[i]) {
                case 'R':
                    checkerBoard[horizont][i] = new Cell(new Rook(black));
                    break;
                case 'N':
                    checkerBoard[horizont][i] = new Cell(new Knight(black));
                    break;
                case 'B':
                    checkerBoard[horizont][i] = new Cell(new Bishop(black));
                    break;
                case 'Q':
                    checkerBoard[horizont][i] = new Cell(new Queen(black));
                    break;
                case 'K':
                    checkerBoard[horizont][i] = new Cell(new King(black));
                    break;
                default:
                    checkerBoard[horizont][i] = new Cell(new Pawn(black));
                    break;
            }
        }
        if (horizont >= 2 && horizont <= 5) {
            checkerBoard[horizont] = emptyCells();
        }
    }

    private Cell[] emptyCells() {
        Cell[] row = new Cell[8];
        for (int i = 0; i < 8; i++) {
            row[i] = new Cell(null);
        }
        return row;
    }

    public void applyMove(Move move) { // check for valid move
        CellIndex startIndex = cellIndexFor(move.getStart());
        Cell startCell =  checkerBoard[startIndex.row][startIndex.column]; //getCell(move.getStart());
        Minions minion = startCell.getMinion();

        CellIndex endIndex = cellIndexFor(move.getEnd());
        Cell endCell =  checkerBoard[endIndex.row][endIndex.column];

        //startCell.setMinion(null);
        //endCell.setMinion(minion);

        // check valid move
        if(minion.validMove(startIndex, endIndex)){
            startCell.setMinion(null);
            endCell.setMinion(minion);
        }
        else{
            System.out.println("!Move not allowed");
        }
    }

    CellIndex cellIndexFor(String stringIndex) {
        String startColumn =  stringIndex.substring(0, 1);
        String startRowString = stringIndex.substring(1, 2);
        return new CellIndex(8-Integer.parseInt(startRowString), columns.indexOf(startColumn));
    }







}
