package chess.model.Figures;

import chess.model.Figures.Minions;
import chess.model.Figures.Position;

public class Rook extends Minions { //Turm

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