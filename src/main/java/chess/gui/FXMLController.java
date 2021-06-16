package chess.gui;

import chess.model.*;
import javafx.event.ActionEvent;
import chess.model.figures.Minion;
import chess.model.figures.Rook;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.*;

public class FXMLController {
    protected static Board board = new Board();
    protected static Manuals manuals = new Manuals();
    protected static StaleMate staleMate = new StaleMate();
    protected static SpecialManuals spManuals = new SpecialManuals();

    private final Gui gui = new Gui(); //conbtroller f. game
    private final List<String> halfMoves = new ArrayList<>();
    private final List<Event> position = new ArrayList<>();
    private int counter = 0;
    private int counterBeatenMinionsWhite = 0;
    private int counterBeatenMinionsBlack = 0;
    private String promoteTo = "Q";
    private boolean promotion = false;
    private boolean aiExists = false;
    private boolean aiColourIsBlack = true;
    private boolean aiIsTurn = false;
    private AI ai;

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
    private ImageView btnBishop;

    @FXML
    private ImageView btnKnight;

    @FXML
    private ImageView btnRook;

    @FXML
    private ImageView btnQueen;


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
    void mouseClicked(MouseEvent event) throws IOException {
        Node source = (Node)event.getSource() ;

        int colIndex;
        int rowIndex;
        if(GridPane.getColumnIndex(source) == null){
            colIndex = 0;
        }
        else{
            if(board.isBlackIsTurn()){
                colIndex = 7 - GridPane.getColumnIndex(source);
            } else {
                colIndex = GridPane.getColumnIndex(source);
            }
        }
        if(GridPane.getRowIndex(source) == null){
            rowIndex = 8;
        }
        else{
            if(board.isBlackIsTurn()) {
                rowIndex = GridPane.getRowIndex(source) + 1;
            } else {
                rowIndex = 8 - GridPane.getRowIndex(source);
            }
        }
        //showPossibleMoves(colIndex, rowIndex);

        List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        String input = columns.get(colIndex) + rowIndex;
        halfMoves.add(input);
        position.add(event);
        System.out.println(input);
        counter++;
        move();

    }

