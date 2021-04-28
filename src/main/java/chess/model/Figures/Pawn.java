package chess.model.Figures;

public class Pawn extends Minions { //Bauer

    public Pawn(boolean black) {
        super(black);
        this.minion_type = 'P';
        if(black) {
            print_minion_type = "p";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
}