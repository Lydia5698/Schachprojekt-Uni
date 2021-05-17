package chess.model;


import chess.model.figures.*;

import java.util.ArrayList;
import java.util.List;

public class Manuals {
    //regelwerk; valide steuerung der minions

    public Manuals() {
    }

    protected boolean checkIfWayIsNotOccupied(CellIndex start, CellIndex end, Cell[][] checkerBoard) {
        Cell startCell = checkerBoard[start.getRow()][start.getColumn()];
        Cell endCell = checkerBoard[end.getRow()][end.getColumn()];
        boolean colourStart = startCell.getMinion().isBlack();
        boolean colourEnd;
        boolean notOccupied = true;

        // endfeld nicht leer
        if (!(endCell.isEmpty())) {
            colourEnd = endCell.getMinion().isBlack();
            // Figur im Endfeld gleiche Farbe, darf keinen Zug ausfuehren
            if (colourStart == colourEnd) {
                notOccupied = false;
            }
        }
        // pawn nur auf leere felder nach vorne ziehen
        if (String.valueOf(startCell.getMinion().getMinion_type()).equals("P") && !(pawnBeats(start, end, checkerBoard))) {
            notOccupied = false;
            // geradeaus pruefen
            int diffRow = start.getRow() - end.getRow(); //positiv dann gehen wir nach oben, negativ nach unten (weil wir von oben zählen)
            int diffColumn = start.getColumn() - end.getColumn(); //negativ nach rechts, positiv nach links
            Cell nextCell = checkerBoard[start.getRow() - 1][start.getColumn()];//weiß bzw black=false
            if (startCell.getMinion().isBlack()) {
                nextCell = checkerBoard[start.getRow() + 1][start.getColumn()];
            }

            //zwischenzelle(bei zweifeldzug)/endzelle vertikal und leer //TODO fails, zwischenzelle nicht empty trotzdem true ausgabe
            if (Math.abs(diffColumn) == 0 && Math.abs(diffRow) == 1 && endCell.isEmpty()) {
                notOccupied = true;
            } else if (Math.abs(diffColumn) == 0 && Math.abs(diffRow) == 2 && endCell.isEmpty() && nextCell.isEmpty()) {
                notOccupied = true;
            }
            return notOccupied;
        }
        // check if minion = knigth ja- prüfe endfeld nein-prüfe zwischen felder
        if (String.valueOf(startCell.getMinion().getMinion_type()).equals("N")) {
            return notOccupied;
        } else {
            int diffRow = start.getRow() - end.getRow(); //positiv dann gehen wir nach oben, negativ nach unten (weil wir von oben zählen)
            int diffColumn = start.getColumn() - end.getColumn();//negativ nach rechts, positiv nach links

            for (int i = 1; i < (Math.max(Math.abs(diffRow), Math.abs(diffColumn))); i++) {
                //diagonal
                if (Math.abs(diffRow) == Math.abs(diffColumn)) {
                    // next celle checken
                    int stepR = (diffRow / Math.abs(diffRow)) * i; // muss in jeder abfrage einzeln sein, da diffrow und diffcolumn null sein koennten bei bewegung in nur eine Richtung
                    int stepC = (diffColumn / Math.abs(diffColumn)) * i;
                    Cell nextCell = checkerBoard[start.getRow() - stepR][start.getColumn() - stepC];

                    if (!(nextCell.isEmpty())) {
                        notOccupied = false;
                        break;
                    }
                }
                // vertical
                else if (Math.abs(diffRow) > Math.abs(diffColumn)) {
                    int stepR = (diffRow / Math.abs(diffRow)) * i;
                    Cell nextCell = checkerBoard[start.getRow() - stepR][start.getColumn()];
                    if (!(nextCell.isEmpty())) {
                        notOccupied = false;
                        break;
                    }
                }
                //horizontal
                else if (Math.abs(diffRow) < Math.abs(diffColumn)) {
                    int stepC = (diffColumn / Math.abs(diffColumn)) * i;
                    Cell nextCell = checkerBoard[start.getRow()][start.getColumn() - stepC];
                    if (!(nextCell.isEmpty())) {
                        notOccupied = false;
                        break;
                    }
                }
            }
        }
        return notOccupied;
    }


