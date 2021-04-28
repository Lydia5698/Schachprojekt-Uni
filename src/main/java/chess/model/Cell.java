package chess.model;

import chess.model.Figures.Minions;
import chess.model.Figures.Position;


public class Cell {
    public Minions getMinion() {
        return minion;
    }

    Minions minion;

    public Cell(Minions minion){
        this.minion = minion;
    }

    public String toString(){
        if (minion == null){
            return " ";
        }
        return minion.print_minions();
    }




}

