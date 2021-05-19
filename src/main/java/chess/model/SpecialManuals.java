package chess.model;

import chess.model.figures.*;

import java.util.ArrayList;
import java.util.List;

import static chess.model.Board.cellIndexFor;

public class SpecialManuals {
    public SpecialManuals(){}

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

    private List<CellIndex> attackerPathNoKnight(CellIndex attacker, CellIndex victim){
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
     * @param endIndex The endIndex of the move
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
     * @param endIndex endIndex of the move
     * @param promoteTo the Minion-Type for the Promotion. Only Bishop, Knight, Rook or Queen are allowed
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
     * @param startIndex startIndex of the move
     * @param endIndex endIndex of the move
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
            if(Math.abs(diffColumnNewMoveLastMove) == 0 && lastMoveEnd.row == startIndex.row && String.valueOf(endLastMoveCell.getMinion().getMinion_type()).equals("P")){
                // The pawn should be moving two steps forward/backward.
                // And our pawn should be moving to the same column as the last
                // pawn
                return Math.abs(lastMoveEnd.row - lastMoveStart.row) == 2
                        && endIndex.column == lastMoveEnd.column;
            }
        }
        return false;
    }

}
