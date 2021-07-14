package chess.gui;

import chess.model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.*;

/**
 * The Controller for the active game. Where you can see the chessboard
 */
public class ActiveGameController extends MainController {
    protected Board board;
    protected ActiveGameHelper activeGameHelper = new ActiveGameHelper(this);
    protected Popups popups;
    protected final List<String> halfMoves = new ArrayList<>();
    protected final List<Event> position = new ArrayList<>();
    protected int counter = 0;
    protected int counterBeatenMinionsWhite = 0;
    protected int counterBeatenMinionsBlack = 0;

    @FXML
    private Group letter;

    @FXML
    private Button btnOptions;

    @FXML
    private ImageView btnSave;

    @FXML
    private ImageView btnLanguage;

    @FXML
    private GridPane chessBoard;

    @FXML
    private GridPane beatenMinion;

    @FXML
    private Text moveList;

    /**
     * Shows the fxml file options
     */
    @FXML
    void showOptions() {
        Stage stage = (Stage) btnOptions.getScene().getWindow();
        show_FXML("options.fxml", stage, getGui());
        //popups.savePopup();
    }

    /**
     * Sets the Gui, makes the white AI move, changes the Language and updates the Board
     *
     * @param gui The current active Gui
     */
    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        setBoard(gui.settings.getBoard());
        popups = new Popups();
        if (board.getMoveList().isEmpty()) {
            activeGameHelper.whiteAIMove();
        }
        if(gui.getSettings().isNetwork_active()){
            networkMove();
        }
        // network white Move
        changeToLanguage();
        updateBoard();
        history();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * The language is changed in the settings when the Image btnLanguage is pushed
     */
    @FXML
    void changeLanguage() {
        getGui().getSettings().changeLanguage();
        changeToLanguage();
    }

