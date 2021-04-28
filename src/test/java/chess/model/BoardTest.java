package chess.model;

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
}