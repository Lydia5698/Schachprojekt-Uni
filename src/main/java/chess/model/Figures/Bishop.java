package chess.model.Figures;

public class Bishop extends Minions { //Läufer

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

    public static class King extends Minions{ //König

        public King(Position pos, boolean black) {
            super(pos, black);
            this.minion_type = 'K';
            if(black) {
                print_minion_type = "k";
            }
                else{
                    print_minion_type = String.valueOf(minion_type);
                }
        }
    }
}
