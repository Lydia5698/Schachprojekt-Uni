package chess.model;


import chess.model.figures.*;

import java.util.ArrayList;
import java.util.List;

import static chess.model.Board.cellIndexFor;

public class Manuals {
    //regelwerk; valide steuerung der minions
    SpecialManuals spManuals = new SpecialManuals();

    public Manuals() {
    }


    /**
     * The method checkIfWayIsNotOccupied checks if the chess fields between the start Cell of the move
     * and the end Cell of the move are empty. If the way is empty the move should be allowed (return true).
     * The method also checks if the end Cell has a piece of the same colour, then no move should be allowed (return false).
     * For knight moves it only checks if the last Cell has a piece of the same colour.
     * @param start CellIndex of the start of the move.
     * @param end CellIndex of the end of the move.
     * @param checkerBoard The chessboard at the moment of the method call.
     * @return boolean true if the way is not occupied and the end Cell does not have a piece of the same colour.
     */
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
        if (String.valueOf(startCell.getMinion().getMinion_type()).equals("P") && !(spManuals.pawnBeats(start, end, checkerBoard))) {
            notOccupied = checkIfPawnMoveIsNotOccupied(start, end, checkerBoard);
            return notOccupied;
        }
        // check if minion = knigth ja- prüfe endfeld(endfeld wurde schon geprueft) nein-prüfe zwischen felder
        if (String.valueOf(startCell.getMinion().getMinion_type()).equals("N")) {
            return notOccupied;
        } else {
            notOccupied = checkIfFieldsInBetweenNotOccupied(start, end, checkerBoard, notOccupied);
        }
        return notOccupied;
    }

    private boolean checkIfFieldsInBetweenNotOccupied(CellIndex start, CellIndex end, Cell[][] checkerBoard, boolean notOccupied){
        boolean fieldsInBetweenNotOccupied = notOccupied;
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
                    fieldsInBetweenNotOccupied = false;
                    return fieldsInBetweenNotOccupied;
                }
            }
            // vertical
            else if (Math.abs(diffRow) > Math.abs(diffColumn)) {
                int stepR = (diffRow / Math.abs(diffRow)) * i;
                Cell nextCell = checkerBoard[start.getRow() - stepR][start.getColumn()];
                if (!(nextCell.isEmpty())) {
                    fieldsInBetweenNotOccupied = false;
                    return fieldsInBetweenNotOccupied;
                }
            }
            //horizontal
            else if (Math.abs(diffRow) < Math.abs(diffColumn)) {
                int stepC = (diffColumn / Math.abs(diffColumn)) * i;
                Cell nextCell = checkerBoard[start.getRow()][start.getColumn() - stepC];
                if (!(nextCell.isEmpty())) {
                    fieldsInBetweenNotOccupied = false;
                    return fieldsInBetweenNotOccupied;
                }
            }
        }
        return fieldsInBetweenNotOccupied;
    }

    private boolean checkIfPawnMoveIsNotOccupied(CellIndex start, CellIndex end, Cell[][] checkerBoard){
        Cell startCell = checkerBoard[start.getRow()][start.getColumn()];
        Cell endCell = checkerBoard[end.getRow()][end.getColumn()];
        boolean pawnMoveNotOccupied = false;
        // geradeaus pruefen
        int diffRow = start.getRow() - end.getRow(); //positiv dann gehen wir nach oben, negativ nach unten (weil wir von oben zählen)
        int diffColumn = start.getColumn() - end.getColumn(); //negativ nach rechts, positiv nach links
        Cell nextCell = checkerBoard[start.getRow() - 1][start.getColumn()];//weiß bzw black=false
        if (startCell.getMinion().isBlack()) {
            nextCell = checkerBoard[start.getRow() + 1][start.getColumn()];
        }
        //zwischenzelle(bei zweifeldzug)/endzelle vertikal und leer
        if (Math.abs(diffColumn) == 0 && Math.abs(diffRow) == 1 && endCell.isEmpty()) {
            pawnMoveNotOccupied = true;
        } else if (Math.abs(diffColumn) == 0 && Math.abs(diffRow) == 2 && endCell.isEmpty() && nextCell.isEmpty()) {
            pawnMoveNotOccupied = true;
        }
        return pawnMoveNotOccupied;
    }


    public List<Cell> getAttackers(boolean isBlack, Cell[][] checker) {
        List<Cell> attackers = new ArrayList<>();
        CellIndex kingIndex = coordinatesKing(isBlack, checker);
        boolean kingColour = checker[kingIndex.getRow()][kingIndex.getColumn()].getMinion().isBlack();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell tmpCell = checker[row][col];
                /*add is Valid movement of minion on field tempcell to king cell && kingcolour != tempcolour*/
                if (!tmpCell.isEmpty() && checkIfValidMove(new CellIndex(row, col), kingIndex, checker) && kingColour != tmpCell.getMinion().isBlack()) {
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
                    if (checkIfValidMove(attacker, kingIndex, checker) && kingColour != tmpCell.getMinion().isBlack()) {
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
        if (minion.validMove(start, end) || spManuals.pawnBeats(start, end, checkerboard)) {
            //unblocked? yes, apply, no dont do it
            return checkIfWayIsNotOccupied(start, end, checkerboard);
        }
        return false;
    }

    protected boolean isCheck(boolean isBlack, Cell[][] checkerBoard, Manuals manuals) {
        return !manuals.getAttackers(isBlack, checkerBoard).isEmpty();
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
        if(manuals.checkIfKingIsAbleToMove(kingIndex, checker, manuals)){
            checkMate = false;
            return checkMate;
        }
        if (attackers.size() > 1) {
            return checkMate;
        }
        // get attackers path as CellIndex
        CellIndex attackerIndex = attackersIndex.get(0);
        List<CellIndex> attackerPath = spManuals.attackerPath(attackerIndex, kingIndex);
        if(checkIfPieceCanProtectTheOwnKing(checker, attackerPath, isBlack)){
            checkMate = false;
            return checkMate;
        }
        return checkMate;
    }

    private boolean checkIfPieceCanProtectTheOwnKing(Cell[][] checker, List<CellIndex> attackerPath, boolean isBlack){
        boolean pieceCanProtectOwnKing = false;
        for(int row=0; row<8;row++){
            for(int col=0; col<8; col++){
                if (!checker[row][col].isEmpty() && !String.valueOf(checker[row][col].getMinion().getMinion_type()).equals("K") && checker[row][col].getMinion().isBlack() == isBlack) {
                    for (CellIndex index : attackerPath) {
                        if (checkIfValidMove(new CellIndex(row, col), index, checker)) {
                            //checkMate = false;
                            pieceCanProtectOwnKing = true;
                            return pieceCanProtectOwnKing;
                        }
                    }
                }
            }
        }
        return pieceCanProtectOwnKing;
    }

    private boolean checkIfKingIsAbleToMove(CellIndex kingIndex, Cell[][] checker, Manuals manuals ){
        boolean ableToMove = false;
        Cell kingCell = checker[kingIndex.getRow()][kingIndex.getColumn()];
        Minion king = kingCell.getMinion();
        CellIndex endKingIndex = new CellIndex(kingIndex.getRow(), kingIndex.getColumn());
        Cell endKingCell;
        Minion endMinion;
        boolean chessFieldExists;
        for (int row = -1; row < 2; row++) {
            for (int col = -1; col < 2; col++) {
                chessFieldExists = kingIndex.getRow() - row < 8 && kingIndex.getRow() - row >= 0 && kingIndex.getColumn() - col < 8 && kingIndex.getColumn() - col >= 0;
                // endIndex
                if (chessFieldExists) {
                    endKingIndex.setRow(kingIndex.getRow() - row);
                    endKingIndex.setColumn(kingIndex.getColumn() - col);
                }
                //check if way is not occupied
                if (chessFieldExists && checkIfWayIsNotOccupied(kingIndex, endKingIndex, checker) && kingIndex != endKingIndex) {
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
                        ableToMove = false;
                    } else {
                        // apply move backwards
                        kingCell.setMinion(king);
                        endKingCell.setMinion(endMinion);
                        // if is not check return true, because king is able to move
                        ableToMove = true;
                        return ableToMove;
                    }
                }
            }
        }
        return ableToMove;
    }

    public boolean moveOfRightColour(Move move, Board board) {
        CellIndex currentIndex = cellIndexFor(move.getStart());
        Cell currentCell = board.getCheckerBoard()[currentIndex.row][currentIndex.column];
        if (currentCell.isEmpty()) {
            return false;
        } else {
            return currentCell.getMinion().isBlack() == board.isBlackIsTurn();
        }
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
