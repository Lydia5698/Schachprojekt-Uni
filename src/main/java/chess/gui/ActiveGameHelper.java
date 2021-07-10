package chess.gui;

import chess.model.Board;
import chess.model.CellIndex;
import chess.model.Move;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.List;

public class ActiveGameHelper {
    ActiveGameController activeGameController;

    public ActiveGameHelper(ActiveGameController activeGameController){
        this.activeGameController = activeGameController;
    }

    String guiOptions(int colIndex, int rowIndex, List<String> columns) {
        String input;
        if(activeGameController.board.isBlackIsTurn() && activeGameController.getGui().getSettings().isRotateBoard()){
            input = columns.get(7 - colIndex) + (rowIndex +1);
        }
        else {
            input = columns.get(colIndex) + (8 - rowIndex);
        }
        // sets first Minion Clicked for Black
        if (activeGameController.getGui().getSettings().isDoubleClick() && activeGameController.board.isBlackIsTurn() && activeGameController.firstMinionClickedBlack.isEmpty()){
            activeGameController.firstMinionClickedBlack = input;
        }
        // sets first Minion clicked for White
        if(activeGameController.getGui().getSettings().isDoubleClick() && !activeGameController.board.isBlackIsTurn() && activeGameController.firstMinionClickedWhite.isEmpty()){
            activeGameController.firstMinionClickedWhite = input;
        }
        return input;
    }


    void checkAndDoMove(String fistField, CellIndex endIndex, CellIndex startIndex, Move moveNew) {
        if (activeGameController.board.manuals.moveOfRightColour(moveNew, activeGameController.board) && activeGameController.board.manuals.checkIfValidMove(startIndex, endIndex,activeGameController.board.getCheckerBoard())) {
            if (!activeGameController.getGui().getSettings().isDoubleClick() || (activeGameController.firstMinionClickedWhite.equals(fistField)) || activeGameController.firstMinionClickedBlack.equals(fistField)){
                applyCurrentMove(moveNew);
            }
            else{
                activeGameController.popupDoubleClick();
            }

        }
        else {
            activeGameController.popupMoveNotAllowed();
            activeGameController.board.setAllowedMove(false);
        }
    }

    private void applyCurrentMove(Move moveNew) {
        activeGameController.firstMinionClickedBlack = "";
        activeGameController.firstMinionClickedWhite = "";
        activeGameController.board.applyMove(moveNew);
        activeGameController.history();
        Event end = activeGameController.position.get(1);
        Node sourceEnd = (Node) end.getSource();
        activeGameController.beatenMinionOutput(sourceEnd);
        activeGameController.updateBoard();
        // network move
        if (activeGameController.getGui().getSettings().isAi_active()) {
            Move aiMove = activeGameController.getGui().getSettings().getAi().getNextMove(activeGameController.board);
            activeGameController.board.applyMove(aiMove);
            // geschlagene figur vom AI Move
            CellIndex endIndex = Board.cellIndexFor(aiMove.getEnd());
            sourceEnd = activeGameController.getNodeByCoordinate(endIndex.getRow(), endIndex.getColumn());
            activeGameController.getGui().getSettings().getAi().increaseTurnNumber();
            //update
            activeGameController.beatenMinionOutput(sourceEnd);
            activeGameController.updateBoard();

        }
        if (activeGameController.getGui().getSettings().isCheck() && activeGameController.getGui().getSettings().isCheckVisible()) {
            activeGameController.popupCheck();
            activeGameController.getGui().getSettings().setCheck(false);
        }

    }

    public void whiteAIMove(){
        if(activeGameController.getGui().getSettings().isAi_active() && !activeGameController.getGui().getSettings().isAi_colour()) {
            activeGameController.board.applyMove(activeGameController.getGui().getSettings().getAi().getNextMove(activeGameController.board));
            activeGameController.getGui().getSettings().getAi().increaseTurnNumber();
            System.out.println(activeGameController.board.showBoard());
            //update
            activeGameController.updateBoard();
        }
    }

    public void showPossibleMoves(int startCol, int startRow) {
        CellIndex startIndex = new CellIndex(startRow, startCol);
        activeGameController.getChessBoard().getChildren().removeIf(node -> node instanceof Rectangle && ((Rectangle) node).getFill().equals(Paint.valueOf("#ff6347")));
        if(activeGameController.getNodeByCoordinate(startRow, startCol) instanceof ImageView && !activeGameController.board.getCheckerBoard()[startRow][startCol].isEmpty()) {
            List<Move> possibleMoves = (activeGameController.board.staleMate.possibleMovesForOneFigureMoveList(startIndex, activeGameController.board.getCheckerBoard()));

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
                System.out.println(s.indexOf(move.getEnd().substring(0, 1)));
                System.out.println(8-Integer.parseInt(move.getEnd().substring(1,2)));
                activeGameController.getChessBoard().add(possMove, s.indexOf(move.getEnd().substring(0, 1)), 8-Integer.parseInt(move.getEnd().substring(1,2)));
                activeGameController.getChessBoard().setAlignment(Pos.CENTER);
                possMove.toFront();
            }
        }
    }




}
