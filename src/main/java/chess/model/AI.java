package chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class AI that represents our artificiall intelligence that should be able to make a next move.
 * @author Jasmin Wojtkiewicz
 */

public class AI {
    public int openingNumber = randomOpeningNumber();
    public boolean colourIsBlack = false;
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
     * @return random integer between minimum and maximum range
     **/
    public static int generateRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

    /**
     * Method, that determines the next move that the AI should make
     * @return move that should be the next Turn for the AI
     */
    public Move getNextMove(){
        //gem move from openingList (place move integer) if exists
        if(turnNumber < moveList.size()) {
            List<Move> moveList = aiOpening.getOpeningMoveList();
            move = moveList.get(turnNumber);
        }
        //get move if it is possible to take

        //generate random possible move

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

    // possible next move that can take (dev: first step: take random piece, sec step: take piece with highest value

}
