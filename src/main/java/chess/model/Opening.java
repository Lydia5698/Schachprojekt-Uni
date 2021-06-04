package chess.model;

import java.util.List;

public class Opening {
    private int openingNumber;
    private List<Move> openingMoveList;
    private List londonOpening;
    private List sicilianOpening;
    private List italian;

    public Opening(int openingNumber) {
        this.openingNumber = openingNumber;
    }

    public List <Move> getOpeningMoveList(){
        
        return openingMoveList;
    }
}
