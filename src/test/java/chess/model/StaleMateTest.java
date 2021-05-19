package chess.model;

//import javafx.util.Pair;
import org.junit.jupiter.api.Test;

//import java.util.ArrayList;
//import java.util.List;

import static chess.model.Board.cellIndexFor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the methods in the StaleMate class.
 * @author Jasmin Wojtkiewicz
 */
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

    @Test
    void checkLegalMoveThatIsLegalTest() {
        Board board = new Board();
        Move pawnMove = new Move("e2-e3");
        CellIndex startIndex = cellIndexFor(pawnMove.getStart());
        CellIndex endIndex = cellIndexFor(pawnMove.getEnd());
        assertTrue(board.staleMate.checkLegalMove(startIndex, endIndex, board.manuals, board.getCheckerBoard()));

    }

    @Test
    void checkLegalMoveThatIsNotLegalTest() {
        Board board = new Board();
        Move towerMove = new Move("a1-a5");
        CellIndex startIndex = cellIndexFor(towerMove.getStart());
        CellIndex endIndex = cellIndexFor(towerMove.getEnd());
        assertFalse(board.staleMate.checkLegalMove(startIndex, endIndex, board.manuals, board.getCheckerBoard()));

    }

    /*@Test
    void possibleMovesForOneFigureTest() {
        Board board = new Board();
        CellIndex whiteKnightIndex = new CellIndex(7,1);
        List<Pair> possibleMoves = new ArrayList<>();
        Pair pairLeft = new Pair(whiteKnightIndex, new CellIndex(5,0)); //("b1-a3")
        Pair pairRight = new Pair(whiteKnightIndex, new CellIndex(5,2)); //("b1-c3")
        possibleMoves.add(pairLeft);
        possibleMoves.add(pairRight);
        List <Pair> possibleMovesFromMethod = new ArrayList<>();
        possibleMovesFromMethod.addAll(board.staleMate.possibleMovesForOneFigure(whiteKnightIndex, board.getCheckerBoard()));
        assertEquals(possibleMovesFromMethod.get(0).getKey(), possibleMoves.get(0).getKey());
        assertEquals(possibleMovesFromMethod.get(0).getValue(), possibleMoves.get(0).getValue());
        assertEquals(possibleMovesFromMethod.get(1).getKey(), possibleMoves.get(1).getKey());
        assertEquals(possibleMovesFromMethod.get(1).getValue(), possibleMoves.get(1).getValue());

    }*/
}