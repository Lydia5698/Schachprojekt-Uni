package chess.model;

import chess.Settings;
import chess.model.figures.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Board is a Checkerboard with Cells where Minions can be in it
 *
 * @author Lydia Günther, Jasmin Wojtkiewicz
 * @see Cell
 * @see Minion
 */
public class Board {
    Cell[][] checkerBoard = new Cell[8][8]; //feldgröße
    public Manuals manuals = new Manuals();
    public StaleMate staleMate = new StaleMate();
    public Settings settings;
    static List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
    public List<String> beaten = new ArrayList<>();
    private final List<Move> moveList = new ArrayList<>();
    private boolean blackIsTurn = false;
    private boolean simple = false;
    private boolean allowedMove = false;

    /**
     * Creates a new Board instance. The Board uses initHorizont to fill the Board with Cells and Minions
     */
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


    /**
     * saves the board in a String
     *
     * @return string output with the Board
     */
    public String showBoard() {
        StringBuilder output = new StringBuilder();                       // stringbuilder erzeugen eines neuen objektes
        int horizontNum = 8;                                              // varibale deklarieren (max value)
        for (Cell[] horizont : checkerBoard) {                            //for each, jede zeile des checkerboards
            output.append(horizontNum).append(" ");                       //nehme nummer und hänge an output Nummer " "
            horizontNum--;                                                //weil nummern ausgabe vorn
            for (Cell cell : horizont) {                                  // for für "cell" auslesen
                output.append(cell.toString()).append(" ");               //figure ausgeben oder leer " "
            }
            output.append("\n");                                          //zeilenumbruch im stringbuiler
        }
        output.append("  ").append(String.join(" ", columns)).append("\n"); //untere buchstaben bauen
        return output.toString();                                         //rückgabe des string output
    }

    /**
     * iterates over the row and sets the Minions
     *
     * @param horizont the row of the Board
     * @param black    the colour of the minion
     */
    private void initHorizont(int horizont, boolean black) {              //aufbauen der truppen
        char[] officerline = "RNBQKBNR".toCharArray();                    //offiziere in string schreiben

        char[] tmp = "PPPPPPPP".toCharArray();                            //bauern in string schreiben
        if (horizont == 0 || horizont == 7) {                             //ausser auf line 0 und 7
            tmp = officerline;                                            //setze offz
        }
        for (int i = 0; i < 8; i++) {                                   //zeile f zeile truppen setzen
            initialiseCellsWithFigures(horizont, black, tmp, i);        //setzen
        }
        if (horizont >= 2 && horizont <= 5) {                           //zw 2 und 5keine figuren auf dem board
            checkerBoard[horizont] = emptyCells();                      //marke zellen als leer
        }
    }


    /**
     * initialises the Board with the Figures
     *
     * @param horizont the row of the Board
     * @param black    the colour of the minion
     * @param tmp      the Pawns in a temporary String
     * @param i        the current row
     */
    private void initialiseCellsWithFigures(int horizont, boolean black, char[] tmp, int i) {
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
     * @param move is a Move from Cli and is already split in Start and End
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

        // makes promotion
        if (move.getEnd().length() > 2) {
            promoteTo = move.getEnd().substring(2, 3);
        }
        // adds the beaten minion to the List beaten
        if (!endCell.isEmpty() && minion.isBlack() == !isBeaten.isBlack()){ //has to be in two steps to avoid nullpointer
            beaten.add(String.valueOf(isBeaten.print_minions()));

        }
        if(!startCell.isEmpty()) {
            checkCurrentMove(move, minion,promoteTo);
        }
        // move is not allowed
        else {
            printMoveNotAllowed();
        }
        if (settings.getSettingsNetwork().isNetwork_active() && allowedMove && blackIsTurn != settings.getSettingsNetwork().isBlack()){
            try {
                settings.getSettingsNetwork().getConnection().send(move.getStart() + "-" + move.getEnd() + promoteTo);
            } catch (Exception e) {
                System.out.println (" exception when send");

            }
        }
    }

