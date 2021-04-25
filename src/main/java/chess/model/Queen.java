package chess.model;

public class Queen extends Minions{ //Dame

    public Queen(Position pos, boolean black) {
        super(pos, black);
        this.minion_type = 'Q';
        if(black) {
            print_minion_type = "q";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
}
