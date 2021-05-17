package chess.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All Tests for the Class Manuals
 * @see Manuals
 */
class ManualsTest {

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
        Move movePawn = new Move("e7-e5"); // Move Pawn E7-E5
        board.applyMove(movePawn);

        CellIndex start = new CellIndex(0, 5); // Move Bishop F8-C5
        CellIndex end = new CellIndex(8-5, 2);
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


    // Get Attackers
    @Test
    void getAttackersKingWhite() {
        Board board = new Board();
        assertTrue(board.manuals.getAttackers(false, board.checkerBoard).isEmpty());
    }

    @Test
    void getAttackersKingBlack() {
        Board board = new Board();
        assertTrue(board.manuals.getAttackers(true, board.checkerBoard).isEmpty());
    }

   @Test
    void getAttackersKingWhiteUnderAttack() {
        Board board = new Board();
        Move movePawn = new Move("e7-e5"); // Move Pawn E7-E5
        board.applyMove(movePawn);
        Move moveBishop = new Move("f8-b4"); // Move Bishop F8-B4
        board.applyMove(moveBishop);
        Move moveBishopCheck = new Move("b4-d2"); //Move B4-D2
        board.applyMove(moveBishopCheck);// white king under attack, list not empty -> false
        boolean attackers = (board.manuals.getAttackers(false, board.checkerBoard)).isEmpty();
        assertFalse(attackers);
    }

    @Test
    void getAttackersKingBlackUnderAttack() {
        Board board = new Board();
        Move movePawn = new Move("e2-e4"); // Move Pawn E2-E4
        board.applyMove(movePawn);
        Move moveBishop = new Move("f1-b5"); // Move Bishop F1-B5
        board.applyMove(moveBishop);
        Move moveBishopCheck = new Move("b5-d7"); //Move B5-D7
        board.applyMove(moveBishopCheck);
        boolean attackers = (board.manuals.getAttackers(true, board.checkerBoard)).isEmpty();
        assertFalse(attackers);
    }

    // Promote Test
    @Test
    void promoteWhitePawn(){
        Board board = new Board();
        Move movePawn = new Move("h2-h4"); // Move Pawn H2-H4
        board.applyMove(movePawn);
        movePawn = new Move("h4-h5"); // Move Pawn H4-H5
        board.applyMove(movePawn);
        movePawn = new Move("h5-h6"); // Move Pawn H5-H6
        board.applyMove(movePawn);
        movePawn = new Move("h6-g7"); // Move Pawn H6-G7
        board.applyMove(movePawn);
        movePawn = new Move("g7-f8"); // Move Pawn G7-F8
        board.applyMove(movePawn);
        CellIndex endIndexPawn = board.cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isQueen = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("Q");
        assertTrue(isQueen);
    }

    @Test
    void promoteWhitePawnToRook(){
        Board board = new Board();
        Move movePawn = new Move("h2-h4"); // Move Pawn H2-H4
        board.applyMove(movePawn);
        movePawn = new Move("h4-h5"); // Move Pawn H4-H5
        board.applyMove(movePawn);
        movePawn = new Move("h5-h6"); // Move Pawn H5-H6
        board.applyMove(movePawn);
        movePawn = new Move("h6-g7"); // Move Pawn H6-G7
        board.applyMove(movePawn);
        movePawn = new Move("g7-f8R"); // Move Pawn G7-F8
        board.applyMove(movePawn);
        CellIndex endIndexPawn = board.cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isRook = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("R");
        boolean isCheck = board.manuals.isCheck(!(endCellPawn.getMinion().isBlack()), board.checkerBoard, board.manuals);
        assertTrue(isRook && isCheck);
    }

    @Test
    void promoteWhitePawnToKnight(){
        Board board = new Board();
        Move movePawn = new Move("h2-h4"); // Move Pawn H2-H4
        board.applyMove(movePawn);
        movePawn = new Move("h4-h5"); // Move Pawn H4-H5
        board.applyMove(movePawn);
        movePawn = new Move("h5-h6"); // Move Pawn H5-H6
        board.applyMove(movePawn);
        movePawn = new Move("h6-g7"); // Move Pawn H6-G7
        board.applyMove(movePawn);
        movePawn = new Move("g7-f8N"); // Move Pawn G7-F8
        board.applyMove(movePawn);
        CellIndex endIndexPawn = board.cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isKnight = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("N");
        boolean isCheck = board.manuals.isCheck(!(endCellPawn.getMinion().isBlack()), board.checkerBoard, board.manuals);
        assertTrue(isKnight && !isCheck);
    }

    @Test
    void promoteWhitePawnToBishop(){
        Board board = new Board();
        Move movePawn = new Move("h2-h4"); // Move Pawn H2-H4
        board.applyMove(movePawn);
        movePawn = new Move("h4-h5"); // Move Pawn H4-H5
        board.applyMove(movePawn);
        movePawn = new Move("h5-h6"); // Move Pawn H5-H6
        board.applyMove(movePawn);
        movePawn = new Move("h6-g7"); // Move Pawn H6-G7
        board.applyMove(movePawn);
        movePawn = new Move("g7-f8B"); // Move Pawn G7-F8
        board.applyMove(movePawn);
        CellIndex endIndexPawn = board.cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isBishop = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("B");
        boolean isCheck = board.manuals.isCheck(!(endCellPawn.getMinion().isBlack()), board.checkerBoard, board.manuals);
        assertTrue(isBishop && !isCheck);
    }


