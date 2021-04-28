package chess.model.Figures;

public class King extends Minions{ //König

    public King(boolean black) {
        super(black);
        this.minion_type = 'K';
        if(black) {
            print_minion_type = "k";
        }
        else{
            print_minion_type = String.valueOf(minion_type);
        }
    }
}