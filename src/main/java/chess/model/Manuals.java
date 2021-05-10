package chess.model;


import chess.model.Cell;
import chess.model.CellIndex;
import chess.model.Figures.Minion;

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


    public List<Cell> getAttackers(Boolean isBlack, Cell[][] checker) {
        List<Cell> attackers = new ArrayList<>();
        CellIndex kingIndex = coordinatesKing(isBlack, checker);
        boolean kingColour = checker[kingIndex.getRow()][kingIndex.getColumn()].getMinion().isBlack();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell tmpCell = checker[row][col];
                if (!(tmpCell.isEmpty())) {/*add is Valid movement of minion on field tempcell to king cell && kingcolour != tempcolour*/
                    if ((checkIfValidMove(new CellIndex(row, col), kingIndex, checker)) && kingColour != tmpCell.getMinion().isBlack()) {
                        attackers.add(tmpCell);
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
                if (!checkerBoard[row][column].isEmpty()) {
                    if (String.valueOf(checkerBoard[row][column].getMinion().getMinion_type()).equals("K") && checkerBoard[row][column].getMinion().isBlack() == isBlack) {
                        kingCell.setRow(row);
                        kingCell.setColumn(column);
                    }
                }
            }
        }
        return kingCell;
    }

    protected boolean checkIfValidMove(CellIndex start, CellIndex end, Cell[][] checkerboard) {
        Cell startCell = checkerboard[start.getRow()][start.getColumn()];
        Minion minion = startCell.getMinion();
        if (minion.validMove(start, end)) {
            //unblocked? yes, apply, no dont do it
            return checkIfWayIsNotOccupied(start, end, checkerboard);
        }
        return false;
    }


}

