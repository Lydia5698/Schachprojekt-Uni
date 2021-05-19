package chess.model;

import chess.model.figures.*;

import java.util.ArrayList;
import java.util.List;

import static chess.model.Board.cellIndexFor;

/**
 * Manuals with all the special rules
 *
 * @author Lydia Günther, Jasmin Wojtkiewicz
 */
public class SpecialManuals {
    /**
     * Creates a new SpecialMove instance.
     */
    public SpecialManuals() {
    }

    /**
     * All Atackers
     *
     * @param attacker CellIndex of the attacker
     * @param victim   CellIndex of the victim
     * @return List<CellIndex> with all the Attackers
     */
    protected List<CellIndex> attackerPath(CellIndex attacker, CellIndex victim) {
        List<CellIndex> attackerPath = new ArrayList<>();
        int diffRow = attacker.getRow() - victim.getRow(); //positiv dann gehen wir nach oben, negativ nach unten (weil wir von oben zählen)
        int diffColumn = attacker.getColumn() - victim.getColumn(); //negativ nach rechts, positiv nach links
        //check if knight move, for knight move only add attackers field
        if (Math.abs(diffRow) == 1 && Math.abs(diffColumn) == 2 || Math.abs(diffRow) == 2 && Math.abs(diffColumn) == 1) {
            CellIndex nextCellIndex = new CellIndex(attacker.getRow(), attacker.getColumn());
            attackerPath.add(nextCellIndex);
            return attackerPath;
        }
        attackerPath = attackerPathNoKnight(attacker, victim);
        return attackerPath;
    }

    /**
     * All attacker paths without the Knight path
     *
     * @param attacker CellIndex of the attacker
     * @param victim   CellIndex of the victim
     * @return List<CellIndex> with all the attacker paths without the Knight path
     */
    private List<CellIndex> attackerPathNoKnight(CellIndex attacker, CellIndex victim) {
        List<CellIndex> attackerPathNoKnight = new ArrayList<>();
        int diffRow = attacker.getRow() - victim.getRow(); //positiv dann gehen wir nach oben, negativ nach unten (weil wir von oben zählen)
        int diffColumn = attacker.getColumn() - victim.getColumn(); //negativ nach rechts, positiv nach links
        for (int row = 0; row <= Math.abs(diffRow); row++) {
            for (int col = 0; col <= Math.abs(diffColumn); col++) {
                //check if diagonal move and same distance in both directions
                if (Math.abs(diffRow) == Math.abs(diffColumn) && row == col) {
                    int stepR = diffRow / Math.abs(diffRow);
                    int stepC = diffColumn / Math.abs(diffColumn);
                    CellIndex nextCellIndex = new CellIndex(attacker.getRow(), attacker.getColumn());
                    nextCellIndex.setRow(attacker.getRow() - row * stepR);
                    nextCellIndex.setColumn(attacker.getColumn() - col * stepC);
                    attackerPathNoKnight.add(nextCellIndex);
                }
                //straight movements
                //oben-unten
                else if (diffColumn == 0 && diffRow != 0) {
                    int stepR = diffRow / Math.abs(diffRow);
                    CellIndex nextCellIndex = new CellIndex(attacker.getRow(), attacker.getColumn());
                    nextCellIndex.setRow(attacker.getRow() - row * stepR);
                    nextCellIndex.setColumn(attacker.getColumn() - col);
                    attackerPathNoKnight.add(nextCellIndex);
                }//links-rechts
                else if (diffColumn != 0 && diffRow == 0) {
                    int stepC = diffColumn / Math.abs(diffColumn);
                    CellIndex nextCellIndex = new CellIndex(attacker.getRow(), attacker.getColumn());
                    nextCellIndex.setRow(attacker.getRow() - row);
                    nextCellIndex.setColumn(attacker.getColumn() - col * stepC);
                    attackerPathNoKnight.add(nextCellIndex);
                }
            }
        }
        return attackerPathNoKnight;
    }

