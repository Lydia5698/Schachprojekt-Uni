package chess.model.Figures;

public class Knight extends Minions { // Hotte Hü

    public Knight(boolean black) {
        super(black);
        this.minion_type = 'N';
        if(black) {
            print_minion_type = "n";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
}
