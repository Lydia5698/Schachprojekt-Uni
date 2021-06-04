package chess.model.figures;

import chess.model.CellIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All Tests for the Class Rook
 * @see Rook
 */
public class RookTest {

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

    @Test
    void validMoveRookA1H1ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,0);
        CellIndex endIndex = new CellIndex(8-1,7);
        Rook rook = new Rook(false);
        assertTrue(rook.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveRookH1A1ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,7);
        CellIndex endIndex = new CellIndex(8-1,0);
        Rook rook = new Rook(false);
        assertTrue(rook.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveRookA1B3ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,0);
        CellIndex endIndex = new CellIndex(8-3,1);
        Rook rook = new Rook(false);
        assertFalse(rook.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveRookA8H8ReturnTrue() {
        CellIndex startIndex = new CellIndex(0,0);
        CellIndex endIndex = new CellIndex(0,7);
        Rook rook = new Rook(true);
        assertTrue(rook.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveRookH8A8ReturnTrue() {
        CellIndex startIndex = new CellIndex(0,7);
        CellIndex endIndex = new CellIndex(0,0);
        Rook rook = new Rook(true);
        assertTrue(rook.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveRookH8H5ReturnTrue() {
        CellIndex startIndex = new CellIndex(0,7);
        CellIndex endIndex = new CellIndex(8-5,7);
        Rook rook = new Rook(true);
        assertTrue(rook.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveRookA8C3ReturnFalse() {
        CellIndex startIndex = new CellIndex(0,0);
        CellIndex endIndex = new CellIndex(8-3,2);
        Rook rook = new Rook(true);
        assertFalse(rook.validMove(startIndex,endIndex));
    }

}