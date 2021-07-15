package chess.model;

import chess.Settings;
import chess.model.figures.Minion;
import org.junit.jupiter.api.Test;
import java.util.List;

import static chess.model.Board.cellIndexFor;
import static org.junit.jupiter.api.Assertions.*;

/**
 * All Tests for the Class Manuals
 * @see Manuals
 */


//TODO: test komplexere Testmethoden kommentieren
public class ManualsTest {

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
        Settings settings = new Settings();
        board.setSettings(settings);
        Move movePawn = new Move("e7-e6"); // Move Pawn E7-E6
        board.applyMove(movePawn);
        Move moveBishop = new Move("f8-b4"); // Move Bishop F8-B4
        board.applyMove(moveBishop);
        Move moveBishopCheck = new Move("b4-d2"); //Move B4-D2
        board.applyMove(moveBishopCheck);// white king under attack, list not empty -> false
        boolean attackers = board.manuals.getAttackers(false, board.checkerBoard).isEmpty();
        assertFalse(attackers);
    }

    @Test
    void getAttackersKingBlackUnderAttack() {
        Board board = new Board();
        Settings settings = new Settings();
        board.setSettings(settings);
        Move movePawn = new Move("e2-e4"); // Move Pawn E2-E4
        board.applyMove(movePawn);
        Move moveBishop = new Move("f1-b5"); // Move Bishop F1-B5
        board.applyMove(moveBishop);
        Move moveBishopCheck = new Move("b5-d7"); //Move B5-D7
        board.applyMove(moveBishopCheck);
        boolean attackers = board.manuals.getAttackers(true, board.checkerBoard).isEmpty();
        assertFalse(attackers);
    }


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

    //checkIfPieceCanProtectTheOwnKing
    @Test
    void checkIfPieceCanNotProtectTheOwnKingWhite(){
        Board board = new Board();
        Settings settings = new Settings();
        board.setSettings(settings);
        Move move = new Move("e2-e4");
        board.applyMove(move);
        move = new Move("e7-e5");
        board.applyMove(move);
        move = new Move("f1-c4");
        board.applyMove(move);
        move = new Move("d8-c6");
        board.applyMove(move);
        move = new Move("d1-h5");
        board.applyMove(move);
        move = new Move("g8-f6");
        board.applyMove(move);
        move = new Move("h5-f7");
        CellIndex startIndex = cellIndexFor(move.getStart());
        CellIndex endIndex = cellIndexFor(move.getEnd());
        Cell startCell = board.checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Cell endCell = board.checkerBoard[endIndex.getRow()][endIndex.getColumn()];
        Minion minion = startCell.getMinion();
        startCell.setMinion(null);
        endCell.setMinion(minion);
        CellIndex kingIndex = board.manuals.coordinatesKing(true, board.checkerBoard);
        List<CellIndex> attackersIndex = board.manuals.getAttackersAsIndexList(true, board.checkerBoard);
        CellIndex attackerIndex = attackersIndex.get(0);
        List<CellIndex> attackerPath = board.manuals.spManuals.attackerPath(attackerIndex, kingIndex);
        boolean canProtect = board.manuals.checkIfPieceCanProtectTheOwnKing(board.checkerBoard, attackerPath, true);
        assertFalse(canProtect);
    }
    @Test
    void checkIfPieceCanNotProtectTheOwnKingBlack(){
        Board board = new Board();
        Settings settings = new Settings();
        board.setSettings(settings);
        settings.setGui_active(false);
        Move move = new Move("g2-g4"); // Move Pawn G2-G4
        board.applyMove(move);
        move = new Move("f2-f3"); // Move Pawn F2-F3
        board.applyMove(move);
        move = new Move("e7-e5"); // Move Pawn E7-E5
        board.applyMove(move);
        move = new Move("d8-h4"); // Move Queen D8-H4
        CellIndex endIndex = cellIndexFor(move.getEnd());
        CellIndex startIndex = cellIndexFor(move.getStart());
        Cell startCell = board.checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Cell endCell = board.checkerBoard[endIndex.getRow()][endIndex.getColumn()];
        Minion minion = startCell.getMinion();
        startCell.setMinion(null);
        endCell.setMinion(minion);
        List<CellIndex> attackersIndex = board.manuals.getAttackersAsIndexList(false, board.checkerBoard);
        CellIndex attackerIndex = attackersIndex.get(0);
        CellIndex kingIndex = board.manuals.coordinatesKing(false, board.checkerBoard);
        List<CellIndex> attackerPath = board.manuals.spManuals.attackerPath(attackerIndex, kingIndex);
        boolean canProtect = board.manuals.checkIfPieceCanProtectTheOwnKing(board.checkerBoard, attackerPath, false);
        assertFalse(canProtect);
    }

    @Test
    void checkIfPieceCanProtectTheOwnKingBlack(){
        Board board = new Board();
        Settings settings = new Settings();
        board.setSettings(settings);
        Move move = new Move("a2-a4"); // Move Pawn G2-G4
        board.applyMove(move);
        move = new Move("e7-e5"); // Move Pawn E7-E5
        board.applyMove(move);
        move = new Move("f2-f3"); // Move Pawn F2-F3
        board.applyMove(move);
        move = new Move("d8-h4"); // Move Queen D8-H4
        CellIndex startIndex = cellIndexFor(move.getStart());
        CellIndex endIndex = cellIndexFor(move.getEnd());
        Cell startCell = board.checkerBoard[startIndex.getRow()][startIndex.getColumn()];
        Cell endCell = board.checkerBoard[endIndex.getRow()][endIndex.getColumn()];
        Minion minion = startCell.getMinion();
        startCell.setMinion(null);
        endCell.setMinion(minion);
        CellIndex kingIndex = board.manuals.coordinatesKing(false, board.checkerBoard);
        List<CellIndex> attackersIndex = board.manuals.getAttackersAsIndexList(false, board.checkerBoard);
        CellIndex attackerIndex = attackersIndex.get(0);
        List<CellIndex> attackerPath = board.manuals.spManuals.attackerPath(attackerIndex, kingIndex);
        boolean canProtect = board.manuals.checkIfPieceCanProtectTheOwnKing(board.checkerBoard, attackerPath, false);
        assertTrue(canProtect);
    }

}