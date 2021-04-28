package chess.model.Figures;

import chess.model.Figures.Minions;
import chess.model.Figures.Position;

public class Queen extends Minions { //Dame

    public Queen(boolean black) {
        super(black);
        this.minion_type = 'Q';
        if(black) {
            print_minion_type = "q";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
}
