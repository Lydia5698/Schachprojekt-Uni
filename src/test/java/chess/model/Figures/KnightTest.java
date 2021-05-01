package chess.model.Figures;

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
    void validMoveKingG1F4ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,6);
        CellIndex endIndex = new CellIndex(8-4,5);
        Knight knight = new Knight(false);
        assertFalse(knight.validMove(startIndex,endIndex));
    }

}