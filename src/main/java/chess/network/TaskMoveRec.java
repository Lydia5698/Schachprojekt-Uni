package chess.network;

import chess.Settings;
import chess.model.Move;
import javafx.concurrent.Task;

//todo methdoenaufruf in der GUI scenebuilder bla bla machen
public class TaskMoveRec extends Task<Move> { //movereceived
    private final Settings ioController;

    public TaskMoveRec(Settings ioController) {
        this.ioController = ioController;
    }

    @Override
    public Move call(){
        if (ioController.getSettingsNetwork().isMoveReceived()) {
            return ioController.getSettingsNetwork().isServer() ? ioController.getSettingsNetwork().getClientMove() : ioController.getSettingsNetwork().getServerMove();
        }
        return new Move("A0-A0");
    }
}
