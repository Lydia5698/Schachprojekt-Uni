package chess.gui;

import chess.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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
    private boolean checkIsVisible = false;
    private int beatenCounter = 0;

    @FXML
    private void b_neuesSpiel() {

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
    private Button btnChessKI;

    @FXML
    private Button btnStartScreen;

    @FXML
    private Button btnChessBoard;

    @FXML
    private GridPane chessBoard;

    @FXML
    private GridPane beatenMinion;

    @FXML
    private Text moveList;

    @FXML
    private Button btnBishop;

    @FXML
    private Button btnKnight;

    @FXML
    private Button btnRook;

    @FXML
    private Button btnQueen;

    @FXML
    private CheckBox lightPossibleMoves;

    @FXML
    private CheckBox checkVisible;

    @FXML
    private CheckBox rotateBoard;

    @FXML
    private Button btnBlack;

    @FXML
    private Button btnWhite;





    public void showStartScreen() {
        Stage stage = (Stage) btnStartScreen.getScene().getWindow();
        gui.show_FXML("startScreen.fxml", stage);
    }

    public void showAnleitung() {
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
        /*if(checkVisible.isSelected()){
            checkIsVisible = false;
        }*/
    }
    public void showSpielauswahl(){
        Stage stage = (Stage) btnSpielstart.getScene().getWindow();
        gui.show_FXML("spielauswahl.fxml", stage);
    }
    @FXML
    public void showKIGui() throws IOException {
        Stage stage = (Stage) btnChessKI.getScene().getWindow();
        popupColour();
        gui.show_FXML("gui.fxml", stage);
    }

    public void exit(){
        System.exit(0);

    }

    @FXML
    private ImageView pawnBlack;

    @FXML
    void mouseClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();

        int colIndex;
        int rowIndex;
        colIndex = getColIndex(source);
        rowIndex = getRowIndex(source);
        //showPossibleMoves(colIndex, rowIndex);

        List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        String input = columns.get(colIndex) + (8 - rowIndex);
        halfMoves.add(input);
        position.add(event);
        System.out.println(input);
        counter++;
        move();

    }

    private int getRowIndex(Node source) {
        int rowIndex;
        if (GridPane.getRowIndex(source) == null) {
            rowIndex = 0;
        } else {
            rowIndex = GridPane.getRowIndex(source);
        }
        return rowIndex;
    }

    private int getColIndex(Node source) {
        int colIndex;
        if(GridPane.getColumnIndex(source) == null){
            colIndex = 0;
        }
        else{
            colIndex = GridPane.getColumnIndex(source);
        }
        return colIndex;
    }

    private void boardRotation() {
        //boardRota
        //white turn
        ImageView iv = null;
        chessBoard.getChildren().removeIf(node -> node instanceof ImageView);

        if (board.isBlackIsTurn()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    iv = getImage(i, j);
                    if (iv != null) {
                        iv.setFitWidth(90);
                        iv.setFitHeight(90);
                        chessBoard.add(iv, j, 7 - i);
                    }

                }
            }
        }

        //black turn
        else if (!board.isBlackIsTurn()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    iv = getImage(i, j);
                    if (iv != null) {
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
        if (!board.getCheckerBoard()[i][j].isEmpty()) {
            switch (board.getCheckerBoard()[i][j].getMinion().getMinion_type()) {
                case 'K':
                    if (board.getCheckerBoard()[i][j].getMinion().isBlack()) {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KingBlack.png")).toExternalForm()));
                    } else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KingWhite.png")).toExternalForm()));
                    }
                    break;
                case 'Q':
                    if (board.getCheckerBoard()[i][j].getMinion().isBlack()) {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/QueenBlack.png")).toExternalForm()));
                    } else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/QueenWhite.png")).toExternalForm()));
                    }
                    break;
                case 'N':
                    if (board.getCheckerBoard()[i][j].getMinion().isBlack()) {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KnightBlack.png")).toExternalForm()));
                    } else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KnightWhite.png")).toExternalForm()));
                    }
                    break;
                case 'B':
                    if (board.getCheckerBoard()[i][j].getMinion().isBlack()) {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/BishopBlack.png")).toExternalForm()));
                    } else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/BishopWhite.png")).toExternalForm()));
                    }
                    break;
                case 'R':
                    if (board.getCheckerBoard()[i][j].getMinion().isBlack()) {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/RookBlack.png")).toExternalForm()));
                    } else {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/RookWhite.png")).toExternalForm()));
                    }
                    break;
                case 'P':
                    if (board.getCheckerBoard()[i][j].getMinion().isBlack()) {
                        iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/PawnBlack.png")).toExternalForm()));
                    } else {
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
        if (counter == 2) {
            String fistField = halfMoves.get(0);
            String secondField = halfMoves.get(1);
            String input = fistField + "-" + secondField + promoteTo;
            Move move = new Move(input);

            CellIndex endIndex = Board.cellIndexFor(move.getEnd());
            CellIndex startIndex = Board.cellIndexFor(move.getStart());
            Cell startCell = board.getCheckerBoard()[startIndex.getRow()][startIndex.getColumn()];
            Cell endCell = board.getCheckerBoard()[endIndex.getRow()][endIndex.getColumn()];
            int diffrow = Math.abs(startIndex.getRow() - endIndex.getRow());
            if(!startCell.isEmpty() && diffrow == 1 && String.valueOf(startCell.getMinion().getMinion_type()).equals("P") && (endIndex.getRow() == 0 || endIndex.getRow() == 7)){
                popupPromote();
                promoteTo = "B";
                promotion = true;
            }

            String inputNew = fistField + "-" + secondField + promoteTo;
            Move moveNew = new Move(inputNew);
            System.out.println(inputNew);

            if (manuals.moveOfRightColour(moveNew, board)) {
                board.applyMove(moveNew);
                //boardRotation();
                String beatenString = "Moves";
                for (Move beatenMinion : board.getMoveList()) {
                    String moveString = beatenMinion.getStart() + "-" + beatenMinion.getEnd();
                    beatenString = String.join(",", beatenString, moveString);
                }
                moveList.setText(beatenString);
                //boardRotation();
            }

            else {
                System.out.println("!Move not allowed");
                board.setAllowedMove(false);
            }

            if (board.isAllowedMove()) {
                Event start = position.get(0);
                Event end = position.get(1);
                Node sourceEnd = (Node)end.getSource();
                Node sourceStart = (Node)start.getSource();
                int endCol = getColIndex(sourceEnd);
                int endRow = getRowIndex(sourceEnd);
                if(promotion){
                    //TODO PromoteTo richtig setzten

                    if(sourceStart.getId().equals("image")){
                        chessBoard.getChildren().remove(sourceStart);
                    }
                    chessBoard.add(createsPromotetMinion(endCell.getMinion().isBlack()), endCol, endRow);
                    promotion = false;


                }
                if(sourceStart.getId().equals("image")){
                    GridPane.setColumnIndex(sourceStart, endCol);
                    GridPane.setRowIndex(sourceStart, endRow);
                }

                sourceStart.toFront();
                ActionEvent event = new ActionEvent();
                if (board.isCheck()) {
                    popupCheck(event);
                    board.setCheck(false);
                }

                beatenMinions(sourceEnd);



            }
            counter = 0;
            halfMoves.clear();
            position.clear();
        }

    }

    private ImageView createsPromotetMinion(boolean black) {
        ImageView promotedMinion = new ImageView();
        String name = "/QueenWhite.png";
        switch (promoteTo) {
            case "B": {
                if(black){
                    name = "/BishopBlack.png";
                }
                else {
                    name = "/BishopWhite.png";
                }
                break;
            }
            case "K": {
                if (black){
                    name = "/KnightBlack.png";
                }
                else {
                    name = "/KnightWhite.png";
                }
                break;
            }
            case "R": {
                if (black){
                    name = "/RookBlack.png";
                }
                else {
                    name = "/RookWhite.png";
                }
                break;
            }
            case "Q":{
                if (black){
                    name = "/QueenBlack.png";
                }
                else {
                    name = "/QueenWhite.png";
                }
                break;
            }

        }
        Image img = new Image(getClass().getResource("ChessFigures" + name).toExternalForm());
        promotedMinion.setImage(img);
        promotedMinion.setFitWidth(90);
        promotedMinion.setFitHeight(90);
        promotedMinion.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                mouseClicked(mouseEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return promotedMinion;
    }


    private void beatenMinions(Node sourceEnd) {
        if(board.getBeaten().size() == 1){
            String minion = board.getBeaten().get(0);
            char minionType = minion.charAt(0);
            if(sourceEnd.getId().equals("image")){
                chessBoard.getChildren().remove(sourceEnd);
                if (Character.isUpperCase(minionType)) {
                //sourceEnd.set
                beatenMinion.add(sourceEnd,0,counterBeatenMinionsWhite);
                counterBeatenMinionsWhite++;
                } else {
                    beatenMinion.add(sourceEnd, 1, counterBeatenMinionsBlack);
                    counterBeatenMinionsBlack++;
                }
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
            if (GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null;
    }

    @FXML
    void popupCheck(ActionEvent event) {

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
        newWindow.showAndWait();

    }
    @FXML
    void popupColour() throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource("colourSelect.fxml"));
        Parent root = (Parent) loader.load();
        Scene secondScene = new Scene(root);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(gui.stage);
        newWindow.showAndWait();

    }

    @FXML
    void promoteMinionBishop(MouseEvent event) {

        promoteTo = btnBishop.getText();
        Stage stage = (Stage) btnBishop.getScene().getWindow();
        stage.close();
    }

    @FXML
    void promoteMinionKnight(MouseEvent event) {
        promoteTo = btnKnight.getText();
        Stage stage = (Stage) btnKnight.getScene().getWindow();
        stage.close();


    }

    @FXML
    void promoteMinionQueen(MouseEvent event) {
        setPromoteTo("Q");
        Stage stage = (Stage) btnQueen.getScene().getWindow();
        stage.close();


    }

    @FXML
    void promoteMinionRook(MouseEvent event) {
        Stage stage = (Stage) btnRook.getScene().getWindow();
        stage.close();


    }
    public void setPromoteTo(String promoteTo) {
        this.promoteTo = promoteTo;
    }

    @FXML
    void colourBlack(MouseEvent event) {
        Stage stage = (Stage) btnBlack.getScene().getWindow();
        stage.close();
    }

    @FXML
    void colourWhite(MouseEvent event) {
        Stage stage = (Stage) btnWhite.getScene().getWindow();
        stage.close();
    }





}
