package chess.model;

import chess.model.figures.Minion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All Tests for the Class Board
 * @see Board
 */
class BoardTest {

    @Test
    void showBoardTest() {
        Board board = new Board();
        String output = board.showBoard();
        String expected = "8 r n b q k b n r \n7 p p p p p p p p \n6                 \n5                 \n4                 \n3                 \n2 P P P P P P P P \n1 R N B Q K B N R \n  a b c d e f g h\n";
        assertEquals(expected, output);
    }

    @Test
    void applyMoveE2E4MovesMinionIntoNewCellTest() {
        Move move = new Move("e2-e4");
        Board board = new Board();
        Cell startCell = board.checkerBoard[8-2][4];//board.getCell("e2");
        Minion minion = startCell.getMinion();
        board.applyMove(move);
        assertTrue(startCell.isEmpty());
        Cell endCell = board.checkerBoard[8-4][4];
        assertEquals(endCell.getMinion(), minion);
    }

    @Test
    void validMoveA1A5CheckIfValidMoveTest() {
        Move move = new Move("a1-a5");
        Board board = new Board();
        CellIndex startIndex = Board.cellIndexFor(move.getStart());
        CellIndex endIndex = Board.cellIndexFor(move.getEnd());
        Cell startCell = board.checkerBoard[7][0];
        Minion minion = startCell.getMinion();
        assertTrue(minion.validMove(startIndex, endIndex));

    }

    @Test
    void cellIndexForB2GetsCellIndexInNumbersTest(){
        String input = "b2";
        Board board =new Board();
        CellIndex index = Board.cellIndexFor(input);
        assertEquals(index.getRow(),8-2);
        assertEquals(index.getColumn(),1);

    }
}