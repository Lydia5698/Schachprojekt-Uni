package chess.model;

import chess.model.Figures.Position;

import java.util.Arrays;
import java.util.List;

public class Move {

    private String start;
    private String end;

    public Move(String input){
        String[] splitInput = input.split("-");
        start = splitInput[0];
        end = splitInput[1];

    }

    static List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }
}