    /**
     * Checks if the Pawn can beats(diagonally)
     *
     * @param startIndex   startIndex of the move
     * @param endIndex     endIndex of the move
     * @param checkerBoard chessboard with Cells
     * @return boolean if Pawn can beat
     */
    // bauer schlagen
    protected boolean pawnBeats(CellIndex startIndex, CellIndex endIndex, Cell[][] checkerBoard) {
        Cell startCell = checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Cell endCell = checkerBoard[endIndex.getRow()][endIndex.getColumn()];
        Minion minion = startCell.getMinion();
        Minion isBeaten = endCell.getMinion();
        boolean pawnBeats = false;
        boolean fieldWithEnemy = String.valueOf(minion.getMinion_type()).equals("P") && isBeaten != null && isBeaten.isBlack() != minion.isBlack();
        // startcellfarbe und richtung
        int diffRow = startIndex.getRow() - endIndex.getRow(); //positiv dann gehen wir nach oben, negativ nach unten (weil wir von oben zählen)
        int diffColumn = startIndex.getColumn() - endIndex.getColumn();//negativ nach rechts, positiv nach links

        if ((Math.abs(diffColumn) == 1 && minion.isBlack() && diffRow == -1 || !minion.isBlack() && diffRow == 1 && Math.abs(diffColumn) == 1) && fieldWithEnemy) {
            pawnBeats = true;
        }
        return pawnBeats;
    }

    /**
     * isValidPromotion checks if a Pawn has reached the last row of the Board. Then it is possible to promote him
     *
     * @param endIndex     The endIndex of the move
     * @param checkerboard The Chessboard
     * @return boolean if the Promotion is Valid
     */
    // checks if Cell contains a Pawn and is at the end of the Board so the Pawn can be promoted
    boolean isValidPromotion(CellIndex endIndex, Cell[][] checkerboard) {
        Cell endCell = checkerboard[endIndex.row][endIndex.column];
        if (endCell.isEmpty()) {
            return false;
        }

        if (String.valueOf(endCell.getMinion().getMinion_type()).equals("P")) {
            int row = 0;
            if (endCell.getMinion().isBlack()) {
                row = 7;
            }
            CellIndex pawnIndex = new CellIndex(row, endIndex.column);
            return endIndex.row == pawnIndex.row && endIndex.column == pawnIndex.column;
        }
        return false;

    }

    /**
     * Promote promotes an Pawn to another Minion. It is possible to get promoted to a Knight, Bishop, Queen or Rook.
     * Before the Pawn gets a Promotion the Methode isValidPromotion is checking if the promotion is valid.
     *
     * @param endIndex     endIndex of the move
     * @param promoteTo    the Minion-Type for the Promotion. Only Bishop, Knight, Rook or Queen are allowed
     * @param checkerboard the Chessboard
     */
    // promotes Pawn to the Minion specified
    public void promote(CellIndex endIndex, String promoteTo, Cell[][] checkerboard) {
        Cell endCell = checkerboard[endIndex.getRow()][endIndex.column];
        if (isValidPromotion(endIndex, checkerboard)) {
            Minion minion;
            switch (promoteTo) {
                case "B":
                    minion = new Bishop(endCell.getMinion().isBlack());
                    break;
                case "N":
                    minion = new Knight(endCell.getMinion().isBlack());
                    break;
                case "R":
                    minion = new Rook(endCell.getMinion().isBlack());
                    break;
                default:
                    minion = new Queen(endCell.getMinion().isBlack());
                    break;
            }
            /*moveList.add(neuer Minion);*/
            endCell.setMinion(null);
            endCell.setMinion(minion);
        }
    }

