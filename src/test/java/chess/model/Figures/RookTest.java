package chess.model.Figures;

import chess.model.Cell;
import chess.model.CellIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void validMoveRookA1A5ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,0);
        CellIndex endIndex = new CellIndex(8-5,0);
        Rook rook = new Rook(false);
        assertTrue(rook.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveRookA1C3ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,0);
        CellIndex endIndex = new CellIndex(8-3,2);
        Rook rook = new Rook(false);
        assertFalse(rook.validMove(startIndex,endIndex));
    }

}