    /**
     * prints Move not allowed. When simple and Gui isnt active it only prints !Move not allowed. When not simple it prints
     * in the correct language
     */
    private void printMoveNotAllowed() {
        if (simple && !settings.isGui_active()) {
            System.out.println("!Move not allowed");
        }
        if(!settings.isGui_active()){
            System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "71")));
        }
        else {
            allowedMove = false;
        }
        allowedMove = false;
    }

    /**
     * checks the current move if its valid, makes no self check or is a special moves
     * @param move the current move
     * @param minion the minion of the start Cell
     * @param promoteTo the promotion letter
     */
    void checkCurrentMove(Move move, Minion minion, String promoteTo) {
        CellIndex startIndex = cellIndexFor(move.getStart());
        CellIndex endIndex = cellIndexFor(move.getEnd());
        Cell startCell = checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Cell endCell = checkerBoard[endIndex.getRow()][endIndex.getColumn()];
        if (manuals.checkIfValidMove(startIndex, endIndex, checkerBoard) && manuals.checkMoveMakesNoSelfCheck(startIndex, endIndex, checkerBoard, manuals)) {
            startCell.setMinion(null);
            endCell.setMinion(minion);
            blackIsTurn = !blackIsTurn;
            if (!settings.isGui_active()) {
                System.out.print("!" + move.getStart() + "-" + move.getEnd() + "\n");
            }
            checkAndPrintCheckCheckMate(minion);
            moveList.add(move);
            manuals.spManuals.promote(endIndex, promoteTo, checkerBoard);
            allowedMove = true;
        }
        // check if special move
        else if (specialMove(move, startIndex, endIndex)) {
            //check if in Check
            checkAndPrintCheckCheckMate(minion);
            allowedMove = true;
        }
        // move is not allowed
        else {
            printMoveNotAllowed();
        }
    }

    /**
     * makes the special moves Rochade and En Passant
     *
     * @param move       current move
     * @param startIndex startIndex of the move
     * @param endIndex   endIndex of the move
     * @return boolean if move is special move
     */
    public boolean specialMove(Move move, CellIndex startIndex, CellIndex endIndex) {
        Cell startCell = checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Cell endCell = checkerBoard[endIndex.getRow()][endIndex.getColumn()];
        Minion minion = startCell.getMinion();
        if (manuals.spManuals.isValidEnPassant(startIndex, endIndex, checkerBoard, moveList)) {
            Move lastMove = moveList.get(moveList.size() - 1);
            CellIndex endIndexLastMove = cellIndexFor(lastMove.getEnd());
            Cell endCellLastMove = checkerBoard[endIndexLastMove.getRow()][endIndexLastMove.getColumn()];
            startCell.setMinion(null);
            endCell.setMinion(minion);
            endCellLastMove.setMinion(null);
            blackIsTurn = !blackIsTurn;
            System.out.print("!" + move.getStart() + "-" + move.getEnd() + "\n");
            moveList.add(move);
            return true;
        } else if (manuals.spManuals.checkRochade(moveList, move, checkerBoard, manuals)) {
            manuals.spManuals.moveRochade(move, checkerBoard, manuals);
            blackIsTurn = !blackIsTurn;
            System.out.print("!" + move.getStart() + "-" + move.getEnd() + "\n");
            moveList.add(move);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates the CellIndex for the input. Splits the input in row and column and gets the index of the letter
     * from the List columns.
     *
     * @param stringIndex a String with an letter and an int
     * @return a CellIndex with two int
     */

    public static CellIndex cellIndexFor(String stringIndex) {
        String startColumn = stringIndex.substring(0, 1);
        String startRowString = stringIndex.substring(1, 2);
        return new CellIndex(8 - Integer.parseInt(startRowString), columns.indexOf(startColumn));
    }

    public List<String> getBeaten() {
        return beaten;
    }

    /**
     * gives the Players Turn back
     *
     * @return boolean players turn
     */
    public boolean isBlackIsTurn() {
        return blackIsTurn;
    }

    public Cell[][] getCheckerBoard() {
        Cell[][] checkerBoardCopy = checkerBoard;
        return checkerBoardCopy;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }


    public List<Move> getMoveList() {
        return moveList;
    }

    public void setBlackIsTurn(boolean blackIsTurn) {
        this.blackIsTurn = blackIsTurn;
    }

    public void setAllowedMove(boolean allowedMove) {
        this.allowedMove = allowedMove;
    }

    public boolean isAllowedMove() {
        return allowedMove;
    }

    /**
     * Method checkAndPrintCheckCheckMate checks while a move is made if the move results in a check or checkmate for the opponent colour.
     * If so !Check or !CheckMate are printed.
     *
     * @param minion the Minion, that is moving in applyMove
     */
    protected void checkAndPrintCheckCheckMate(Minion minion) {
        if (manuals.isCheck(!(minion.isBlack()), checkerBoard, manuals)) {
            if (!simple) {
                System.out.println("!" + settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "82")));
            }
            settings.setPlayerInCheck(true);

        }
        else {
            settings.setPlayerInCheck(false);
        }
        //check if in Check Mate
        if (manuals.checkMate(!(minion.isBlack()), checkerBoard, manuals)) {
            if (!simple) {
                System.out.println("!" + settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "92")));
                System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "93")));
            }
            settings.setPlayerInCheck(true);

        } else if (staleMate.isStaleMate(!minion.isBlack(), checkerBoard)) {
            if (!simple) {
                System.out.println("!" + settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "94")));
                System.out.println(settings.getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(settings.getSettingsLanguage().getLanguageNumber() + "93")));
            }
            settings.setPlayerInCheck(true);
            settings.setGameEnd(true);
        }

    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
    public Settings getSettings() {
        return settings;
    }


}
