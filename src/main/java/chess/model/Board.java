package chess.model;

import chess.model.figures.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Board is a Checkerboard with Cells where Minions can be in it
 *
 * @author Lydia Günther
 * @see Cell
 * @see Minion
 */

public class Board {
    Cell[][] checkerBoard = new Cell[8][8]; //feldgröße
    public Manuals manuals = new Manuals();
    private char[] officerline = "RNBQKBNR".toCharArray();
    private char[] frontline = "PPPPPPPP".toCharArray();
    static List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
    public List<String> beaten = new ArrayList<>();
    private boolean blackIsTurn = false;
    private boolean gameEnd = false;
    private boolean simple = false;

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

    /**
     * Adds empty Cells to the Checkerboard for the Cells on a Chessboard without Minions at the beginning of the Game
     *
     * @return a Cell array row with empty Cells
     */
    private Cell[] emptyCells() {
        Cell[] row = new Cell[8];
        for (int i = 0; i < 8; i++) {
            row[i] = new Cell(null);
        }
        return row;
    }

    /**
     * ApplyMove gets the start and end Index for the Cells from the move
     * Gets the Cell from the board with the indices and gets the Minions or the Empty Cell from the Cell
     * Checks if the minion from the start Cell can make the move to the end Cell uses the methode validMove from Minion
     * Writes the String "!Move not allowed" on the Console when illegal move
     *
     * @param move is a Move move from Cli is already split in Start and End
     * @see CellIndex
     * @see Cell
     * @see Minion
     */
    public void applyMove(Move move) { // check for valid move
        CellIndex startIndex = cellIndexFor(move.getStart());
        CellIndex endIndex = cellIndexFor(move.getEnd());
        Cell startCell = checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Cell endCell = checkerBoard[endIndex.getRow()][endIndex.getColumn()];
        Minion minion = startCell.getMinion();
        Minion isBeaten = endCell.getMinion();
        String promoteTo = "";

        if (move.getEnd().length() > 2) {
            promoteTo = move.getEnd().substring(2, 3);
        }

        if (!endCell.isEmpty() && minion.isBlack() == !isBeaten.isBlack()) {
            beaten.add(String.valueOf(isBeaten.print_minions()));
        }
        /*if(manuals.isValidEnPassant(startIndex, endIndex, checkerBoard)){
            startCell.setMinion(null);
            endCell.setMinion(minion);
            blackIsTurn = !blackIsTurn;
            System.out.println("!" + move.getStart() + "-" + move.getEnd());
        }*/

        if (manuals.checkIfValidMove(startIndex, endIndex, checkerBoard)&& manuals.checkMoveMakesNoSelfCheck(startIndex, endIndex, checkerBoard, manuals)) {
            startCell.setMinion(null);
            endCell.setMinion(minion);
            blackIsTurn = !blackIsTurn;
            System.out.print("!" + move.getStart() + "-" + move.getEnd() + "\n");
            manuals.promote(endIndex, promoteTo, checkerBoard);
            //minion, ist die figur die bewegt wird, isCheck muss auf die gegnerische team farbe angewendet werden
            if (manuals.isCheck(!(minion.isBlack()), checkerBoard, manuals) && !simple) {
                System.out.println("!Check");
            }
            if (manuals.checkMate(!(minion.isBlack()), checkerBoard, manuals) && !simple) {
                System.out.println("!Check Mate");

            }
        } else {
            System.out.println("!Move not allowed");
        }
    }

    /**
     * Creates the CellIndex for the input. Splits the input in row and column and gets the index of the letter
     * from the List columns.
     *
     * @param stringIndex a String with an letter and an int
     * @return a CellIndex with two int
     */

    public CellIndex cellIndexFor(String stringIndex) {
        String startColumn = stringIndex.substring(0, 1);
        String startRowString = stringIndex.substring(1, 2);
        return new CellIndex(8 - Integer.parseInt(startRowString), columns.indexOf(startColumn));
    }

    public List<String> getBeaten() {
        return beaten;
    }

    public boolean isBlackIsTurn() {
        return blackIsTurn;
    }

    public Cell[][] getCheckerBoard() {
        return checkerBoard;
    }


    public boolean isGameEnd() {
        return gameEnd;
    }

    public boolean isSimple() {
        return simple;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }
}
