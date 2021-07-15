package chess.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import chess.model.AI;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AITest {


    @Test
    void getNextMoveWhiteAI() {
        AI aiNext = new AI(false);
        Board board1= new Board();
        Move move1 = aiNext.getNextMove(board1);
        String moveStart = move1.getStart();
        String moveEnd = move1.getEnd();
        boolean sameMoves = false;
        if(("e2".equals(moveStart)||"d2".equals(moveStart))&&("e4".equals(moveEnd)||"d4".equals(moveEnd))){
            sameMoves=true;
        }
        Assertions.assertTrue(sameMoves);
    }

    @Test
    void getNextMoveBlackAI() {
        AI aiNextB = new AI(true);
        Board board1= new Board();
        Move move2 = aiNextB.getNextMove(board1);
        String moveStart = move2.getStart();
        String moveEnd = move2.getEnd();
        boolean sameMoves = false;
        if(("d7".equals(moveStart)||"c7".equals(moveStart)||"e7".equals(moveStart))&&("e5".equals(moveEnd)||"d5".equals(moveEnd)||"c5".equals(moveEnd))){
            sameMoves=true;
        }
        Assertions.assertTrue(sameMoves);
    }

    @Test
    void getTurnNumber() {
        AI ai = new AI(true);
        int number = ai.getTurnNumber();
        Assertions.assertEquals(0,number);
    }

    @Test
    void setTurnNumber() {
        AI aiSetNumber = new AI(false);
        aiSetNumber.setTurnNumber(7);
        Assertions.assertEquals(7, aiSetNumber.getTurnNumber());
    }

    @Test
    void increaseTurnNumber() {
        AI aiIncrease = new AI(true);
        aiIncrease.increaseTurnNumber();
        aiIncrease.increaseTurnNumber();
        Assertions.assertEquals(2, aiIncrease.getTurnNumber());
    }

    @Test
    void takesMove() {
        AI aiTakes = new AI(true);
        Board board= new Board();
        List<Move> possibleMoves = aiTakes.possibleNextMoves(board);
        List<Move> takesList = aiTakes.takesMove(possibleMoves, board);
        String moveEnd = takesList.get(0).getEnd();
        Assertions.assertEquals("test", moveEnd);

    }
}