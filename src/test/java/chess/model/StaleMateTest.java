package chess.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StaleMateTest {

    @Test
    void isStaleMateTrueStaleMate() {
        Board board = new Board();
        StaleMate staleMate = new StaleMate();
        Move move = new Move("e2-e3");
        board.applyMove(move);
        move = new Move("a7-a5");
        board.applyMove(move);
        move = new Move("d1-h5");
        board.applyMove(move);
        move = new Move("a8-a6");
        board.applyMove(move);
        move = new Move("h5-a5");
        board.applyMove(move);
        move = new Move("h7-h5");
        board.applyMove(move);
        move = new Move("a5-c7");
        board.applyMove(move);
        move = new Move("a6-h6");
        board.applyMove(move);
        move = new Move("h2-h4");
        board.applyMove(move);
        move = new Move("f7-f6");
        board.applyMove(move);
        move = new Move("c7-d7");
        board.applyMove(move);
        move = new Move("e8-f7");
        board.applyMove(move);
        move = new Move("d7-b7");
        board.applyMove(move);
        move = new Move("d8-d3");
        board.applyMove(move);
        move = new Move("b7-b8");
        board.applyMove(move);
        move = new Move("d3-h7");
        board.applyMove(move);
        move = new Move("b8-c8");
        board.applyMove(move);
        move = new Move("f7-g6");
        board.applyMove(move);
        move = new Move("c8-e6");
        board.applyMove(move);//white piece(isBlack = false)
        assertTrue(staleMate.isStaleMate(true, board.getCheckerBoard()));//black(true) team stalemate?
    }
}