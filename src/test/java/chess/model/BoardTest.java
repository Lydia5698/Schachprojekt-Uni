package chess.model;

import chess.model.Figures.Minions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void showBoard() {
        Board board = new Board();
        String output = board.showBoard();
        String expected = "8 r n b q k b n r \n7 p p p p p p p p \n6                 \n5                 \n4                 \n3                 \n2 P P P P P P P P \n1 R N B Q K B N R \n  a b c d e f g h\n";
        assertEquals(expected, output);
    }

    @Test
    void applyMoveE2E4MovesMinionIntoNewCell() {
        Move move = new Move("e2-e4");
        Board board = new Board();
        Cell startCell = board.getCell("e2");
        Minions minion = startCell.getMinion();
        board.applyMove(move);
        assertTrue(startCell.isEmpty());
        Cell endCell = board.getCell("e4");
        assertEquals(endCell.getMinion(), minion);
    }
    @Test
    void applyMoveE2E3MovesMinionIntoNewCell() {
        Move move = new Move("e2-e3");
        Board board = new Board();
        Cell startCell = board.getCell("e2");
        Minions minion = startCell.getMinion();
        board.applyMove(move);
        assertTrue(startCell.isEmpty());
        Cell endCell = board.getCell("e3");
        assertEquals(endCell.getMinion(), minion);
    }


}