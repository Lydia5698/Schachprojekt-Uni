package chess.network;

import chess.Settings;
import chess.model.Move;
import javafx.concurrent.Task;

//todo methdoenaufruf in der GUI scenebuilder bla bla machen
public class Task_MoveRec extends Task<Move> { //movereceived
    private Settings ioController;

    public Task_MoveRec(Settings ioController){
        this.ioController = ioController;
    }

    @Override
    public Move call() throws Exception {
       if(ioController.isMoveReceived()) {
           return ioController.isServer() ? ioController.getClientMove() : ioController.getServerMove();
       }
       return new Move("A0-A0");
    }
}