    /**
     * is ValidEnPassant checks if the Pawn can move like en Passant
     *
     * @param startIndex   startIndex of the move
     * @param endIndex     endIndex of the move
     * @param checkerboard Chessboard
     * @return a boolean if the en Passant is valid
     */
    boolean isValidEnPassant(CellIndex startIndex, CellIndex endIndex, Cell[][] checkerboard, ArrayList<Move> moveList) {
        Cell startCell = checkerboard[startIndex.row][startIndex.column];
        Cell endCell = checkerboard[endIndex.row][endIndex.column];
        int diffRow = startIndex.getRow() - endIndex.getRow(); //positiv dann gehen wir nach oben, negativ nach unten (weil wir von oben zählen)
        int diffColumn = startIndex.getColumn() - endIndex.getColumn();//negativ nach rechts, positiv nach links
        // check if Final Cell is not empty
        if (!endCell.isEmpty()) {
            return false;
        }

        // The Figure in the startCell should be a Pawn
        if (!String.valueOf(startCell.getMinion().getMinion_type()).equals("P")) {
            return false;
        }
        // Move type is different according to player color. Case white
        if (!startCell.getMinion().isBlack()) {
            if (startIndex.row < endIndex.row) {
                // White can only move up
                return false;
            }
            // Case Black
        } else {
            if (startIndex.row > endIndex.row) {
                // Black can only move down
                return false;
            }
        }
        // check if the move is like an Bishop move.
        if (Math.abs(diffRow) == Math.abs(diffColumn) && Math.abs(diffColumn) == 1 && Math.abs(diffRow) == 1) {
            // Check move List if the last move was an Pawn move.
            if (moveList.isEmpty()) {
                return false;
            }
            Move lastMove = moveList.get(moveList.size() - 1);
            CellIndex lastMoveEnd = cellIndexFor(lastMove.getEnd());
            CellIndex lastMoveStart = cellIndexFor(lastMove.getStart());
            Cell endLastMoveCell = checkerboard[lastMoveEnd.row][lastMoveEnd.column];
            int diffColumnNewMoveLastMove = endIndex.column - lastMoveEnd.column;
            // check if the Pawn moves in the right direction
            // check if both Pawns are in the same row at the begining of the move
            // check if last move was Pawn move
            if (Math.abs(diffColumnNewMoveLastMove) == 0 && lastMoveEnd.row == startIndex.row && String.valueOf(endLastMoveCell.getMinion().getMinion_type()).equals("P")) {
                // The pawn should be moving two steps forward/backward.
                // And our pawn should be moving to the same column as the last
                // pawn
                return Math.abs(lastMoveEnd.row - lastMoveStart.row) == 2
                        && endIndex.column == lastMoveEnd.column;
            }
        }
        return false;
    }

    /**
     * checks if the figure in the Cell has moved or got beaten
     *
     * @param cell     String CellIndex
     * @param MoveList List with all moves
     * @return true if figure has moved or got beaten
     */
    private boolean hasFigureMoved(String cell, ArrayList<Move> MoveList) {
        for (Move move : MoveList) {
            if (move.getStart().equals(cell)
                    || move.getEnd().equals(cell)) {
                return true;
            }
        }
        return false;
    }

    private boolean figureRochadeHasMoved(ArrayList<Move> MoveList, CellIndex start, CellIndex end, Cell[][] checkerboard) {
        Cell startCell = checkerboard[start.row][start.column];
        String posRookWLeft = "a1";
        String posRookWRight = "h1";
        String posRookBLeft = "a8";
        String posRookBRight = "h8";
        String posKingBlack = "e8";
        String posKingWhite = "e1";
        // case black
        if (startCell.getMinion().isBlack()) {
            // check if Rook or King has moved and the move goes in the direction of the Rook
            return (!(hasFigureMoved(posRookBLeft, MoveList) && end.column == 2 || hasFigureMoved(posRookBRight, MoveList) && end.column == 6  || hasFigureMoved(posKingBlack, MoveList)));
            // case white
        } else {
            // check if Rook or King has moved and the move goes in the direction of the Rook
            return (!((hasFigureMoved(posRookWLeft, MoveList) && end.column != 2) || (hasFigureMoved(posRookWRight, MoveList) && end.column != 6) || hasFigureMoved(posKingWhite, MoveList)));
        }
    }


