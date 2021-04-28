package chess.model.Figures;

public class Position { //2d array;
    int vertical; // 1 2 3 4 5 6 7 8
    int horizont; // A B C D E F G H

    public Position(int vertical, int horizont){
        this.vertical=vertical;
        this.horizont=horizont;
    }

    public int getVertical() {
        return vertical;
    }

   public int getHorizont() {
        return horizont;
    }


}
