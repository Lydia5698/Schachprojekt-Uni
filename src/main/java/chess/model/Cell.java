package chess.model;

import chess.model.Figures.Minion;

/**
 * A Cell is an object that contains other objects.
 *
 * @author Lydia Günther
 * @version ?
 * @since ?
 */
public class Cell {
    /**
     * Create a Cell with an Minion in it
     *
     * @param minion a chess minion
     * @see Minion
     */
    public Cell(Minion minion) {
        this.minion = minion;
    }

    private Minion minion;

    public Minion getMinion() {
        return minion;
    }

    /**
     * Overrides the methode toString, so that a empty cell has an empty String and not null
     * a Cell with a Minion calls there Methode print_minions and returns there String print_minion_type
     *
     * @return minion.print_minions or " " empty String
     * @see Minion
     */
    @Override
    public String toString() {
        if (minion == null) {
            return " ";
        }
        return minion.print_minions();
    }

    /**
     * Checks if Cell is empty and returns a boolean
     *
     * @return boolean if Empty or not
     */
    public boolean isEmpty() {
        return minion == null;
    }

    /**
     * Sets the Minion in a Cell
     *
     * @param minion sets the Minion in the Cell
     */
    public void setMinion(Minion minion) {
        this.minion = minion;
    }
}