    private void boardRotation() {
        //boardRota
        //white turn
        ImageView iv = null;
        chessBoard.getChildren().removeIf(node -> node instanceof ImageView);

        if(board.isBlackIsTurn()) {
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    iv = getImage(i, j);
                    if(iv != null){
                        iv.setFitWidth(90);
                        iv.setFitHeight(90);
                        //chessBoard.getChildren().remove(8- j, 8-i);
                        chessBoard.add(iv, 7 - j, 7 - i);
                    }

                }
            }
        }

            //black turn
        else if(!board.isBlackIsTurn()){
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    iv = getImage(i, j);
                    if(iv != null) {
                        iv.setFitWidth(90);
                        iv.setFitHeight(90);
                        chessBoard.add(iv, j, i);
                    }

                }
            }
        }
    }
    
    private ImageView getImage(int i, int j){
        ImageView iv = null;
        if(!board.getCheckerBoard()[i][j].isEmpty()) {
            switch (board.getCheckerBoard()[i][j].getMinion().getMinion_type()) {
                case 'K':
                    if(board.getCheckerBoard()[i][j].getMinion().isBlack()){
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KingBlack.png")).toExternalForm()));
                    }else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KingWhite.png")).toExternalForm()));
                    }
                    break;
                case 'Q':
                    if(board.getCheckerBoard()[i][j].getMinion().isBlack()){
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/QueenBlack.png")).toExternalForm()));
                    }else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/QueenWhite.png")).toExternalForm()));
                    }
                    break;
                case 'N':
                    if(board.getCheckerBoard()[i][j].getMinion().isBlack()){
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KnightBlack.png")).toExternalForm()));
                    }else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KnightWhite.png")).toExternalForm()));
                    }
                    break;
                case 'B':
                    if(board.getCheckerBoard()[i][j].getMinion().isBlack()){
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/BishopBlack.png")).toExternalForm()));
                    }else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/BishopWhite.png")).toExternalForm()));
                    }
                    break;
                case 'R':
                    if(board.getCheckerBoard()[i][j].getMinion().isBlack()){
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/RookBlack.png")).toExternalForm()));
                    }else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/RookWhite.png")).toExternalForm()));
                    }
                    break;
                case 'P':
                    if(board.getCheckerBoard()[i][j].getMinion().isBlack()){
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/PawnBlack.png")).toExternalForm()));
                    }else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/PawnWhite.png")).toExternalForm()));
                    }
                    break;
            }
            iv.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                try {
                    mouseClicked(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return iv;
    }

    public void move() throws IOException {
        if(counter == 2){
            String fistField = halfMoves.get(0);
            String secondField = halfMoves.get(1);
            String input = fistField + "-" + secondField + promoteTo;
            Move move = new Move(input);
            System.out.println(input);
            CellIndex endIndex = Board.cellIndexFor(move.getEnd());
            CellIndex startIndex = Board.cellIndexFor(move.getStart());
            Cell startCell = board.getCheckerBoard()[endIndex.getRow()][endIndex.getColumn()];
            if(!startCell.isEmpty() && String.valueOf(startCell.getMinion().getMinion_type()).equals("P") && endIndex.getRow() == 0 || endIndex.getRow() == 7){
                popupPromote();
                createsPromotetMinion();
                promotion = true;
            }

            if (manuals.moveOfRightColour(move, board)) {
                board.applyMove(move);
                //boardRotation();

                //TODO switch for aiIsTurn
                aiIsTurn = true;
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
                if(promotion){
                    //TODO minion löschen verschiedene Farben
                    chessBoard.getChildren().remove(endCol,endRow);
                    chessBoard.add(createsPromotetMinion(), endCol, endRow);


                }


                sourceEnd.toBack();
                sourceStart.toFront();
                ActionEvent event = new ActionEvent();
                if(board.isCheck()){
                    popupCheck(event);
                }

                beatenMinions(sourceEnd);

            }
            counter = 0;
            halfMoves.clear();
            position.clear();
        }

    }

    public void aiMove (){
        if (aiIsTurn){
            //generate moves for next move
            Move move = ai.getNextMove(board);
            //apply ai move
            board.applyMove(move);
            ai.increaseTurnNumber();
            // String output = board.showBoard(); // so war es in der cli
            // System.out.println(output);

            //change aiIsTurn = false
            aiIsTurn= false;
        }
    }

    private ImageView createsPromotetMinion() {
        ImageView promotedMinion = new ImageView();
        String name = "/BishopBlack.png";
        Image img = new Image(getClass().getResource("ChessFigures" + name).toExternalForm());
        promotedMinion.setImage(img);
        promotedMinion.setFitWidth(90);
        promotedMinion.setFitHeight(90);
        return promotedMinion;
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
        List<Move> possibleMoves = (staleMate.possibleMovesForOneFigureMoveList(startIndex, board.getCheckerBoard()));
        String start = possibleMoves.get(1).getStart();
        //Node endNode = getNodeByCoordinate(, endIndex.getColumn());
        //endNode.toFront();
    }

    Node getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : chessBoard.getChildren()) {
            if(GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }

    @FXML
    void popupCheck(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("check!");
        alert.setContentText("You are in check");
        alert.show();

    }
    @FXML
    void popupPromote() throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("promoteBlack.fxml"));
        Parent root = (Parent) loader.load();
        Scene secondScene = new Scene(root);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(gui.stage);
        newWindow.show();

    }
    @FXML
    void promoteMinionBishop(MouseEvent event) {
        promoteTo = "B";

    }
    @FXML
    void promoteMinionKnight(MouseEvent event) {
        promoteTo = "K";

    }

    @FXML
    void promoteMinionQueen(MouseEvent event) {
        promoteTo = "Q";

    }

    @FXML
    void promoteMinionRook(MouseEvent event) {
        promoteTo = "R";

    }






}
