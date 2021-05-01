package chess.model.Figures;

import chess.model.CellIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void validMoveKingE1E2VerticalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,4);
        CellIndex endIndex = new CellIndex(8-2,4);
        King king = new King(false);
        assertTrue(king.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingE1D2DiagonalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,4);
        CellIndex endIndex = new CellIndex(8-2,3);
        King king = new King(false);
        assertTrue(king.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingE1E6ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,4);
        CellIndex endIndex = new CellIndex(8-6,4);
        King king = new King(false);
        assertFalse(king.validMove(startIndex,endIndex));
    }
}