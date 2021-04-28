package chess.model.Figures;

public class Bishop extends Minions { //Läufer

    public Bishop(boolean black) {
        super(black);
        this.minion_type = 'B';
        if(black) {
            print_minion_type = "b";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }


}
