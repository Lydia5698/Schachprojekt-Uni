package chess.model.figures;

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
    void validMoveKingE8E7VerticalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-8,4);
        CellIndex endIndex = new CellIndex(8-7,4);
        King king = new King(true);
        assertTrue(king.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingE1D1HorizontalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,4);
        CellIndex endIndex = new CellIndex(8-1,3);
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
    void validMoveKingE8F7DiagonalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-8,4);
        CellIndex endIndex = new CellIndex(8-7,5);
        King king = new King(true);
        assertTrue(king.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingE1C3DiagonalReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,4);
        CellIndex endIndex = new CellIndex(8-3,2);
        King king = new King(false);
        assertFalse(king.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingE1E6VerticalReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,4);
        CellIndex endIndex = new CellIndex(8-6,4);
        King king = new King(false);
        assertFalse(king.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingE1H1HorizontalReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,4);
        CellIndex endIndex = new CellIndex(8-1,7);
        King king = new King(false);
        assertFalse(king.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingE8H8HorizontalReturnFalse() {
        CellIndex startIndex = new CellIndex(8-8,4);
        CellIndex endIndex = new CellIndex(8-8,7);
        King king = new King(true);
        assertFalse(king.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveKingE8G6DiagonalReturnFalse() {
        CellIndex startIndex = new CellIndex(8-8,4);
        CellIndex endIndex = new CellIndex(8-6,6);
        King king = new King(true);
        assertFalse(king.validMove(startIndex,endIndex));
    }

}