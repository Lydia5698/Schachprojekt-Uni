package chess.gui;

import chess.model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.*;

public class ActiveGameController extends MainController {
    protected Board board;
    private final List<String> halfMoves = new ArrayList<>();
    private final List<Event> position = new ArrayList<>();
    private int counter = 0;
    private int counterBeatenMinionsWhite = 0;
    private int counterBeatenMinionsBlack = 0;
    private String firstMinionClickedWhite = "";
    private String firstMinionClickedBlack = "";

    @FXML
    private Group letter;

    @FXML
    private Button btnOptions;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private GridPane chessBoard;

    @FXML
    private GridPane beatenMinion;

    @FXML
    private Text moveList;

    @FXML
    void showOptions(){
        Stage stage = (Stage) btnOptions.getScene().getWindow();
        show_FXML("options.fxml", stage, getGui());
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        setBoard(gui.settings.getBoard());
        if(board.getMoveList().isEmpty()){
            whiteAIMove();
        }
        // network white Move
        changeToLanguage();
        updateBoard();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @FXML
    void changeLanguage(MouseEvent event) {
        getGui().getSettings().changeLanguage();
        changeToLanguage();
    }

    private void changeToLanguage(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "03")))).toExternalForm())));
        btnOptions.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"31")));
    }

    public void whiteAIMove(){
        if(getGui().getSettings().isAi_active() && !getGui().getSettings().isAi_colour()) {
            board.applyMove(getGui().getSettings().getAi().getNextMove(board));
            getGui().getSettings().getAi().increaseTurnNumber();
            System.out.println(board.showBoard());
            //update
            updateBoard();
        }
    }

    @FXML
    void mouseClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();

        int colIndex;
        int rowIndex;
        colIndex = getColIndex(source);
        rowIndex = getRowIndex(source);
        if(getGui().getSettings().isHighlightPossibleMoves())
        showPossibleMoves(colIndex, rowIndex);

        List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        String input;
        // checks if rotation is on and changes the coordinates for black
        if(board.isBlackIsTurn() && getGui().getSettings().isRotateBoard()){
            input = columns.get(7 - colIndex) + (rowIndex+1);
        }
        else {
            input = columns.get(colIndex) + (8 - rowIndex);
        }
        // sets first Minion Clicked for Black
        if (getGui().getSettings().isDoubleClick() && board.isBlackIsTurn() && firstMinionClickedBlack.isEmpty()){
            firstMinionClickedBlack = input;
        }
        // sets first Minion clicked for White
        if(getGui().getSettings().isDoubleClick() && !board.isBlackIsTurn() && firstMinionClickedWhite.isEmpty()){
            firstMinionClickedWhite = input;
        }
        halfMoves.add(input);
        position.add(event);
        counter++;
        move();

    }
    public void move() throws IOException {

        if (counter == 2 && !board.isGameEnd()) {
            String fistField = halfMoves.get(0);
            String secondField = halfMoves.get(1);
            String input = fistField + "-" + secondField;
            Move move = new Move(input);

            CellIndex endIndex = Board.cellIndexFor(move.getEnd());
            CellIndex startIndex = Board.cellIndexFor(move.getStart());
            Cell startCell = board.getCheckerBoard()[startIndex.getRow()][startIndex.getColumn()];
            int diffrow = Math.abs(startIndex.getRow() - endIndex.getRow());
            if (!startCell.isEmpty() && diffrow == 1 && String.valueOf(startCell.getMinion().getMinion_type()).equals("P") && (endIndex.getRow() == 0 || endIndex.getRow() == 7)) {
                popupPromote();
                input = fistField + "-" + secondField + getPromoteTo();

            }

            Move moveNew = new Move(input);

            checkAndDoMove(fistField, endIndex, startIndex, moveNew);
            counter = 0;
            halfMoves.clear();
            position.clear();
            if(getGui().getSettings().isRotateBoard() && !getGui().getSettings().isAi_active()){
                boardRotation();
            }
            if(board.isGameEnd()){
                popupCheckMate();
            }
        }


    }

    private void checkAndDoMove(String fistField, CellIndex endIndex, CellIndex startIndex, Move moveNew) {
        if (board.manuals.moveOfRightColour(moveNew, board) && board.manuals.checkIfValidMove(startIndex, endIndex,board.getCheckerBoard())) {
            if (!getGui().getSettings().isDoubleClick() || (firstMinionClickedWhite.equals(fistField)) || firstMinionClickedBlack.equals(fistField)){
                applyCurrentMove(moveNew);
            }
            else{
                popupDoubleClick();
            }

        }
        else {
            popupMoveNotAllowed();
            board.setAllowedMove(false);
        }
    }

    private void applyCurrentMove(Move moveNew) {
        firstMinionClickedBlack = "";
        firstMinionClickedWhite = "";
        board.applyMove(moveNew);
        history();
        Event end = position.get(1);
        Node sourceEnd = (Node) end.getSource();
        beatenMinion(sourceEnd);
        updateBoard();
        if (board.isCheck() & getGui().getSettings().isCheckVisible()) {
            popupCheck();
            board.setCheck(false);
        }
        if (board.isGameEnd()) {
            popupCheckMate();
        }
        // network move
        if (getGui().getSettings().isAi_active()) {
            Move aiMove = getGui().getSettings().getAi().getNextMove(board);
            board.applyMove(aiMove);
            // geschlagene figur vom AI Move
            CellIndex endIndex = Board.cellIndexFor(aiMove.getEnd());
            sourceEnd = getNodeByCoordinate(endIndex.getRow(), endIndex.getColumn());
            getGui().getSettings().getAi().increaseTurnNumber();
            //update
            beatenMinion(sourceEnd);
            updateBoard();

        }
    }

    private void history() {
        String beatenString = "Moves";
        for (Move beatenMinion : board.getMoveList()) {
            String moveString = beatenMinion.getStart() + "-" + beatenMinion.getEnd();
            beatenString = String.join(",", beatenString, moveString);
        }
        moveList.setText(beatenString);
    }


    private void boardRotation() {
        ImageView iv;
        chessBoard.getChildren().removeIf(node -> node instanceof ImageView);

        if (board.isBlackIsTurn()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    iv = getImage(i, j);
                    if (iv != null) {
                        iv.setFitWidth(90);
                        iv.setFitHeight(90);
                        chessBoard.add(iv, 7- j, 7 - i);
                    }
                }
            }
            letter.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }

        //black turn
        else if (!board.isBlackIsTurn()) {
            updateBoard();
            letter.setNodeOrientation(NodeOrientation.INHERIT);
        }
    }

    private ImageView getImage(int i, int j) {
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
            assert iv != null;
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

    private void updateBoard(){
        ImageView iv;
        chessBoard.getChildren().removeIf(node -> node instanceof ImageView);
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
    private void beatenMinion(Node sourceEnd) {
        if(board.getBeaten().size() == 1){
            String minion = board.getBeaten().get(0);
            char minionType = minion.charAt(0);
            if(sourceEnd instanceof ImageView) {
                chessBoard.getChildren().remove(sourceEnd);
                if (Character.isUpperCase(minionType)) {
                    //sourceEnd.set
                    beatenMinion.add(sourceEnd, 0, counterBeatenMinionsWhite);
                    counterBeatenMinionsWhite++;
                } else {
                    beatenMinion.add(sourceEnd, 1, counterBeatenMinionsBlack);
                    counterBeatenMinionsBlack++;
                }
            }
            board.getBeaten().clear();
        }
    }


    public void showPossibleMoves(int startCol, int startRow) {
        CellIndex startIndex = new CellIndex(startRow, startCol);
        chessBoard.getChildren().removeIf(node -> node instanceof Rectangle && ((Rectangle) node).getFill().equals(Paint.valueOf("#ff6347")));
        if(getNodeByCoordinate(startRow, startCol) instanceof ImageView && !board.getCheckerBoard()[startRow][startCol].isEmpty()) {
            List<Move> possibleMoves = (board.staleMate.possibleMovesForOneFigureMoveList(startIndex, board.getCheckerBoard()));

            for (Move move : possibleMoves) {
                String s = "abcdefgh";
                Rectangle possMove = new Rectangle();
                possMove.setHeight(10);
                possMove.setWidth(10);
                possMove.setFill(Paint.valueOf("#ff6347"));
                possMove.setArcHeight(10.0d);
                possMove.setArcWidth(10.0d);
                possMove.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    try {
                        mouseClicked(mouseEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println(s.indexOf(move.getEnd().substring(0, 1)));
                System.out.println(8-Integer.parseInt(move.getEnd().substring(1,2)));
                chessBoard.add(possMove, s.indexOf(move.getEnd().substring(0, 1)), 8-Integer.parseInt(move.getEnd().substring(1,2)));
                chessBoard.setAlignment(Pos.CENTER);
                possMove.toFront();
            }
        }
    }

    Node getNodeByCoordinate(int row, int column) {
        for (Node node : chessBoard.getChildren()) {
            int nodeRow;
            int nodeCol;
            try {
                nodeRow = GridPane.getRowIndex(node);
            } catch (Exception e){
                nodeRow = 0;
            }
            try {
                nodeCol = GridPane.getColumnIndex(node);
            } catch (Exception e){
                nodeCol = 0;
            }
            if (nodeRow == row  && nodeCol == column  && node instanceof ImageView) {
                return node;
            }
        }
        return null;
    }

    @FXML
    void popupCheck() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"82")));
        if(board.isBlackIsTurn()){
            alert.setContentText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"80")));
        }
        else {
            alert.setContentText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"81")));
        }

        alert.show();
    }

    @FXML
    void popupCheckMate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"92")));
        if (board.isBlackIsTurn()) {
            alert.setContentText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"90")));
        } else {
            alert.setContentText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber()+"91")));

        }
        alert.show();
    }

    @FXML
    void popupMoveNotAllowed() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "70")));
        alert.setContentText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "71")));
        alert.show();
    }

    @FXML
    void popupDoubleClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "83")));
        if(board.isBlackIsTurn()){
            alert.setContentText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "84")) + firstMinionClickedBlack);
        }
        else {
            alert.setContentText(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "84")) + firstMinionClickedWhite);
        }
        alert.show();
    }

    @FXML
    void popupPromote() throws IOException {
        popupPromotion("promote.fxml");

    }

    public void popupPromotion(String filename) throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource(filename));
        Parent root = loader.load();
        Scene secondScene = new Scene(root);
        ((MainController)loader.getController()).setGui(getGui());
        ((PromoteController)loader.getController()).setController(this);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(getGui().stage);
        newWindow.showAndWait();
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

}
