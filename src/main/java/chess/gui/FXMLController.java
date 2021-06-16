package chess.gui;

import chess.gui.Gui;
import chess.model.*;
import chess.model.figures.Minion;
import chess.model.figures.Rook;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FXMLController {
    protected static Board board = new Board();
    protected static Manuals manuals = new Manuals();
    protected static StaleMate staleMate = new StaleMate();

    private final Gui gui = new Gui(); //conbtroller f. game
    private final List<String> halfMoves = new ArrayList<>();
    private final List<Event> position = new ArrayList<>();
    private int counter = 0;
    private int counterBeatenMinionsWhite = 0;
    private int counterBeatenMinionsBlack = 0;

    @FXML
    private void b_neuesSpiel(){

    }


    @FXML
    private Button btnSpielstart;

    @FXML
    private Button btnOptions;

    @FXML
    private Button btnCredits;

    @FXML
    private Button btnAnleitung;

    @FXML
    private Button btnStartScreen;

    @FXML
    private Button btnChessBoard;

    @FXML
    private GridPane chessBoard;

    @FXML
    private GridPane beatenMinion;

    @FXML
    private Text beatenText;

    public void showStartScreen(){
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        gui.show_FXML("startScreen.fxml", stage);
    }
    public void showAnleitung(){
        Stage stage = (Stage) btnAnleitung.getScene().getWindow();
        gui.show_FXML("anleitung.fxml", stage);
    }
    public void showCredits(){
        Stage stage = (Stage) btnCredits.getScene().getWindow();
        gui.show_FXML("credits.fxml", stage);

    }
    public void showGui(){
        Stage stage = (Stage) btnChessBoard.getScene().getWindow();
        gui.show_FXML("gui.fxml", stage);
    }
    public void showOptions(){
        Stage stage = (Stage) btnOptions.getScene().getWindow();
        gui.show_FXML("options.fxml", stage);
    }
    public void showSpielauswahl(){
        Stage stage = (Stage) btnSpielstart.getScene().getWindow();
        gui.show_FXML("spielauswahl.fxml", stage);
    }

    public void exit(){
        System.exit(0);

    }

    @FXML
    private ImageView pawnBlack;

    @FXML
    void mouseClicked(MouseEvent event) {
        Node source = (Node)event.getSource() ;

        int colIndex;
        int rowIndex;
        if(GridPane.getColumnIndex(source) == null){
            colIndex = 0;
        }
        else{
            colIndex = GridPane.getColumnIndex(source);
        }
        if(GridPane.getRowIndex(source) == null){
            rowIndex = 8;
        }
        else{
            rowIndex = 8 - GridPane.getRowIndex(source);
        }
        showPossibleMoves(colIndex, rowIndex);

        List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        String input = columns.get(colIndex) + rowIndex;
        halfMoves.add(input);
        position.add(event);
        System.out.println(input);
        counter++;
        move();

        //boardRota
        //white turn
        ImageView iv;

        if(board.isBlackIsTurn() == false) {
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    switch (board.getCheckerBoard()[i][j].getMinion().getMinion_type()) {
                        case 'K': iv = new ImageView(new Image("ChessFigures/KingWhite.png"));
                            break;
                        case 'Q': iv = new ImageView(new Image("ChessFigures/QueenWhite.png"));
                            break;
                        case 'N': iv = new ImageView(new Image("ChessFigures/KnightWhite.png"));
                            break;
                        case 'B': iv = new ImageView(new Image("ChessFigures/BishopWhite.png"));
                            break;
                        case 'R': iv = new ImageView(new Image("ChessFigures/RookWhite.png"));
                            break;
                        case 'P': iv = new ImageView(new Image("ChessFigures/PawnWhite.png"));
                            break;
                        case 'k': iv = new ImageView(new Image("ChessFigures/KingWBlack.png"));
                            break;
                        case 'q': iv = new ImageView(new Image("ChessFigures/QueenBlack.png"));
                            break;
                        case 'n': iv = new ImageView(new Image("ChessFigures/KnightBlack.png"));
                            break;
                        case 'b': iv = new ImageView(new Image("ChessFigures/BishopBlack.png"));
                            break;
                        case 'r': iv = new ImageView(new Image("ChessFigures/RookBlack.png"));
                            break;
                        case 'p': iv = new ImageView(new Image("ChessFigures/PawnBlack.png"));
                            break;
                        default: iv = new ImageView(new Image(""));
                    }
                    chessBoard.add(iv, i, j);
                }
            }
        }

            //black turn
        else if(board.isBlackIsTurn() == true){
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    switch (board.getCheckerBoard()[i][j].getMinion().getMinion_type()) {
                        case 'K': iv = new ImageView(new Image("ChessFigures/KingWhite.png"));
                            break;
                        case 'Q': iv = new ImageView(new Image("ChessFigures/QueenWhite.png"));
                            break;
                        case 'N': iv = new ImageView(new Image("ChessFigures/KnightWhite.png"));
                            break;
                        case 'B': iv = new ImageView(new Image("ChessFigures/BishopWhite.png"));
                            break;
                        case 'R': iv = new ImageView(new Image("ChessFigures/RookWhite.png"));
                            break;
                        case 'P': iv = new ImageView(new Image("ChessFigures/PawnWhite.png"));
                            break;
                        case 'k': iv = new ImageView(new Image("ChessFigures/KingWBlack.png"));
                            break;
                        case 'q': iv = new ImageView(new Image("ChessFigures/QueenBlack.png"));
                            break;
                        case 'n': iv = new ImageView(new Image("ChessFigures/KnightBlack.png"));
                            break;
                        case 'b': iv = new ImageView(new Image("ChessFigures/BishopBlack.png"));
                            break;
                        case 'r': iv = new ImageView(new Image("ChessFigures/RookBlack.png"));
                            break;
                        case 'p': iv = new ImageView(new Image("ChessFigures/PawnBlack.png"));
                            break;
                        default: iv = new ImageView(new Image(""));
                    }
                    chessBoard.add(iv, 7-i, 7-j);
                }
            }
        }
    }

    public void move(){
        if(counter == 2){
            String fistField = halfMoves.get(0);
            String secondField = halfMoves.get(1);
            String input = fistField + "-" + secondField;
            Move move = new Move(input);
            System.out.println(input);
            if (manuals.moveOfRightColour(move, board)) {
                board.applyMove(move);
            }
            else {
                System.out.println("!Move not allowed");
                board.setAllowedMove(false);
            }

            if(board.isAllowedMove()){
                Event start = position.get(0);
                Event end = position.get(1);
                Node sourceEnd = (Node)end.getSource();
                Node sourceStart = (Node)start.getSource();
                Integer endCol = GridPane.getColumnIndex(sourceEnd);
                Integer endRow = GridPane.getRowIndex(sourceEnd);
                GridPane.setColumnIndex(sourceStart, endCol);
                GridPane.setRowIndex(sourceStart, endRow);

                sourceEnd.toBack();
                sourceStart.toFront();

                beatenMinions(sourceEnd);

            }
            counter = 0;
            halfMoves.clear();
            position.clear();
        }

    }

    private void beatenMinions(Node sourceEnd) {
        if(board.getBeaten().size() == 1){
            String minion = board.getBeaten().get(0);
            char minionType = minion.charAt(0);
            if(Character.isUpperCase(minionType)){
                //sourceEnd.set
                beatenMinion.add(sourceEnd,0,counterBeatenMinionsWhite);
                counterBeatenMinionsWhite++;

            }
            else {
                beatenMinion.add(sourceEnd,1,counterBeatenMinionsBlack);
                counterBeatenMinionsBlack++;
            }
            board.getBeaten().clear();
        }
    }

    public void showPossibleMoves(int startCol, int startRow){
        CellIndex startIndex = new CellIndex(startRow, startCol);
        List<Pair> possibleMoves = new ArrayList<>(staleMate.possibleMovesForOneFigure(startIndex, board.getCheckerBoard()));
        CellIndex endIndex = (CellIndex) possibleMoves.get(1).getValue();
        Node endNode = getNodeByCoordinate(endIndex.getRow(), endIndex.getColumn());
        endNode.toFront();
    }

    Node getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : chessBoard.getChildren()) {
            if(GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }



}
