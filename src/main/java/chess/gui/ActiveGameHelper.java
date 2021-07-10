package chess.gui;

import chess.model.Board;
import chess.model.CellIndex;
import chess.model.Move;
import javafx.event.Event;
import javafx.scene.Node;

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
        if (activeGameController.board.isCheck() & activeGameController.getGui().getSettings().isCheckVisible()) {
            activeGameController.popupCheck();
            activeGameController.board.setCheck(false);
        }
        if (activeGameController.board.isGameEnd()) {
            activeGameController.popupCheckMate();
        }
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
    }


}
