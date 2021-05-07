package chess.model;

public class Vector {
    private Position nextPos;
    private Position origin;

    public Vector(Position origin, Position nextPos){
        this.nextPos = nextPos;
        this.origin = origin;
    }

    public Position getNextPos() {
        return nextPos;
    }

    public Position getOrigin() {
        return origin;
    }
}
