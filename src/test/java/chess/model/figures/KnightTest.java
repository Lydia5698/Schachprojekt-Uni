package chess.model.figures;

import chess.model.CellIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void validMoveKnightG1F3ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,6);
        CellIndex endIndex = new CellIndex(8-3,5);
        Knight knight = new Knight(false);
        assertTrue(knight.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKnightG8F6ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-8,6);
        CellIndex endIndex = new CellIndex(8-6,5);
        Knight knight = new Knight(true);
        assertTrue(knight.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKnightG1E2ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,6);
        CellIndex endIndex = new CellIndex(8-2,4);
        Knight knight = new Knight(false);
        assertTrue(knight.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKnightG8E7ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-8,6);
        CellIndex endIndex = new CellIndex(8-7,4);
        Knight knight = new Knight(true);
        assertTrue(knight.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingG1F4ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,6);
        CellIndex endIndex = new CellIndex(8-4,5);
        Knight knight = new Knight(false);
        assertFalse(knight.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingG1G4ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,6);
        CellIndex endIndex = new CellIndex(8-4,6);
        Knight knight = new Knight(false);
        assertFalse(knight.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingG1D1ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,6);
        CellIndex endIndex = new CellIndex(8-1,3);
        Knight knight = new Knight(false);
        assertFalse(knight.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingB8B5ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-8,1);
        CellIndex endIndex = new CellIndex(8-5,1);
        Knight knight = new Knight(true);
        assertFalse(knight.validMove(startIndex,endIndex));
    }

}