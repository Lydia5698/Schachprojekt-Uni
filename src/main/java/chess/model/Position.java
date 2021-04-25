package chess.model;

public class Position { //2d array;
    private int vertical; // 1 2 3 4 5 6 7 8
    private int horizont; // A B C D E F G H

    public Position(int vertical, int horizont){
        this.vertical=vertical;
        this.horizont=horizont;
    }
}
