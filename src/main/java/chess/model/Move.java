package chess.model;

import java.util.Arrays;
import java.util.List;

/**
 * Move is a move form a Minion from a Cell to another Cell. Every move has an Start and End point
 *
 * @author Lydia Günther
 * @version ?
 * @since ?
 */
public class Move {

    private String start;
    private String end;

    /**
     * Splits the input from the Console? in a Start and End point. And saves them in the private Strings start and end
     *
     * @param input is the input from the Console(Cli)
     * @see chess.cli.Cli
     */
    public Move(String input) {
        String[] splitInput = input.split("-");
        start = splitInput[0];
        end = splitInput[1];

    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }
}
