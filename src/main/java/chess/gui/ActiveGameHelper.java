package chess.gui;

import chess.model.Board;
import chess.model.CellIndex;
import chess.model.Move;
import chess.model.SpecialManuals;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.List;

/**
 * Makes the moves and has the options for the Gui
 */
public class ActiveGameHelper {
    ActiveGameController activeGameController;
    SpecialManuals spManuals = new SpecialManuals();
    protected String firstMinionClickedWhite = "";
    protected String firstMinionClickedBlack = "";

    /**
     * Creates a activeGameHelper for the ActiveGameController.
     *
     * @param activeGameController the activeGameController of the current game
     */
    public ActiveGameHelper(ActiveGameController activeGameController) {
        this.activeGameController = activeGameController;
    }

    /**
     * This method changes the Row/Column of the field clicked for the Board rotation and
     * saves this field for the double click funktion
     *
     * @param colIndex column of the Field clicked
     * @param rowIndex row of the Field clicked
     * @param columns  the indices for the columns from the letters to numbers
     * @return the input of the field clicked changed for the options rotation and double click
     */
    String guiOptions(int colIndex, int rowIndex, List<String> columns) {
        String input;
        if (activeGameController.board.isBlackIsTurn() && activeGameController.getGui().getSettings().isRotateBoard()) {
            input = columns.get(7 - colIndex) + (rowIndex + 1);
        } else {
            input = columns.get(colIndex) + (8 - rowIndex);
        }
        // sets first Minion Clicked for Black
        if (activeGameController.getGui().getSettings().isDoubleClick() && activeGameController.board.isBlackIsTurn() && firstMinionClickedBlack.isEmpty()) {
            firstMinionClickedBlack = input;
        }
        // sets first Minion clicked for White
        if (activeGameController.getGui().getSettings().isDoubleClick() && !activeGameController.board.isBlackIsTurn() && firstMinionClickedWhite.isEmpty()) {
            firstMinionClickedWhite = input;
        }
        return input;
    }


    /**
     * Checks if the move is allowed and checks the first field clicked when double move is activated
     * else pops up the popups for move not allowed
     *
     * @param fistField  the first field clicked
     * @param endIndex   the endIndex of the move (second Field)
     * @param startIndex the startIndex of the move (first Field)
     * @param moveNew    the move (startIndex + endIndex + promoteTo)
     */
    void checkAndDoMove(String fistField, CellIndex endIndex, CellIndex startIndex, Move moveNew) {
        if (activeGameController.board.manuals.moveOfRightColour(moveNew, activeGameController.board) && activeGameController.board.manuals.checkIfValidMove(startIndex, endIndex, activeGameController.board.getCheckerBoard())){

                if (!activeGameController.getGui().getSettings().isDoubleClick() || (firstMinionClickedWhite.equals(fistField)) || firstMinionClickedBlack.equals(fistField)) {
                    applyCurrentMove(moveNew);
                } else {
                    activeGameController.popups.popupDoubleClick(firstMinionClickedBlack, firstMinionClickedWhite, activeGameController.getGui());
                }
        }
        else if (spManuals.isValidEnPassant(startIndex, endIndex, activeGameController.board.getCheckerBoard(), activeGameController.board.getMoveList())
                || spManuals.figureRochadeHasMoved(activeGameController.board.getMoveList(), startIndex, endIndex, activeGameController.board.getCheckerBoard())) {
            applyCurrentMove(moveNew);
        }

        else{
        activeGameController.popups.popupMoveNotAllowed(activeGameController.getGui());
        activeGameController.board.setAllowedMove(false);
        }
    }

    /**
     * Applies the move on the Board from the Player and the AI if active. And checks for check
     *
     * @param moveNew the move (startIndex + endIndex + promoteTo)
     */
    private void applyCurrentMove(Move moveNew) {
        firstMinionClickedBlack = "";
        firstMinionClickedWhite = "";
        activeGameController.board.applyMove(moveNew);
        activeGameController.history();
        Event end = activeGameController.position.get(1);
        Node sourceEnd = (Node) end.getSource();
        activeGameController.beatenMinionOutput();
        activeGameController.updateBoard();
        if (activeGameController.getGui().getSettings().isAi_active()) {
            Move aiMove = activeGameController.getGui().getSettings().getAi().getNextMove(activeGameController.board);
            activeGameController.board.applyMove(aiMove);
            // geschlagene figur vom AI Move
            CellIndex endIndex = Board.cellIndexFor(aiMove.getEnd());
            activeGameController.getGui().getSettings().getAi().increaseTurnNumber();
            //update
            activeGameController.beatenMinionOutput();
            activeGameController.updateBoard();

        }
        if (activeGameController.getGui().getSettings().getIsInCheck() && activeGameController.getGui().getSettings().isCheckVisible()) {
            activeGameController.popups.popupCheck(activeGameController.getGui());
            activeGameController.getGui().getSettings().setInCheck(false);
        }

    }

    /**
     * Applies the move for the White AI because the white AI must move in the beginning first
     */
    public void whiteAIMove() {
        if (activeGameController.getGui().getSettings().isAi_active() && !activeGameController.getGui().getSettings().isAi_colour()) {
            activeGameController.board.applyMove(activeGameController.getGui().getSettings().getAi().getNextMove(activeGameController.board));
            activeGameController.getGui().getSettings().getAi().increaseTurnNumber();
            //update
            activeGameController.updateBoard();
        }
    }

    /**
     * Is an Option and shows the Possible moves a clicked Figure can make
     *
     * @param startCol the column of the Cell/Grid where the Figure is standing
     * @param startRow the row of the Cell/Grid where the Figure is standing
     */
    public void showPossibleMoves(int startCol, int startRow) { //TODO enpassante rochade
        CellIndex startIndex = new CellIndex(startRow, startCol);
        activeGameController.getChessBoard().getChildren().removeIf(node -> node instanceof Rectangle && ((Rectangle) node).getFill().equals(Paint.valueOf("#ff6347")));
        if (activeGameController.getNodeByCoordinate(startRow, startCol) instanceof ImageView && !activeGameController.board.getCheckerBoard()[startRow][startCol].isEmpty()) {
            List<Move> possibleMoves = (activeGameController.board.staleMate.possibleMovesForOneFigureMoveList(startIndex, activeGameController.board.getCheckerBoard(), activeGameController.board.getMoveList()));

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
                        activeGameController.mouseClicked(mouseEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                if(activeGameController.counter == 0){
                    activeGameController.getChessBoard().add(possMove, s.indexOf(move.getEnd().substring(0, 1)), 8 - Integer.parseInt(move.getEnd().substring(1, 2)));
                    activeGameController.getChessBoard().setAlignment(Pos.CENTER);
                    possMove.toFront();
                }

            }
        }
    }


}
