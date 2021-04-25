package chess.model;

public class Knight extends Minions{ // Hotte Hü

    public Knight(Position pos, boolean black) {
        super(pos, black);
        this.minion_type = 'N';
        if(black) {
            print_minion_type = "n";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
}
