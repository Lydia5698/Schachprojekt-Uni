package chess.model;

//import javafx.util.Pair;
import chess.Settings;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

//import java.util.ArrayList;
//import java.util.List;

import java.util.ArrayList;
import java.util.List;

import static chess.model.Board.cellIndexFor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the methods in the StaleMate class.
 * @author Jasmin Wojtkiewicz
 */
public class StaleMateTest {

    @Test
    void isStaleMateTrueStaleMate() {
        Board board = new Board();
        Settings settings = new Settings();
        board.setSettings(settings);
        StaleMate staleMate = new StaleMate();
        Move move = new Move("e2-e3");
        board.applyMove(move);
        board.applyMove(new Move("a7-a5"));
        board.applyMove(new Move("d1-h5"));
        board.applyMove(new Move("a8-a6"));
        board.applyMove(new Move("h5-a5"));
        board.applyMove(new Move("h7-h5"));
        board.applyMove(new Move("a5-c7"));
        board.applyMove(new Move("a6-h6"));
        board.applyMove(new Move("h2-h4"));
        board.applyMove(new Move("f7-f6"));
        board.applyMove(new Move("c7-d7"));
        board.applyMove(new Move("e8-f7"));
        board.applyMove(new Move("d7-b7"));
        board.applyMove(new Move("d8-d3"));
        board.applyMove(new Move("b7-b8"));
        board.applyMove(new Move("d3-h7"));
        board.applyMove(new Move("b8-c8"));
        board.applyMove(new Move("f7-g6"));
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

    @Test
    void possibleMovesForOneFigureTest() {
        Board board = new Board();
        Move moveFirst = new Move("b1-a3");
        CellIndex startIndex = cellIndexFor(moveFirst.getStart());
        List<Pair> possibleMoves = new ArrayList<>();
        Pair pairLeft = new Pair(startIndex, new CellIndex(0,5)); //("b1-a3")
        Pair pairRight = new Pair(startIndex, new CellIndex(0,4)); //("b1-c3")
        possibleMoves.add(pairLeft);
        possibleMoves.add(pairRight);
        List <Pair> possibleMovesFromMethod = new ArrayList<>();
        possibleMovesFromMethod.addAll(board.staleMate.possibleMovesForOneFigure(startIndex, board.getCheckerBoard()));
        assertEquals(possibleMovesFromMethod.get(0).getKey().toString(), possibleMoves.get(0).getKey().toString());
        assertEquals(possibleMovesFromMethod.get(1).getKey().toString(), possibleMoves.get(1).getKey().toString());
    }

    @Test
    void possibleMovesForOneFigureTestMoveList() {
        Board board = new Board();
        Move moveFirst = new Move("a2-a3");
        Move moveSecond = new Move("a2-a4");
        CellIndex startIndex = cellIndexFor(moveFirst.getStart());
        List<Move> possibleMovesFromMethod;
        possibleMovesFromMethod = board.staleMate.possibleMovesForOneFigureMoveList(startIndex, board.checkerBoard, board.getMoveList());
        assertEquals(possibleMovesFromMethod.get(0).getStart(), moveSecond.getStart());
        assertEquals(possibleMovesFromMethod.get(0).getEnd(), moveSecond.getEnd());
        assertEquals(possibleMovesFromMethod.get(1).getStart(), moveFirst.getStart());
        assertEquals(possibleMovesFromMethod.get(1).getEnd(), moveFirst.getEnd());
    }

    @Test
    void attackerPathPawn() {
        Board board = new Board();
        Settings settings = new Settings();
        board.setSettings(settings);
        Move moveFirst = new Move("e2-e4");
        board.applyMove(moveFirst);
        Move moveSecond = new Move("f7-f5");
        board.applyMove(moveSecond);
        CellIndex victim = cellIndexFor(moveSecond.getEnd());
        CellIndex attacker = cellIndexFor(moveFirst.getEnd());
        List<CellIndex> attackerPath;
        attackerPath = board.manuals.spManuals.attackerPath(attacker,victim);
        assertEquals(attackerPath.get(0).getColumn(), attacker.getColumn());
        assertEquals(attackerPath.get(0).getRow(), attacker.getRow());
        assertEquals(attackerPath.get(1).getColumn(), victim.getColumn());
        assertEquals(attackerPath.get(1).getRow(), victim.getRow());
    }
}