    public List<Cell> getAttackers(boolean isBlack, Cell[][] checker) {
        List<Cell> attackers = new ArrayList<>();
        CellIndex kingIndex = coordinatesKing(isBlack, checker);
        boolean kingColour = checker[kingIndex.getRow()][kingIndex.getColumn()].getMinion().isBlack();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell tmpCell = checker[row][col];
                /*add is Valid movement of minion on field tempcell to king cell && kingcolour != tempcolour*/
                if (!(tmpCell.isEmpty()) && (checkIfValidMove(new CellIndex(row, col), kingIndex, checker)) && kingColour != tmpCell.getMinion().isBlack()) {
                    attackers.add(tmpCell);
                }
            }
        }
        return attackers;
    }

    public List<CellIndex> getAttackersAsIndexList(boolean isBlack, Cell[][] checker) {
        List<CellIndex> attackers = new ArrayList<>();
        CellIndex kingIndex = coordinatesKing(isBlack, checker);
        boolean kingColour = checker[kingIndex.getRow()][kingIndex.getColumn()].getMinion().isBlack();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell tmpCell = checker[row][col];
                if (!(tmpCell.isEmpty())) {/*add is Valid movement of minion on field tempcell to king cell && kingcolour != tempcolour*/
                    CellIndex attacker = new CellIndex(row, col);
                    if ((checkIfValidMove(attacker, kingIndex, checker)) && kingColour != tmpCell.getMinion().isBlack()) {
                        attackers.add(attacker);
                    }
                }
            }
        }
        return attackers;
    }

    CellIndex coordinatesKing(boolean isBlack, Cell[][] checkerBoard) {
        CellIndex kingCell = new CellIndex(9, 9);
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (!checkerBoard[row][column].isEmpty() && String.valueOf(checkerBoard[row][column].getMinion().getMinion_type()).equals("K") && checkerBoard[row][column].getMinion().isBlack() == isBlack) {
                    kingCell.setRow(row);
                    kingCell.setColumn(column);
                }
            }
        }
        return kingCell;
    }

    protected boolean checkIfValidMove(CellIndex start, CellIndex end, Cell[][] checkerboard) {
        Cell startCell = checkerboard[start.getRow()][start.getColumn()];
        Minion minion = startCell.getMinion();
        if (minion.validMove(start, end) || pawnBeats(start, end, checkerboard)) {
            //unblocked? yes, apply, no dont do it
            return checkIfWayIsNotOccupied(start, end, checkerboard);
        }
        return false;
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


    protected boolean isCheck(boolean isBlack, Cell[][] checkerBoard, Manuals manuals) {
        return !(manuals.getAttackers((isBlack), checkerBoard).isEmpty());
    }

    protected boolean checkMate(boolean isBlack, Cell[][] checker, Manuals manuals) {
        boolean checkMate = true;
        List<Cell> attackers = manuals.getAttackers(isBlack, checker);
        List<CellIndex> attackersIndex = manuals.getAttackersAsIndexList(isBlack, checker);
        // list empty, no attackers no checkmate
        if (attackers.isEmpty()) {
            checkMate = false;
            return checkMate;
        }
        CellIndex kingIndex = coordinatesKing(isBlack, checker);
        Cell kingCell = checker[kingIndex.getRow()][kingIndex.getColumn()];
        Minion king = kingCell.getMinion();
        CellIndex endKingIndex = coordinatesKing(isBlack, checker);
        Cell endKingCell; //= checker[kingIndex.getRow()][kingIndex.getColumn()];
        Minion endMinion;

        // check if king move is still check: no-> return false, no checkmate break
        // if king cant move, and more than one attacker -> checkmate
        for (int row = -1; row < 2; row++) {
            for (int col = -1; col < 2; col++) {
                // endIndex
                if (kingIndex.getRow() - row < 8 && kingIndex.getRow() - row >= 0 && kingIndex.getColumn() - col < 8 && kingIndex.getColumn() - col >= 0) {
                    endKingIndex.setRow(kingIndex.getRow() - row);
                    endKingIndex.setColumn(kingIndex.getColumn() - col);
                }
                //check if way is not occupied
                if (checkIfWayIsNotOccupied(kingIndex, endKingIndex, checker) && (kingIndex != endKingIndex) && kingIndex.getRow() - row < 8 && kingIndex.getRow() - row >= 0 && kingIndex.getColumn() - col < 8 && kingIndex.getColumn() - col >= 0/*nicht sicher ob das aussreicht, oder row und collum gecheckt werden muessen*/) {
                    //save minion from endfield // endkingCell aktualisieren und richtigen minion setzen
                    endKingCell = checker[kingIndex.getRow() - row][kingIndex.getColumn() - col];
                    endMinion = endKingCell.getMinion();
                    //apply move
                    kingCell.setMinion(null);
                    endKingCell.setMinion(king);
                    //check if king of kingcolour is still in check
                    if (isCheck(king.isBlack(), checker, manuals)) { // nimmt hier alles checker board, ohne koenigsbewegung
                        // apply move back
                        kingCell.setMinion(king);
                        endKingCell.setMinion(endMinion);
                    } else {
                        // apply move backwards
                        kingCell.setMinion(king);
                        endKingCell.setMinion(endMinion);
                        // if is not check return false
                        checkMate = false;
                        return checkMate;
                    }
                }
            }
        }
        // king can't move check if list of attacker bigger than one, if king cant move and if more than 1 attacker -> checkmate
        if (attackers.size() > 1) {
            return checkMate;
        }
        // get attackers path as CellIndex
        CellIndex attackerIndex = attackersIndex.get(0);
        List<CellIndex> attackerPath = attackerPath(attackerIndex, kingIndex);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                /*//knight capture einfuegen
                if(String.valueOf(attackers.get(0).getMinion().getMinion_type()).equals("N")){
                    // feld nicht leer(wichtig zu prüfen, sonst nullpointer) und figur eigene farbe
                    if((!(checker[row][col].isEmpty()) && checker[row][col].getMinion().isBlack() == isBlack)) {
                        //if valid move to take the attaking knight, no checkmate ->false
                        checkMate = !(checkIfValidMove(new CellIndex(row, col), attackerIndex, checker));//if move not valid, returns !false = true
                    }
                }*/
                // feld nicht leer(wichtig zu prüfen, sonst nullpointer), figur kein koenig und figur eigene farbe /*else*/
                if (!(checker[row][col].isEmpty()) && !(String.valueOf(checker[row][col].getMinion().getMinion_type()).equals("K")) && (checker[row][col].getMinion().isBlack() == isBlack)) {
                    for (CellIndex index : attackerPath) {
                        if (checkIfValidMove(new CellIndex(row, col), index, checker)) {
                            checkMate = false;
                            return checkMate;
                        }
                    }
                }
            }
        }
        return checkMate;
    }

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
        for (int row = 0; row <= Math.abs(diffRow); row++) {
            for (int col = 0; col <= Math.abs(diffColumn); col++) {
                //check if diagonal move and same distance in both directions
                if (Math.abs(diffRow) == Math.abs(diffColumn) && row == col) {
                    int stepR = diffRow / Math.abs(diffRow);
                    int stepC = diffColumn / Math.abs(diffColumn);
                    CellIndex nextCellIndex = new CellIndex(attacker.getRow(), attacker.getColumn());
                    nextCellIndex.setRow(attacker.getRow() - row * stepR);
                    nextCellIndex.setColumn(attacker.getColumn() - col * stepC);
                    attackerPath.add(nextCellIndex);
                }
                //straight movements
                //oben-unten
                else if (diffColumn == 0 && diffRow != 0) {
                    int stepR = diffRow / Math.abs(diffRow);
                    CellIndex nextCellIndex = new CellIndex(attacker.getRow(), attacker.getColumn());
                    nextCellIndex.setRow(attacker.getRow() - row * stepR);
                    nextCellIndex.setColumn(attacker.getColumn() - col);
                    attackerPath.add(nextCellIndex);
                }//links-rechts
                else if (diffColumn != 0 && diffRow == 0) {
                    int stepC = diffColumn / Math.abs(diffColumn);
                    CellIndex nextCellIndex = new CellIndex(attacker.getRow(), attacker.getColumn());
                    nextCellIndex.setRow(attacker.getRow() - row);
                    nextCellIndex.setColumn(attacker.getColumn() - col * stepC);
                    attackerPath.add(nextCellIndex);
                }
            }
        }
        return attackerPath;
    }

    public boolean moveOfRightColour(Move move, Board board) {
        CellIndex currentIndex = board.cellIndexFor(move.getStart());
        Cell currentCell = board.getCheckerBoard()[currentIndex.row][currentIndex.column];
        if (currentCell.isEmpty()) {
            return false;
        } else {
            return currentCell.getMinion().isBlack() == board.isBlackIsTurn();
        }
    }

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

    boolean isValidEnPassant(CellIndex startIndex, CellIndex endIndex, Cell[][] checkerboard) {
        Cell startCell = checkerboard[startIndex.row][startIndex.column];
        Cell endCell = checkerboard[endIndex.row][endIndex.column];
        int diffRow = startIndex.getRow() - endIndex.getRow(); //positiv dann gehen wir nach oben, negativ nach unten (weil wir von oben zählen)
        int diffColumn = startIndex.getColumn() - endIndex.getColumn();//negativ nach rechts, positiv nach links
        // check if Final square is empty
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
        /*if (Math.abs(diffRow) == Math.abs(diffColumn)) {
            // Check move List if the last move was an Pawn move.
            if (// move List is Empty) {
                return false;
            }
            Move lastMove = moveList.get(moveList.size() - 1);
            CellIndex lastMoveEnd = cellIndexFor(lastMove.getEnd());
            CellIndex lastMoveStart = cellIndexFor(lastMove.getStart());
            // Pawn speichern in moveList? Flag?

            if (// lastMove Piece == Pawn) {
                // The pawn should be moving two steps forward/backward.
                // And our pawn should be moving to the same file as the last
                // pawn
                if (Math.abs(lastMoveEnd.row - lastMoveStart.row) == 2
                        && lastMoveEnd.row == startIndex.row) {
                    return true;
                }
            }
        }*/
        return false;
    }

    protected boolean checkMoveMakesNoSelfCheck(CellIndex start, CellIndex end, Cell[][] checkerBoard, Manuals manuals){
        //create cells to simulate move
        Cell startCell = checkerBoard[start.getRow()][start.getColumn()];
        Cell endCell = checkerBoard[end.getRow()][end.getColumn()];
        Minion startMinion = startCell.getMinion();
        Minion endMinion = endCell.getMinion();
        startCell.setMinion(null);
        endCell.setMinion(startMinion);
        if (isCheck(startMinion.isBlack(), checkerBoard, manuals)){
            startCell.setMinion(startMinion);
            endCell.setMinion(endMinion);
            return false;
        }
        else{
            startCell.setMinion(startMinion);
            endCell.setMinion(endMinion);
            return true;
        }
    }


}

