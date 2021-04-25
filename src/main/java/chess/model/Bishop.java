package chess.model;

public class Bishop extends Minions{ //Läufer

    public Bishop(Position pos, boolean black) {
        super(pos, black);
        this.minion_type = 'B';
        if(black) {
            print_minion_type = "b";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
}
