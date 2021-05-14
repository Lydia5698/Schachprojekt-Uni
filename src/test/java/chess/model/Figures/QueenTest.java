package chess.model.Figures;

import chess.model.CellIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void validMoveQueenD1D4VerticalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,3);
        CellIndex endIndex = new CellIndex(8-4,3);
        Queen queen = new Queen(false);
        assertTrue(queen.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveQueenD1G1HorizontalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,3);
        CellIndex endIndex = new CellIndex(8-1,6);
        Queen queen = new Queen(false);
        assertTrue(queen.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveQueenD1G4DiagonalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-1,3);
        CellIndex endIndex = new CellIndex(8-4,6);
        Queen queen = new Queen(false);
        assertTrue(queen.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveQueenD8D4VerticalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-8,3);
        CellIndex endIndex = new CellIndex(8-4,3);
        Queen queen = new Queen(true);
        assertTrue(queen.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveQueenD8A8HorizontalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-8,3);
        CellIndex endIndex = new CellIndex(8-8,0);
        Queen queen = new Queen(true);
        assertTrue(queen.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveQueenD8G5DiagonalReturnTrue() {
        CellIndex startIndex = new CellIndex(8-8,3);
        CellIndex endIndex = new CellIndex(8-5,6);
        Queen queen = new Queen(true);
        assertTrue(queen.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveQueenD1A3ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-1,3);
        CellIndex endIndex = new CellIndex(8-3,0);
        Queen queen = new Queen(false);
        assertFalse(queen.validMove(startIndex,endIndex));
    }

    @Test
    void validMoveQueenD8E5ReturnFalse() {
        CellIndex startIndex = new CellIndex(8-8,3);
        CellIndex endIndex = new CellIndex(8-6,4);
        Queen queen = new Queen(true);
        assertFalse(queen.validMove(startIndex,endIndex));
    }

}