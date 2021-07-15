package chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Opening that defines a few chosen common chess openings like the sicilian opening or london opening.
 * @author Jasmin Wojtkiewicz
 */
public class Opening {
    private final int openingNumber;
    private final boolean colour; // = true; // true = black, false = white
    private List<Move> openingMoveList;
    private final String[] londonOpeningWhite = {"d2-d4", "c1-f4", "g1-f3", "e2-e3", "f1-e2", "e1-g1"};
    private final String[] londonOpeningBlack = {"d7-d5", "c7-c6", "c8-f5", "e7-e6", "f8-e7", "g8-f6", "e8-g8"};
    private final String[] sicilianOpeningWhite = {"e2-e4", "g1-f3", "d2-d4", "f3-d4", "b1-c3", "a2-a4"};
    private final String[] sicilianOpeningBlack = {"c7-c5", "d7-d6", "c5-d4", "g8-f6", "a7-a6", "b8-c6"};
    private final String[] italianWhite = {"e2-e4", "g1-f3", "f1-c4"};
    private final String[] italianBlack = {"e7-e5", "b8-c6", "f8-e7"};

    /**
     * Instantiates an Opening with a random chosen opening sequence and a given colour.
     * @param openingNumber the random number that defines the opening sequence
     * @param isBlack the colour of the opening
     */
    public Opening(int openingNumber, boolean isBlack) {
        this.openingNumber = openingNumber;
        this.colour = isBlack;
        generateOpeningMoveList();
    }

    /**
     * Generates the List of Moves for the opening the AI will try to play.
     */
    private void generateOpeningMoveList() {
        if (colour) { //if black
            if (openingNumber == 1) {
                openingMoveList = makeStringArrayToMoveList(londonOpeningBlack);
            } else if (openingNumber == 2) {
                openingMoveList = makeStringArrayToMoveList(sicilianOpeningBlack);
            } else if (openingNumber == 3) {
                openingMoveList = makeStringArrayToMoveList(italianBlack);
            }
        } else { //if white
            if (openingNumber == 1) {
                openingMoveList = makeStringArrayToMoveList(londonOpeningWhite);
            } else if (openingNumber == 2) {
                openingMoveList = makeStringArrayToMoveList(sicilianOpeningWhite);
            } else if (openingNumber == 3) {
                openingMoveList = makeStringArrayToMoveList(italianWhite);
            }
        }
    }

    public List<Move> getOpeningMoveList() {
        return openingMoveList;
    }

    private List<Move> makeStringArrayToMoveList(String[] array) {
        List<Move> moveList = new ArrayList<>();
        for (String x : array) {
            Move move = new Move(x);
            moveList.add(move);
        }
        return moveList;
    }

}
