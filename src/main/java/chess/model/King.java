package chess.model;

public class King extends Minions{ //König

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
