package chess.model;

import chess.model.figures.Minion;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Class StaleMate with all methods to check for a stalemate.
 *
 * @author Jasmin Wojtkiewicz
 */
public class StaleMate {
    Manuals manuals = new Manuals();

    /**
     * Constructor of StaleMate class.
     */
    public StaleMate() {
    }

    // farbe vom gegner reingeben weiß am zug, fuer black ueberpruefen
    // feld durchgehen, schaue nach schwarzer figur
    // schwarze figur kann sie sich bewegen, alle die sich bewegen können werden zur movelist hinzugefuegt
    // steht schwarz im schachmatt/schach

    //method which fields have piece of colour b and is there possible move

    /**
     * Method isStaleMate checks if a team of a certain colour can not move and is not in check, so it is a stalemate.
     *
     * @param isBlack      boolean of the colour of the team that has to be checked, if it is able to move
     * @param checkerBoard checkerboard at this time
     * @return boolean whether the team is able to move (false) or not (true)
     */
    protected boolean isStaleMate(boolean isBlack, Cell[][] checkerBoard) {
        boolean staleMate = false;
        List<Pair> moveList = new ArrayList<>();
        // check every field for figure of certain colour if colour, possibleMoves
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = checkerBoard[row][col];
                if (!cell.isEmpty() && cell.getMinion().isBlack() == isBlack) {
                    moveList.addAll(possibleMovesForOneFigure(new CellIndex(row, col), checkerBoard));
                }
            }
        }
        if (moveList.isEmpty()) {
            staleMate = true;
        }
        return staleMate;
    }

    /**
     * The method possibleMovesForOneFigure checks for all possible moves of the figure whether they are legal or not.
     *
     * @param cellIndex    starting field of the piece that is checked for legal moves
     * @param checkerBoard the checkerboard at this time
     * @return list of pairs of cell indices with all legal moves for this piece
     */
    //method, welche moves ok sind fuer ein piece
    public List<Pair> possibleMovesForOneFigure(CellIndex cellIndex, Cell[][] checkerBoard) {
        List<Pair> possibleMoves = new ArrayList<>();
        Cell cell = checkerBoard[cellIndex.getRow()][cellIndex.getColumn()];
        Minion minion = cell.getMinion();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                CellIndex end = new CellIndex(row, col);
                //check if move is legal
                if (minion.validMove(cellIndex, end) && checkLegalMove(cellIndex, end, manuals, checkerBoard)) {
                    //check ifLegalMove
                    Pair pair = new Pair<>(cellIndex, end);
                    possibleMoves.add(pair);
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Method checkLegalMove checks if a move from one field to another is a legal move for the piece
     * on the starting field.
     *
     * @param startIndex   is the position of the starting field of the move to be tested
     * @param endIndex     is the position of the end field of the move to be tested
     * @param manuals      manuals, so that checkMoveMakesNoSelfCheck can be called
     * @param checkerBoard the checkerboard at this time
     * @return boolean whether the move is legal (true) or not (false)
     */
    protected boolean checkLegalMove(CellIndex startIndex, CellIndex endIndex, Manuals manuals, Cell[][] checkerBoard) {
        boolean isLegal = false;
        if (manuals.checkIfValidMove(startIndex, endIndex, checkerBoard) && manuals.checkMoveMakesNoSelfCheck(startIndex, endIndex, checkerBoard, manuals)) {
            isLegal = true;
            return isLegal;
        }
        return isLegal;
    }


    /**
     * The method possibleMovesForOneFigureMoveList adds all possible Moves for one figuere to a moves List.
     *
     * @param cellIndex    starting field of the piece that is checked for legal moves
     * @param checkerBoard the checkerboard at this time
     * @return list of moves with all legal moves for this piece
     */
    //method, welche moves ok sind fuer ein piece
    public List<Move> possibleMovesForOneFigureMoveList(CellIndex cellIndex, Cell[][] checkerBoard, List<Move> moveList) {
        //TODO bauern schlagen und en passante einbauen!!!!!!!!!!!!!!
        List<Move> possibleMoves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                CellIndex end = new CellIndex(row, col);
                //check if move is legal
                if (manuals.checkIfValidMove(cellIndex,end,checkerBoard) && checkLegalMove(cellIndex, end, manuals, checkerBoard) && !(cellIndex.getRow() == end.getRow() && cellIndex.getColumn() == end.getColumn())
                && (manuals.spManuals.isValidEnPassant(cellIndex,end,checkerBoard,moveList) || manuals.spManuals.figureRochadeHasMoved(moveList,cellIndex,end,checkerBoard))) {
                    //check ifLegalMove
                    // make index into string
                    String input = cellIndex.makeIndexIntoString(cellIndex, end);
                    Move move = new Move(input);
                    possibleMoves.add(move);


                }
            }
        }
        return possibleMoves;
    }

    /**
     * Method that gives back a possible List of moves for one colour.
     *
     * @param isBlack      the colour of the moves that should be generated.
     * @param checkerBoard the current checkerBoard.
     * @return List of pairs of the moves possible for one colour.
     */
    public List<Move> possibleMoveList(boolean isBlack, Cell[][] checkerBoard, List<Move> boardMoveList) {

        boolean staleMate = false;
        List<Move> moveList = new ArrayList<>();
        // check every field for figure of certain colour if colour, possibleMoves
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = checkerBoard[row][col];
                if (!cell.isEmpty() && cell.getMinion().isBlack() == isBlack) {
                    moveList.addAll(possibleMovesForOneFigureMoveList(new CellIndex(row, col), checkerBoard,boardMoveList));
                }
            }
        }
        return moveList;
    }

}
