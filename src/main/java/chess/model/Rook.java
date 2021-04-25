package chess.model;

public class Rook extends Minions{ //Turm

    public Rook(Position pos, boolean black) {
        super(pos, black);
        this.minion_type = 'R';
        if(black) {
            print_minion_type = "r";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
}