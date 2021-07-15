package chess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import chess.model.figures.*;

/**
 * Class AI that represents our artificial intelligence that should be able to make a next move.
 *
 * @author Jasmin Wojtkiewicz
 */

public class AI {
    public int openingNumber = randomOpeningNumber();
    public boolean colourIsBlack;//  false = white;
    public int turnNumber = 0;
    Opening aiOpening;
    Move move;

    /**
     * Constructor that creates an instance of the ai with a certain colour and a randomly chosen opening sequence.
     * @param colourIsBlack
     */
    public AI(boolean colourIsBlack) {
        this.colourIsBlack = colourIsBlack;
        this.aiOpening = new Opening(openingNumber, colourIsBlack);
    }

    /**
     * This methods sets the random opening Number which the AI will try to play
     *
     * @return integer that determines the opening the AI will try to play
     */
    public int randomOpeningNumber() {
        return generateRandomInteger(3, 1);
    }

    /**
     * Generates a random integer between an maximum and minimum integer
     *
     * @param maximum maximum of range
     * @param minimum minimum of range
     * @return random integer between minimum and maximum range
     **/
    public static int generateRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    /**
     * Method, that determines the next move that the AI should make
     *
     * @param board the actual chessboard at this moment
     * @return move that should be the next Turn for the AI
     */
    public Move getNextMove(Board board) {
        List<Move> moveListOpening = aiOpening.getOpeningMoveList();
        List<Move> moveListPossible = possibleNextMoves(board);
        List<Move> takeMoveList = takesMove(moveListPossible, board);
        Move moveT = takeMoveList.get(0);
        String end = moveT.getEnd();
        if (!(end.equals("test"))) {
            //good move here~
            move = moveT;
            return move;
        }
        else if (turnNumber < moveListOpening.size()) {
            //List<Move> moveList = aiOpening.getOpeningMoveList();
            move = moveListOpening.get(turnNumber);
            //make cellIndex start and cellindex end from move
            CellIndex startCell = Board.cellIndexFor(move.getStart());
            CellIndex endCell = Board.cellIndexFor(move.getEnd());

            // make cell for cellindex start for getting the minion on this field
            Cell[][] checkerBoard = board.getCheckerBoard();
            Cell startCelle = checkerBoard[startCell.getRow()][startCell.getColumn()];
            Minion minion = startCelle.getMinion();

            if (!startCelle.isEmpty() && minion.validMove(startCell, endCell) && board.staleMate.checkLegalMove(startCell, endCell, board.manuals, checkerBoard) && !(startCell.getRow() == endCell.getRow() && startCell.getColumn() == endCell.getColumn())) {
                return move;
            }
        }
        //random move is in this part
        int number = generateRandomInteger(moveListPossible.size(), 0);
        if(number < moveListPossible.size()) {
            move = moveListPossible.get(number);
        } else {
            move = moveListPossible.get(0);
        }
        return move;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
    public void setTurnNumber(int number){
        this.turnNumber=number;
    }

    /**
     * increases the AI turnNumber by one.
     */
    public void increaseTurnNumber() {
        turnNumber = turnNumber + 1;
    }

    /**
     * Generates List of possible Moves for the ai
     *
     * @param board the current board with the current checkerBoard
     * @return list with all possible moves for the ai
     */
    public List<Move> possibleNextMoves(Board board) {
        return board.staleMate.possibleMoveList(colourIsBlack, board.checkerBoard, board.getMoveList());
    }

    /**
     * Looks for the move that captures pieces of the enemy in a list of moves. Please only give this method
     * a list with only valid and legal moves. It is possible that this method returns an empty list.
     *
     * @param moveList the List of possible moves for one colour
     * @return list of moves, that take pieces
     */
    public List<Move> takesMove(List<Move> moveList, Board board) {
        // make hashmap
        List<Move> takesMoveList = new ArrayList<>();
        Move bestMove = new Move("aa-test");
        HashMap<String, Integer> maps = new HashMap<>();
        maps.put("p", 1);
        maps.put("b", 3);
        maps.put("n", 3);
        maps.put("r", 5);
        maps.put("q", 9);
        maps.put("k", 10);

        int min = -1;
        for (Move move : moveList) { // check every move if it is possible to take a figure and give it a score according to figure
            // check if last field is with piece
            CellIndex endIndex = Board.cellIndexFor(move.getEnd());
            Cell endCell = board.checkerBoard[endIndex.getRow()][endIndex.getColumn()];

            if (!endCell.isEmpty()) { // if cell not empty look at the piece
                char typ = endCell.getMinion().getMinion_type();
                String type = Character.toString(typ);
                int val = maps.get(type.toLowerCase());
                if (val > min) {
                    min = val;
                    bestMove = move;
                }
            }
        }
        takesMoveList.add(bestMove);
        return takesMoveList;
    }
}
