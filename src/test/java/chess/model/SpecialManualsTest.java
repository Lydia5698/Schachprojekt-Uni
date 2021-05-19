package chess.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static chess.model.Board.cellIndexFor;
import static org.junit.jupiter.api.Assertions.*;

class SpecialManualsTest {

    // EnPassant Tests
    @Test
    void isValidEnPassantWhite(){
        Board board = new Board();
        ArrayList<Move> moveList = board.getMoveList();
        Move movePawn = new Move("d2-d4"); // Move Pawn D2-D4
        board.applyMove(movePawn);
        moveList.add(movePawn);
        movePawn = new Move("d4-d5"); // Move Pawn D4-D5
        board.applyMove(movePawn);
        moveList.add(movePawn);
        movePawn = new Move("e7-e5"); // Move Pawn E7-E5
        board.applyMove(movePawn);
        moveList.add(movePawn);
        movePawn = new Move("d5-e6"); // Move Pawn D5-E6 EnPassant
        CellIndex startIndex = cellIndexFor(movePawn.getStart());
        CellIndex endIndex = cellIndexFor(movePawn.getEnd());
        boolean validEnPassant = board.spManuals.isValidEnPassant(startIndex, endIndex, board.checkerBoard, board.getMoveList());
        assertTrue(validEnPassant);
    }

    @Test
    void isValidEnPassantBlack(){
        Board board = new Board();
        ArrayList<Move> moveList = board.getMoveList();
        Move movePawn = new Move("g7-g5"); // Move Pawn G7-G5
        board.applyMove(movePawn);
        moveList.add(movePawn);
        movePawn = new Move("g5-g4"); // Move Pawn G5-G4
        board.applyMove(movePawn);
        moveList.add(movePawn);
        movePawn = new Move("f2-f4"); // Move Pawn F2-F4
        board.applyMove(movePawn);
        moveList.add(movePawn);
        movePawn = new Move("g4-f3"); // Move Pawn G4-F3 EnPassant
        CellIndex startIndex = cellIndexFor(movePawn.getStart());
        CellIndex endIndex = cellIndexFor(movePawn.getEnd());
        boolean validEnPassant = board.spManuals.isValidEnPassant(startIndex, endIndex, board.checkerBoard, board.getMoveList());
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
        CellIndex startIndex = cellIndexFor(movePawn.getStart());
        CellIndex endIndex = cellIndexFor(movePawn.getEnd());
        boolean validEnPassant = board.spManuals.isValidEnPassant(startIndex, endIndex, board.checkerBoard, board.getMoveList());
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
        CellIndex startIndex = cellIndexFor(movePawn.getStart());
        CellIndex endIndex = cellIndexFor(movePawn.getEnd());
        boolean validEnPassant = board.spManuals.isValidEnPassant(startIndex, endIndex, board.checkerBoard, board.getMoveList());
        assertFalse(validEnPassant);
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
        CellIndex endIndexPawn = cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isQueen = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("Q");
        assertTrue(isQueen);
    }

    @Test
    void promoteWhitePawnToRook(){
        Board board = new Board();
        Move movePawn = new Move("g2-g4"); // Move Pawn G2-G4
        board.applyMove(movePawn);
        movePawn = new Move("g4-g5"); // Move Pawn G4-G5
        board.applyMove(movePawn);
        movePawn = new Move("g5-g6"); // Move Pawn G5-G6
        board.applyMove(movePawn);
        movePawn = new Move("g6-f7"); // Move Pawn G6-F7
        board.applyMove(movePawn);
        movePawn = new Move("f7-g8R"); // Move Pawn F7-G8 to Rook
        board.applyMove(movePawn);
        CellIndex endIndexPawn = cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isRook = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("R");
        boolean isCheck = board.manuals.isCheck(!(endCellPawn.getMinion().isBlack()), board.checkerBoard, board.manuals);
        assertTrue(isRook && !isCheck);
    }

    @Test
    void promoteWhitePawnToKnight(){
        Board board = new Board();
        Move movePawn = new Move("f2-f4"); // Move Pawn F2-F4
        board.applyMove(movePawn);
        movePawn = new Move("f4-f5"); // Move Pawn F4-F5
        board.applyMove(movePawn);
        movePawn = new Move("f5-f6"); // Move Pawn F5-F6
        board.applyMove(movePawn);
        movePawn = new Move("f6-e7"); // Move Pawn F6-E7
        board.applyMove(movePawn);
        movePawn = new Move("e7-d8N"); // Move Pawn E7-D8 to Knight
        board.applyMove(movePawn);
        CellIndex endIndexPawn = cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isKnight = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("N");
        boolean isCheck = board.manuals.isCheck(!(endCellPawn.getMinion().isBlack()), board.checkerBoard, board.manuals);
        assertTrue(isKnight && !isCheck);
    }

    @Test
    void promoteWhitePawnToBishop(){
        Board board = new Board();
        Move movePawn = new Move("e2-e4"); // Move Pawn E2-E4
        board.applyMove(movePawn);
        movePawn = new Move("e4-e5"); // Move Pawn E4-E5
        board.applyMove(movePawn);
        movePawn = new Move("e5-e6"); // Move Pawn E5-E6
        board.applyMove(movePawn);
        movePawn = new Move("e6-d7"); // Move Pawn E6-D7
        board.applyMove(movePawn);
        movePawn = new Move("d7-c8B"); // Move Pawn D7-C8 to Bishop
        board.applyMove(movePawn);
        CellIndex endIndexPawn = cellIndexFor(movePawn.getEnd());
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
        CellIndex endIndexPawn = cellIndexFor(movePawn.getEnd());
        Cell endCellPawn = board.checkerBoard[endIndexPawn.row][endIndexPawn.column];
        boolean isQueen = String.valueOf(endCellPawn.getMinion().getMinion_type()).equals("Q");
        assertTrue(isQueen);
    }

}