    /**
     * Changes all buttons and text fields to the selected language
     */
    private void changeToLanguage() {
        btnLanguage.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "03")))).toExternalForm())));
        btnOptions.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "31")));
    }

    /**
     * Opens the File Chooser were you can save the current Game
     */
    @FXML
    void saveGame() {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        LoadSaveController loadSaveController = new LoadSaveController(getGui());
        loadSaveController.saveFile(stage);
        //show_FXML("saveScreen.fxml", stage, getGui());
    }


    /**
     * Saves the mouseEvent and the pos of the field clicked.
     * if rotation and double click is activated the input is beeing changed
     * activates the methode move
     *
     * @param event the event when the player clicks on a field
     * @throws IOException for the popups if they dont get closed
     */
    @FXML
    void mouseClicked(MouseEvent event) throws IOException {
        updateBoard();
        Node source = (Node) event.getSource();

        if(getGui().getSettings().isNetwork_active()){ // networkmove ausgabe
            networkMove();
        }

        int colIndex;
        int rowIndex;
        colIndex = getColIndex(source);
        rowIndex = getRowIndex(source);
        if (getGui().getSettings().isHighlightPossibleMoves()) {
            activeGameHelper.showPossibleMoves(colIndex, rowIndex);

        }
        List<String> columns = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        // checks if rotation is on and changes the coordinates for black
        String input = activeGameHelper.guiOptions(colIndex, rowIndex, columns);
        halfMoves.add(input);
        position.add(event);
        counter++;
        move();

    }

    /**
     * if two fields are clicked move creates the move from the halfMoves list and checks for Promotion
     * then the methode checkAndDoMove is called to apply the move
     * if rotate board is activated the board gets rotated after every drawn move
     *
     * @throws IOException for the popups if they dont get closed
     */
    public void move() throws IOException {
        if (counter == 2 && !getGui().getSettings().isGameEnd()) {
            String fistField = halfMoves.get(0);
            String secondField = halfMoves.get(1);
            String input = fistField + "-" + secondField;
            Move move = new Move(input);

            CellIndex endIndex = Board.cellIndexFor(move.getEnd());
            CellIndex startIndex = Board.cellIndexFor(move.getStart());
            Cell startCell = board.getCheckerBoard()[startIndex.getRow()][startIndex.getColumn()];
            input = checkPromotion(input, startIndex, endIndex);

            Move moveNew = new Move(input);
            if(!startCell.isEmpty()){
                activeGameHelper.checkAndDoMove(fistField, endIndex, startIndex, moveNew);

            }
            if(getGui().getSettings().isNetwork_active()){ // netowkmove ausgabe
                networkMove();
            }
            counter = 0;
            halfMoves.clear();
            position.clear();
            if (getGui().getSettings().isRotateBoard() && !getGui().getSettings().isAi_active()) {
                boardRotation();
            }
            if (getGui().getSettings().isGameEnd()) {
                popups.popupCheckMate(gui);
                getGui().getSettings().setAi_active(false);
                getGui().getSettings().setNetwork_active(false);
            }
        }
    }

    /**
     * Calls the methode crateClient in the Settings class. And after the move the board gets updated
     */
    private void networkMove(){
        getGui().getSettings().createClient();
        updateBoard();
    }

    /**
     * checks if the Pawn is allowed to make a promotion
     *
     * @param input      the current move without the promotion
     * @param startIndex the startIndex of the current move. The position of the Pawn
     * @param endIndex   the endIndex of the current move
     * @return the new move with promoteTo. The letter for the Promotion
     * @throws IOException for the popups if they dont get closed
     */
    private String checkPromotion(String input, CellIndex startIndex, CellIndex endIndex) throws IOException {
        int diffrow = Math.abs(startIndex.getRow() - endIndex.getRow());
        Cell startCell = board.getCheckerBoard()[startIndex.getRow()][startIndex.getColumn()];
        String fistField = halfMoves.get(0);
        String secondField = halfMoves.get(1);
        String promoteMove = input;

        if (!startCell.isEmpty() && diffrow == 1 && String.valueOf(startCell.getMinion().getMinion_type()).equals("P") && (endIndex.getRow() == 0 || endIndex.getRow() == 7)) {
            popupPromotion("promote.fxml");
            promoteMove = fistField + "-" + secondField + getPromoteTo();
        }
        return promoteMove;
    }

    /**
     * The history of the moves. It is getting printed above the chessboard
     */
    void history() {
        String beatenString = "Moves";
        for (Move beatenMinion : board.getMoveList()) {
            String moveString = beatenMinion.getStart() + "-" + beatenMinion.getEnd();
            beatenString = String.join(",", beatenString, moveString);
        }
        moveList.setText(beatenString);
    }


    /**
     * rotates the Board. Deletes the old images and sets the new one in a rotated order
     */
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
                        chessBoard.add(iv, 7 - j, 7 - i);
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

    /**
     * Sets the Images for the Board. Gets the information of the Figures from the Board. Loads the Images from the Directory ChessFigures
     *
     * @param i the row of the chessboard
     * @param j column of the chessboard
     * @return the Image at the Index (row, column)
     */
    private ImageView getImage(int i, int j) {
        ImageView iv = null;
        if (!board.getCheckerBoard()[i][j].isEmpty()) {
            iv = getImageView(i, j, false, false);
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

    private ImageView getImageView(int i, int j, boolean beatenMinions, boolean colour) {
        char minion;
        String toChar;
        ImageView iv = null;
        if(!beatenMinions){
            minion = board.getCheckerBoard()[i][j].getMinion().getMinion_type();
            colour = board.getCheckerBoard()[i][j].getMinion().isBlack();
        }
        else {
            toChar = board.getBeaten().get(i);
            minion = toChar.charAt(0);
            minion = Character.toUpperCase(minion);
        }
        switch (minion) {
            case 'K':
                if (colour) {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KingBlack.png")).toExternalForm()));
                } else {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KingWhite.png")).toExternalForm()));
                }
                break;
            case 'Q':
                if (colour) {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/QueenBlack.png")).toExternalForm()));
                } else {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/QueenWhite.png")).toExternalForm()));
                }
                break;
            case 'N':
                if (colour) {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KnightBlack.png")).toExternalForm()));
                } else {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/KnightWhite.png")).toExternalForm()));
                }
                break;
            case 'B':
                if (colour) {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/BishopBlack.png")).toExternalForm()));
                } else {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/BishopWhite.png")).toExternalForm()));
                }
                break;
            case 'R':
                if (colour) {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/RookBlack.png")).toExternalForm()));
                } else {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/RookWhite.png")).toExternalForm()));
                }
                break;
            case 'P':
                if (colour) {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/PawnBlack.png")).toExternalForm()));
                } else {
                    iv = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("ChessFigures/PawnWhite.png")).toExternalForm()));
                }
                break;
        }
        return iv;
    }

    /**
     * Updates the Board after every drawn move. Rotation of the Board. Gets the Information from the board
     */
    void updateBoard() {
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

    /**
     * Sets the beaten Minions on the Grid next to the chessboard. It distinguished between black and white Minions beaten
     *
     */
    void beatenMinionOutput() {
        String minion;
        ImageView iv;
        char minionType;
        if (board.getBeaten().size() == 1) {
            minion = board.getBeaten().get(0);
            minionType = minion.charAt(0);
            if (Character.isUpperCase(minionType)) {
                //sourceEnd.set
                iv = getImageView(0,1,true, false);
                iv.setFitHeight(90);
                iv.setFitWidth(90);
                beatenMinion.add(iv, 0, counterBeatenMinionsWhite);
                counterBeatenMinionsWhite++;
            } else {
                iv = getImageView(0,1,true,true);
                iv.setFitHeight(90);
                iv.setFitWidth(90);
                beatenMinion.add(iv, 1, counterBeatenMinionsBlack);
                counterBeatenMinionsBlack++;
            }
        }
        board.getBeaten().clear();
    }




    /**
     * Gets the Node with Coordinates from the Gridpane chessboard. Only returns the Images at this position not the
     * background
     *
     * @param row    the row of the wanted node
     * @param column the column of the wanted node
     * @return the node at the coordinates
     */
    Node getNodeByCoordinate(int row, int column) {
        for (Node node : chessBoard.getChildren()) {
            int nodeRow;
            int nodeCol;
            try {
                nodeRow = GridPane.getRowIndex(node);
            } catch (Exception e) {
                nodeRow = 0;
            }
            try {
                nodeCol = GridPane.getColumnIndex(node);
            } catch (Exception e) {
                nodeCol = 0;
            }
            if (nodeRow == row && nodeCol == column && node instanceof ImageView) {
                return node;
            }
        }
        return null;
    }


    /**
     * Pops up the promotionPopup where you can decide in which Minion you Pawn should be promoted
     *
     * @param filename the filename of the promotion fxml
     * @throws IOException for the popups if they dont get closed
     */
    @FXML
    public void popupPromotion(String filename) throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Gui.class.getResource(filename));
        Parent root = loader.load();
        Scene secondScene = new Scene(root);
        ((MainController) loader.getController()).setGui(getGui());
        ((PromoteController) loader.getController()).setController(this);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(getGui().stage);
        newWindow.showAndWait();
    }

    /**
     * The row index of the Gridpane is null for 0.
     *
     * @param source the node which is clicked
     * @return the right row index without null
     */
    private int getRowIndex(Node source) {
        int rowIndex;
        if (GridPane.getRowIndex(source) == null) {
            rowIndex = 0;
        } else {
            rowIndex = GridPane.getRowIndex(source);
        }
        return rowIndex;
    }

    /**
     * The column index of the Gridpane is null for 0.
     *
     * @param source the node which is clicked
     * @return the right column index without null
     */
    private int getColIndex(Node source) {
        int colIndex;
        if (GridPane.getColumnIndex(source) == null) {
            colIndex = 0;
        } else {
            colIndex = GridPane.getColumnIndex(source);
        }
        return colIndex;
    }

    public GridPane getChessBoard() {
        return chessBoard;
    }


}
