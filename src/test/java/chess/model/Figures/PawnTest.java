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
    void validMovePawnE2G2ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-2,4);
        CellIndex endIndex = new CellIndex(8-2,6);
        Pawn pawn = new Pawn(false);
        assertFalse(pawn.validMove(startIndex,endIndex));
    }

}