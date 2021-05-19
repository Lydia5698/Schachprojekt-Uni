package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The CheckIfWayIsOccupiedTest class tests the method checkIfWayIsNotOccupied.
 * @author Jasmin Wojtkiewicz
 */
class CheckIfWayIsOccupiedTest {

    // Get Way is Occupied
    @Test
    void checkIfWayIsNotOccupiedE2E4Vertical() {
        Board board = new Board();
        CellIndex start = new CellIndex(8-2, 4);
        CellIndex end = new CellIndex(8-4, 4);
        assertTrue(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedE7E5Vertical() {
        Board board = new Board();
        CellIndex start = new CellIndex(8-7, 4);
        CellIndex end = new CellIndex(8-5, 4);
        assertTrue(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedA3D3RookHorizontal() {
        Board board = new Board();
        Move movePawn = new Move("a2-a4"); // Move Pawn A2-A4
        board.applyMove(movePawn);
        Move moveRook = new Move("a1-a3"); // Move Rook Vertical
        board.applyMove(moveRook);

        CellIndex start = new CellIndex(8-3, 0); // Move Rook A3-D3
        CellIndex end = new CellIndex(8-3, 3);
        assertTrue(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedH6D6RookHorizontal() {
        Board board = new Board();
        Move movePawn = new Move("h7-h5"); // Move Pawn H7-H5
        board.applyMove(movePawn);
        Move moveRook = new Move("h8-h6"); // Move Rook Vertical
        board.applyMove(moveRook);

        CellIndex start = new CellIndex(8-6, 7); // Move Rook H6-D6
        CellIndex end = new CellIndex(8-6, 3);
        assertTrue(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedC1F4BishopDiagonal() {
        Board board = new Board();
        Move movePawn = new Move("d2-d4"); // Move Pawn D2-D4
        board.applyMove(movePawn);

        CellIndex start = new CellIndex(8-1, 2); // Move Bishop C1-F4
        CellIndex end = new CellIndex(8-4, 5);
        assertTrue(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedF8C5BishopDiagonal() {
        Board board = new Board();
        Move movePawn = new Move("g7-g5"); // Move Pawn E7-E5
        board.applyMove(movePawn);

        CellIndex start = new CellIndex(0, 5); // Move Bishop F8-H6
        CellIndex end = new CellIndex(8-6, 7);
        assertTrue(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedVerticalB1C3Knight() {
        Board board = new Board();
        CellIndex start = new CellIndex(8-1, 1);
        CellIndex end = new CellIndex(8-3, 2);
        assertTrue(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void bishopBeatsPawnDiagonal() {
        Board board = new Board();
        Move movePawn = new Move("e7-e5"); // Move Pawn E7-E5
        board.applyMove(movePawn);
        Move moveBishop = new Move("f8-a3"); // Move Bishop F8-A3
        board.applyMove(moveBishop);

        CellIndex start = new CellIndex(8-3, 0); // Move Bishop A3-B2
        CellIndex end = new CellIndex(8-2, 1);
        assertTrue(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedE1A1Horizontal() {
        Board board = new Board();
        CellIndex start = new CellIndex(8-1, 4);
        CellIndex end = new CellIndex(8-1, 0);
        assertFalse(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedA1A3Vertical() {
        Board board = new Board();
        CellIndex start = new CellIndex(8-1, 0);
        CellIndex end = new CellIndex(8-3, 0);
        assertFalse(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

    @Test
    void checkIfWayIsNotOccupiedC1A3Diagonal() {
        Board board = new Board();
        CellIndex start = new CellIndex(8-1, 2);
        CellIndex end = new CellIndex(8-3, 0);
        assertFalse(board.manuals.checkIfWayIsNotOccupied(start, end, board.checkerBoard));
    }

}