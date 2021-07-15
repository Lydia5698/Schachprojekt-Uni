package chess.model;

import chess.Settings;
import chess.model.figures.Minion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * All Tests for the Class Board
 * @see Board
 */
public class BoardTest {

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
        Settings settings = new Settings();
        board.setSettings(settings);
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
        CellIndex index = Board.cellIndexFor(input);
        assertEquals(index.getRow(),8-2);
        assertEquals(index.getColumn(),1);

    }

    @Test
    void checkIfCurrentMoveIsValid(){
        Board board = new Board();
        Settings settings = new Settings();
        board.setSettings(settings);
        Move move = new Move("e2-e4");
        CellIndex startIndex = Board.cellIndexFor(move.getStart());
        Cell startCell = board.checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Minion startMinion = startCell.getMinion();
        board.checkCurrentMove(move,startMinion,"");
        assertTrue(board.isAllowedMove());
    }

    @Test
    void checkIfCurrentMoveIsNotValid(){
        Board board = new Board();
        Settings settings = new Settings();
        board.setSettings(settings);
        Move move = new Move("e1-e5");
        CellIndex startIndex = Board.cellIndexFor(move.getStart());
        Cell startCell = board.checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Minion startMinion = startCell.getMinion();
        board.checkCurrentMove(move,startMinion,"");
        assertFalse(board.isAllowedMove());
    }

    @Test
    void checkIfCheck(){
        Board board = new Board();
        Settings settings = new Settings();
        board.setSimple(true);
        board.setSettings(settings);
        board.applyMove(new Move("e2-e4"));
        assertFalse(board.settings.isPlayerInCheck());
        board.applyMove(new Move("f7-f5"));
        board.settings.getSettingsLanguage().changeLanguage();
        board.applyMove(new Move("d1-h5"));
        assertTrue(board.settings.isPlayerInCheck());
        board.setSimple(false);
        board.applyMove(new Move("g7-g6"));
        assertFalse(board.settings.isPlayerInCheck());
        board.settings.getSettingsLanguage().changeLanguage();
        board.applyMove(new Move("h5-g6"));
        assertTrue(board.settings.isPlayerInCheck());
    }
}