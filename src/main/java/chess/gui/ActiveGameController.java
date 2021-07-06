package chess.gui;

import chess.Settings;
import chess.model.*;
import javafx.event.ActionEvent;
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
import javafx.scene.control.CheckBox;
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
    protected static Manuals manuals = new Manuals();
    protected static StaleMate staleMate = new StaleMate();
    protected static SpecialManuals spManuals = new SpecialManuals();
    private final List<String> halfMoves = new ArrayList<>();
    private final List<Event> position = new ArrayList<>();
    private int counter = 0;
    private int counterBeatenMinionsWhite = 0;
    private int counterBeatenMinionsBlack = 0;
    private final int beatenCounter = 0;
    private String firstMinionClicked;

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
    void showOptions(MouseEvent event){
        Stage stage = (Stage) btnOptions.getScene().getWindow();
        show_FXML("options.fxml", stage, getGui());
    }

    @Override
    public void setGui(Gui gui){
        this.gui = gui;
        whiteAIMove();
        if(gui.getSettings().isLanguageGerman()){
            changeToGerman();
        }
        setBoard(gui.settings.getBoard());
        updateBoard();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @FXML
    void changeLanguage(MouseEvent event) {
        if(getGui().getSettings().isLanguageEnglish()){
            getGui().getSettings().setLanguageEnglish(false);
            getGui().getSettings().setLanguageGerman(true);
            changeToGerman();
        }
        else {
            getGui().getSettings().setLanguageEnglish(true);
            getGui().getSettings().setLanguageGerman(false);
            changeToEnglish();
        }
    }

    private void changeToGerman(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/UnitedKingdomFlag.png")).toExternalForm()));
        btnOptions.setText(gui.getSettings().getLanguage().getDic().get(231));
    }

    private void changeToEnglish(){
        btnLanguage.setImage(new Image(Objects.requireNonNull(getClass().getResource("Flags/GermanFlag.png")).toExternalForm()));
        btnOptions.setText(gui.getSettings().getLanguage().getDic().get(131));

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

        halfMoves.add(input);
        position.add(event);
        System.out.println(input);
        counter++;
        move();

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
        ActionEvent event = new ActionEvent();
        if (counter == 2 && !board.isGameEnd()) {
            String fistField = halfMoves.get(0);
            String secondField = halfMoves.get(1);
            String input = fistField + "-" + secondField;
            Move move = new Move(input);

            CellIndex endIndex = Board.cellIndexFor(move.getEnd());
            CellIndex startIndex = Board.cellIndexFor(move.getStart());
            Cell startCell = board.getCheckerBoard()[startIndex.getRow()][startIndex.getColumn()];
            if(!startCell.isEmpty()){
                firstMinionClicked = fistField;
            }
            int diffrow = Math.abs(startIndex.getRow() - endIndex.getRow());
            if (!startCell.isEmpty() && diffrow == 1 && String.valueOf(startCell.getMinion().getMinion_type()).equals("P") && (endIndex.getRow() == 0 || endIndex.getRow() == 7)) {
                popupPromote();
                input = fistField + "-" + secondField + getPromoteTo();

            }


            Move moveNew = new Move(input);
            System.out.println(input);

            if (manuals.moveOfRightColour(moveNew, board)) {
                board.applyMove(moveNew);
                System.out.println(board.showBoard());


                String beatenString = "Moves";
                for (Move beatenMinion : board.getMoveList()) {
                    String moveString = beatenMinion.getStart() + "-" + beatenMinion.getEnd();
                    beatenString = String.join(",", beatenString, moveString);
                }
                moveList.setText(beatenString);
                Event end = position.get(1);
                Node sourceEnd = (Node) end.getSource();
                beatenMinions(sourceEnd);
                updateBoard();
                if (board.isCheck() & getGui().getSettings().isCheckVisible()) {
                    popupCheck(event);
                    board.setCheck(false);
                }
                if(board.isGameEnd()){
                    popupCheckMate(event);
                }

                if (getGui().getSettings().isAi_active()){
                    board.applyMove(getGui().getSettings().getAi().getNextMove(board));
                    getGui().getSettings().getAi().increaseTurnNumber();
                    System.out.println(board.showBoard());
                    //update
                    updateBoard();
                }

            }

            else {
                popupMoveNotAllowed(event); //TODO falscher move popup
                board.setAllowedMove(false);
                // ersten Half move merken nur bei move not allowed abfangen bei leerem feld angeklickt
            }

            counter = 0;
            halfMoves.clear();
            position.clear();
            if(getGui().getSettings().isRotateBoard() && !getGui().getSettings().isAi_active()){
                boardRotation();
            }
            if(board.isGameEnd()){
                popupCheckMate(event);
                //board = new Board();
            }
        }
        

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
    private void beatenMinions(Node sourceEnd) {
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
        chessBoard.getChildren().removeIf(node -> node instanceof Rectangle && ((Rectangle) node).getFill().equals(Paint.valueOf("#ff0000")));
        if(getNodeByCoordinate(startRow, startCol) instanceof ImageView && !board.getCheckerBoard()[startRow][startCol].isEmpty()) {
            List<Move> possibleMoves = (staleMate.possibleMovesForOneFigureMoveList(startIndex, board.getCheckerBoard()));

            for (Move move : possibleMoves) {
                String s = "abcdefgh";
                Rectangle possMove = new Rectangle();
                possMove.setHeight(10);
                possMove.setWidth(10);
                possMove.setFill(Paint.valueOf("#ff0000"));
                possMove.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    try {
                        mouseClicked(mouseEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                chessBoard.add(possMove, s.indexOf(move.getEnd().substring(0, 1)), 8-Integer.parseInt(move.getEnd().substring(1,2)));
                chessBoard.setAlignment(Pos.CENTER);
                possMove.toFront();
                // TODO es werden nicht alle moves richtig angezeigt
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
            if (nodeRow == column + 1 && nodeCol == row + 1 && node instanceof ImageView) {
                return node;
            }
        }
        return null;
    }

    @FXML
    void popupCheck(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(getGui().getSettings().isLanguageGerman()){
            alert.setTitle("Schach!");
            if(board.isBlackIsTurn()){
                alert.setContentText(getGui().getSettings().getLanguage().getDic().get(280));
            }
            else {
                alert.setContentText(getGui().getSettings().getLanguage().getDic().get(281));
            }

        }
        else {
            alert.setTitle("check!");
            if (board.isBlackIsTurn()) {
                alert.setContentText(getGui().getSettings().getLanguage().getDic().get(180));
            } else {
                alert.setContentText(getGui().getSettings().getLanguage().getDic().get(181));
            }
            alert.show();
        }

    }
    @FXML
    void popupCheckMate(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(getGui().getSettings().isLanguageGerman()) {
            alert.setTitle("Schachmatt!");
            if (board.isBlackIsTurn()) {
                alert.setContentText(getGui().getSettings().getLanguage().getDic().get(290));
            } else {
                alert.setContentText(getGui().getSettings().getLanguage().getDic().get(291));
            }
        }
        else {
            alert.setTitle("checkMate!");
            if (board.isBlackIsTurn()) {
                alert.setContentText(getGui().getSettings().getLanguage().getDic().get(190));
            } else {
                alert.setContentText(getGui().getSettings().getLanguage().getDic().get(191));
            }
        }
        alert.show();
    }

    @FXML
    void popupMoveNotAllowed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(getGui().getSettings().isLanguageGerman()){
            alert.setTitle(getGui().getSettings().getLanguage().getDic().get(270));
            alert.setContentText(getGui().getSettings().getLanguage().getDic().get(270));
        }
        else {
            alert.setTitle(getGui().getSettings().getLanguage().getDic().get(170));
            alert.setContentText(getGui().getSettings().getLanguage().getDic().get(171));
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