    @Test
    void promoteBlackPawn(){
        Board board = new Board();
        Move movePawn = new Move("a7-a5"); // Move Pawn A7-A5
        board.applyMove(movePawn);
        movePawn = new Move("a5-a4"); // Move Pawn A5-A4
        board.applyMove(movePawn);
        movePawn = new Move("a4-a3"); // Move Pawn A4-A3
        board.applyMove(movePawn);
        movePawn = new Move("a3-b2"); // Move Pawn A3-B2
        board.applyMove(movePawn);
        movePawn = new Move("b2-c1"); // Move Pawn B2-C1
        board.applyMove(movePawn);
        CellIndex endIndexPawn = board.cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isQueen = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("Q");
        assertTrue(isQueen);
    }

/*    // Tests EnPassant
    @Test
    void isValidEnPassantWhite(){
        Board board = new Board();
        Move movePawn = new Move("d2-d4"); // Move Pawn D2-D4
        board.applyMove(movePawn);
        movePawn = new Move("d4-d5"); // Move Pawn D4-D5
        board.applyMove(movePawn);
        movePawn = new Move("e7-e5"); // Move Pawn E7-E5
        board.applyMove(movePawn);
        movePawn = new Move("d5-e6"); // Move Pawn D5-E6 EnPassant
        CellIndex startIndex = board.cellIndexFor(movePawn.getEnd());
        CellIndex endIndex = board.cellIndexFor(movePawn.getEnd());
        boolean validEnPassant = board.manuals.isValidEnPassant(startIndex, endIndex, board.checkerBoard);
        assertTrue(validEnPassant);
    }

    @Test
    void isValidEnPassantBlack(){
        Board board = new Board();
        Move movePawn = new Move("g7-g5"); // Move Pawn G7-G5
        board.applyMove(movePawn);
        movePawn = new Move("g5-g4"); // Move Pawn G5-G4
        board.applyMove(movePawn);
        movePawn = new Move("f2-f4"); // Move Pawn F2-F4
        board.applyMove(movePawn);
        movePawn = new Move("g4-f3"); // Move Pawn G4-F3 EnPassant
        CellIndex startIndex = board.cellIndexFor(movePawn.getEnd());
        CellIndex endIndex = board.cellIndexFor(movePawn.getEnd());
        boolean validEnPassant = board.manuals.isValidEnPassant(startIndex, endIndex, board.checkerBoard);
        assertTrue(validEnPassant);
    }

    @Test
    void isNotValidEnPassantWhite(){
        Board board = new Board();
        Move movePawn = new Move("g2-g4"); // Move Pawn G2-G4
        board.applyMove(movePawn);
        movePawn = new Move("g4-g5"); // Move Pawn G4-G5
        board.applyMove(movePawn);
        movePawn = new Move("h7-h5"); // Move Pawn H7-H5
        board.applyMove(movePawn);
        movePawn = new Move("h8-h6"); // Move Rook H8-H6
        board.applyMove(movePawn);
        movePawn = new Move("g5-h6"); // Move Pawn G5-H6 EnPassant
        CellIndex startIndex = board.cellIndexFor(movePawn.getEnd());
        CellIndex endIndex = board.cellIndexFor(movePawn.getEnd());
        boolean validEnPassant = board.manuals.isValidEnPassant(startIndex, endIndex, board.checkerBoard);
        assertFalse(validEnPassant);
    }

    @Test
    void isNotValidEnPassantBlack(){
        Board board = new Board();
        Move movePawn = new Move("g2-g4"); // Move Pawn G2-G4
        board.applyMove(movePawn);
        movePawn = new Move("g4-g5"); // Move Pawn G4-G5
        board.applyMove(movePawn);
        movePawn = new Move("e7-e5"); // Move Pawn E7-E5
        board.applyMove(movePawn);
        movePawn = new Move("g5-f6"); // Move Pawn G5-F6 EnPassant
        CellIndex startIndex = board.cellIndexFor(movePawn.getEnd());
        CellIndex endIndex = board.cellIndexFor(movePawn.getEnd());
        boolean validEnPassant = board.manuals.isValidEnPassant(startIndex, endIndex, board.checkerBoard);
        assertFalse(validEnPassant);
    }*/

    //move of right colour
    @Test
    void moveOfRightColour(){
        Board board = new Board();
        Move movePawn = new Move("g2-g4"); // Move Pawn G2-G4
        assertTrue(board.manuals.moveOfRightColour(movePawn, board));
    }

    @Test
    void moveOfFalseColour(){
        Board board = new Board();
        Move movePawn = new Move("g7-g5"); // Move Pawn G7-G5
        assertFalse(board.manuals.moveOfRightColour(movePawn, board));
    }

    @Test
    void moveWithEmptyStartCell(){
        Board board = new Board();
        Move moveEmptyStartCell = new Move("e3-e4");
        assertFalse(board.manuals.moveOfRightColour(moveEmptyStartCell, board));
    }




}