    /**
     * Checks if the move is an valid Rochade
     *
     * @param MoveList     List with all Moves
     * @param move         the current move
     * @param checkerboard Chessboard with Cells in it
     * @param manuals      the manuals
     * @return boolean if Rochade is valid
     */
    boolean checkRochade(ArrayList<Move> MoveList, Move move, Cell[][] checkerboard, Manuals manuals) {
        CellIndex start = cellIndexFor(move.getStart());
        CellIndex end = cellIndexFor(move.getEnd());
        int diffColumn = end.column - start.column;
        int diffRow = end.row - start.row;
        Cell startCell = checkerboard[start.row][start.column];
        CellIndex rookWhiteL = cellIndexFor("a1");
        CellIndex rookWhiteR = cellIndexFor("h1");
        CellIndex rookBlackL = cellIndexFor("a8");
        CellIndex rookBlackR = cellIndexFor("h8");
        // check if startCell is Empty
        boolean validRochade = figureRochadeHasMoved(MoveList, start, end, checkerboard);

        if (startCell.isEmpty()) {
            validRochade = false;
        }
        // case black
        if (startCell.getMinion().isBlack()) {
            // check if the fields between Left Rook and King are Occupied
            if (!(manuals.checkIfFieldsInBetweenNotOccupied(start, rookBlackL, checkerboard, true)) && end.column == 2) {
                validRochade = false;
            }
            // check if the fields between Right Rook and King are Occupied
            if (!(manuals.checkIfFieldsInBetweenNotOccupied(start, rookBlackR, checkerboard, true)) && end.column == 6) {
                validRochade = false;
            }
        }
        // Case white
        else {
            // check if the fields between Left Rook and King are Occupied
            if (!(manuals.checkIfFieldsInBetweenNotOccupied(start, rookWhiteL, checkerboard, true)) && end.column == 2) {
                validRochade = false;
            }
            // check if the fields between Right Rook and King are Occupied
            if (!(manuals.checkIfFieldsInBetweenNotOccupied(start, rookWhiteR, checkerboard, true)) && end.column == 6) {
                validRochade = false;
            }
        }
        // check if a King is in the startCell and checks if the King makes two steps
        if (!(Math.abs(diffColumn) == 2 && String.valueOf(startCell.getMinion().getMinion_type()).equals("K"))) {
            validRochade = false;
        }
        // checks if the King is in the end Row
        if (!(start.row == 0 && end.row == 0 || start.row == 7 && end.row == 7)) {
            validRochade = false;
        }
        // checks if the King moves horizontal
        if (!(Math.abs(diffRow) == 0)) {
            validRochade = false;
        }

        return validRochade;
    }

    /**
     * makes the Rochade move
     *
     * @param move         current move
     * @param checkerBoard chessboard with Cells
     * @param manuals      manuals
     */
    void moveRochade(Move move, Cell[][] checkerBoard, Manuals manuals) {
        CellIndex start = cellIndexFor(move.getStart());
        CellIndex end = cellIndexFor(move.getEnd());
        Cell startCell = checkerBoard[start.row][start.column];
        Cell endCell = checkerBoard[end.row][end.column];
        int row = 7;
        // Case black
        if (startCell.getMinion().isBlack()) {
            row = 0;
        }
        Cell newRookIndexShort = checkerBoard[row][5];
        Cell newRookIndexLong = checkerBoard[row][3];
        CellIndex RookRCellIndex = new CellIndex(row, 7);
        CellIndex RookLCellIndex = new CellIndex(row, 0);
        Cell RookLCell = checkerBoard[RookLCellIndex.row][RookLCellIndex.column];
        Cell RookRCell = checkerBoard[RookRCellIndex.row][RookRCellIndex.column];
        Minion king = startCell.getMinion();
        Minion rookL = RookLCell.getMinion();
        Minion rookR = RookRCell.getMinion();

        // checks if the fields between the King and Rook are attacked
        // case right short Rochade
        if (end.getColumn() == 6 && manuals.checkMoveMakesNoSelfCheck(start, RookRCellIndex, checkerBoard, manuals)) {
            startCell.setMinion(null);
            RookRCell.setMinion(null);
            newRookIndexShort.setMinion(rookR);
            endCell.setMinion(king);
        }
        // checks if the fields between the King and Rook are attacked
        // case left long Rochade
        if (end.getColumn() == 2 && manuals.checkMoveMakesNoSelfCheck(start, RookLCellIndex, checkerBoard, manuals)) {
            startCell.setMinion(null);
            RookLCell.setMinion(null);
            newRookIndexLong.setMinion(rookL);
            endCell.setMinion(king);
        }



    }

}
