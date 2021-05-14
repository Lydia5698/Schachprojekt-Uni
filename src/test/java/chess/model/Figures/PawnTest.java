package chess.model.Figures;

import chess.model.CellIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void validMovePawnE2E4ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-2,4);
        CellIndex endIndex = new CellIndex(8-4,4);
        Pawn pawn = new Pawn(false);
        assertTrue(pawn.validMove(startIndex,endIndex));
    }

    @Test
    void validMovePawnE7E5ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-7,4);
        CellIndex endIndex = new CellIndex(8-5,4);
        Pawn pawn = new Pawn(true);
        assertTrue(pawn.validMove(startIndex,endIndex));
    }

    @Test
    void validMovePawnE2E3ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-2,4);
        CellIndex endIndex = new CellIndex(8-3,4);
        Pawn pawn = new Pawn(false);
        assertTrue(pawn.validMove(startIndex,endIndex));
    }

    @Test
    void validMovePawnB7B6ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-7,1);
        CellIndex endIndex = new CellIndex(8-6,1);
        Pawn pawn = new Pawn(true);
        assertTrue(pawn.validMove(startIndex,endIndex));
    }

    @Test
    void validMovePawnE2G2ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-2,4);
        CellIndex endIndex = new CellIndex(8-2,6);
        Pawn pawn = new Pawn(false);
        assertFalse(pawn.validMove(startIndex,endIndex));
    }

    @Test
    void validMovePawnE7G7ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-7,4);
        CellIndex endIndex = new CellIndex(8-7,6);
        Pawn pawn = new Pawn(true);
        assertFalse(pawn.validMove(startIndex,endIndex));
    }

    @Test
    void validMovePawnG7G8ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-7,6);
        CellIndex endIndex = new CellIndex(8-8,6);
        Pawn pawn = new Pawn(true);
        assertFalse(pawn.validMove(startIndex,endIndex));
    }

    @Test
    void validMovePawnG7E5ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-7,6);
        CellIndex endIndex = new CellIndex(8-5,4);
        Pawn pawn = new Pawn(true);
        assertFalse(pawn.validMove(startIndex,endIndex));
    }

}