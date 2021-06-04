package chess.model.figures;

import chess.model.CellIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All Tests for the Class Bishop
 * @see Bishop
 */
public class BishopTest {

    @Test
    void validMoveBishopC1E3ReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,2);
        CellIndex endIndex = new CellIndex(8-3,4);
        Bishop bishop = new Bishop(false);
        assertTrue(bishop.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveBishopC1C5ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,2);
        CellIndex endIndex = new CellIndex(8-5,2);
        Bishop bishop = new Bishop(false);
        assertFalse(bishop.validMove(startIndex,endIndex));
    }

}