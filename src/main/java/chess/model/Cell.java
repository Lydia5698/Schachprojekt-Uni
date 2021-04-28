package chess.model;

import chess.model.Figures.Minions;
import chess.model.Figures.Position;


public class Cell {

    private Minions minion;

    public Minions getMinion() {
        return minion;
    }



    public Cell(Minions minion){
        this.minion = minion;
    }

    public String toString(){
        if (minion == null){
            return " ";
        }
        return minion.print_minions();
    }


    public boolean isEmpty() {
        return minion == null;
    }

    public void setMinion(Minions minion) {
        this.minion = minion;
    }
}

