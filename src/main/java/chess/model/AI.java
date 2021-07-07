package chess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import chess.model.Board;
import chess.model.figures.*;

/**
 * Class AI that represents our artificiall intelligence that should be able to make a next move.
 * @author Jasmin Wojtkiewicz
 */

public class AI {
    public int openingNumber = randomOpeningNumber();
    public boolean colourIsBlack;// = false;
    public int turnNumber=0;
    List<Move> moveList = new ArrayList<>();
    Opening aiOpening;
    Move move;



    //constructor without input
    public AI(boolean colourIsBlack) {
        this.colourIsBlack = colourIsBlack;
        this.aiOpening = new Opening(openingNumber, colourIsBlack);
    }

    /**
     * This methods sets the random opening Number which the AI will try to play
     * @return integer that determines the opening the AI will try to play
     */
    public int randomOpeningNumber(){
        return generateRandomInteger(3,1);
    }

    /**
     *  Generates a random integer between an maximum and minimum integer
     * @param maximum maximum of range
     * @param minimum minimum of range
     * @return random integer between minimum and maximum range
     **/
    public static int generateRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

    /**
     * Method, that determines the next move that the AI should make
     * @param board the actual chessboard at this moment
     * @return move that should be the next Turn for the AI
     *
     */
    public Move getNextMove(Board board){
        List<Move> moveListOpening = aiOpening.getOpeningMoveList();
        List<Move> moveListPossible = possibleNextMoves(board);

        List<Move> takeMoveList = takesMove(moveListPossible, board);
        Move moveT = takeMoveList.get(0);
        String end = moveT.getEnd();
        if (!(end.equals("test"))){
            //good move here~
            System.out.println("ai makes a veryyyy good move"); //TODO delete this line later
            move = moveT;
            return move;

        }
        //gem move from openingList (place move integer) if exists
        else if(turnNumber < moveListOpening.size()) {
            System.out.println("ai opening turn number: " + turnNumber);
            //List<Move> moveList = aiOpening.getOpeningMoveList();
            move = moveListOpening.get(turnNumber);
            //make cellIndex start and cellindex end from move
            CellIndex startCell = Board.cellIndexFor(move.getStart());
            CellIndex endCell = Board.cellIndexFor(move.getEnd());

            // make cell for cellindex start for getting the minion on this field
            Cell[][] checkerBoard = board.getCheckerBoard();
            Cell startCelle = checkerBoard[startCell.getRow()][startCell.getColumn()];
            Minion minion = startCelle.getMinion();
            //TODO check here if legal move, oly return legal moves!!!!!
            if (minion.validMove(startCell, endCell) && board.staleMate.checkLegalMove(startCell, endCell, board.manuals, checkerBoard)&& !(startCell.getRow()==endCell.getRow() &&  startCell.getColumn() == endCell.getColumn())) {
                System.out.println("legal move check for move from openingsequence was succsessful");
                return move;
            }
        }
        //get move if it is possible to take
        else{ //random move is in this else
            int number = generateRandomInteger(moveListPossible.size(), 0);
            System.out.println("random move number: " + Integer.toString(number));
            if(number < moveListPossible.size()) {
                move = moveListPossible.get(number);
            }
            else{ //TODO check for check mate and stalemate!
                move = moveListPossible.get(0);
            }
        }
        //generate random possible move
        //System.out.println("this ai is playing: " );
        return move;
    }


    public int getTurnNumber() {
        return turnNumber;
    }

    /**
     * increases the AI turnNumber by one.
     */
    public void increaseTurnNumber(){
        turnNumber = turnNumber+1;
    }


    /**
     * Generates List of possible Moves for the ai
     * @param board the current board with the current checkerBoard
     * @return list with all possible moves for the ai
     */
    // possible next move
    public List<Move> possibleNextMoves(Board board){
        // make list of all moves
        List <Move> possibleMoves = board.staleMate.possibleMoveList(colourIsBlack, board.checkerBoard);
        //TODO get random move
        return possibleMoves;
    }

    public boolean isColourIsBlack() {
        return colourIsBlack;
    }

    public void setColourIsBlack(boolean colourIsBlack) {
        this.colourIsBlack = colourIsBlack;
    }
// possible next move that can take (dev: first step: take random piece, sec step: take piece with highest value
    /**
     * Looks for the move that captures pieces of the enemy in a list of moves. Please only give this method
     * a list with only valid and legal moves. It is possible that this method returns an empty list.
     * @param moveList the List of possible moves for one colour
     * @return list of moves, that take pieces
     */
    public List<Move> takesMove(List<Move> moveList, Board board){
        // make hashmap
        List <Move> takesMoveList = new ArrayList<>();
        Move bestMove = new Move("aa-test");
        HashMap <String, Integer> maps = new HashMap<String, Integer>();
        maps.put("p", 1);
        maps.put("b", 3);
        maps.put("n", 3);
        maps.put("r", 5);
        maps.put("q", 9);
        maps.put("k", 10);

        int min = -1;
        for (Move move: moveList){ // check every move if it is possible to take a figure and give it a score according to figure
            // check if last field is with piece
            //String endField = move.getEnd();

            CellIndex endIndex = board.cellIndexFor(move.getEnd());
            Cell endCell = board.checkerBoard[endIndex.getRow()][endIndex.getColumn()];

            if (!endCell.isEmpty()){ // if cell not empty look at the piece
                char typ = endCell.getMinion().getMinion_type();
                String type = Character.toString(typ);
                int val = maps.get(type.toLowerCase());
                if (val > min){
                    min = val;
                    bestMove = move;
                    System.out.println("best Move is :" + bestMove.getStart() +"-"+ bestMove.getEnd());

                }
                //score move with number in hashmap /sorted map
            }
        }
        takesMoveList.add(bestMove);
        //sort moves in hashmap or so, (add all remaining moves)

        return takesMoveList;
    }


}
