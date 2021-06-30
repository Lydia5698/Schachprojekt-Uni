package chess.model;

import java.util.ArrayList;
import java.util.List;

public class Opening {
    private int openingNumber;
    private boolean colour; // = true; // true = black, false = white
    private List<Move> openingMoveList;
    private String[] londonOpeningWhite = {"d2-d4", "c1-f4", "g1-f3", "e2-e3", "f1-e2", "e1-g1"};
    private String[] londonOpeningBlack = {"d7-d5", "c7-c6", "c8-f5", "e7-e6", "f8-e7", "g8-f6", "e8-g8"};
    private String[] sicilianOpeningWhite = {"e2-e4", "g1-f3", "d2-d4", "f3-d4", "b1-c3", "a2-a4"};
    private String[] sicilianOpeningBlack = {"c7-c5", "d7-d6", "c5-d4", "g8-f6", "a7-a6", "b8-c6"};
    private String[] italianWhite = {"e2-e4", "g1-f3", "f1-c4"};
    private String[] italianBlack = {"e7-e5", "b8-c6", "f8-e7"};

    public Opening(int openingNumber, boolean isBlack) {
        this.openingNumber = openingNumber;
        this.colour = isBlack;
        generateOpeningMoveList();
    }


    /**
     * Generates the List of Moves for the opening the AI will try to play.
     */
    private void generateOpeningMoveList(){
        if(colour){ //if black
            if (openingNumber == 1) {
                openingMoveList = makeStringArrayToMoveList(londonOpeningBlack);
            } else if (openingNumber == 2) {
                openingMoveList = makeStringArrayToMoveList(sicilianOpeningBlack);
            } else if (openingNumber == 3){
                openingMoveList = makeStringArrayToMoveList(italianBlack);
            }
        }
        else {
            if (openingNumber == 1) {
                openingMoveList = makeStringArrayToMoveList(londonOpeningWhite);
            } else if (openingNumber == 2) {
                openingMoveList = makeStringArrayToMoveList(sicilianOpeningWhite);
            } else if (openingNumber == 3){
                openingMoveList = makeStringArrayToMoveList(italianWhite);
            }
        }
        //return openingMoveList;
    }

    public List<Move> getOpeningMoveList() {
        return openingMoveList;
    }

    public List<Move> makeStringArrayToMoveList(String[] array){
        List<Move> moveList = new ArrayList<>();
        for (String x : array){
            Move move = new Move(x);
            moveList.add(move);
        }
        return moveList;
    }

    public int getOpeningNumber() {
        return openingNumber;
    }
}
