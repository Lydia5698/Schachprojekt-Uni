/*
server:
anzeigen [IpAdresse:Port]
[16:17]
client:
eingeben [IpAdresse:Port]
[16:18]
s: running
c: accepted
[16:19]
s: white (seine Farbe)
c: black (gegenFarbe)(edited)
[16:25]
c: [move]
s: ok
c: {gui updaten}
[16:26]
c: [move]
s: invalid
c: {move not allowed anzeigen und nochmal}
[16:27]
s: [move]
c: ok {immer}
[16:28]
c: [move]
(patt detected)
s: patt, tschüslimüsli
-tennen-(edited)
[16:29]
c: [move]
(checkmate detected)
s: checkmate, SanFranTschüsko
-tennen-

*/

package chess.network;

import chess.Settings;

public class NetwChessProtocol {

    private static final int Start = 0;
    private static final int Wait = 1;
    private static final int stillWait = 2;
    private static final int OutputMSG = 3;
    private int status = Start;
    private String svrMove;
    private String cltMove;
    private boolean enteredMove = false;
    private boolean receivedMove = false;
    private Settings settings;

    public NetwChessProtocol(Settings settings){
        this.settings = settings;
    }

    public NetwChessProtocol(){}

    public String processInput(String workingInput, boolean white) {
        String theOutput = "";
        String playingAs = "white";
        if (!white) {
            playingAs = "black";
        }
        if (status == Start) {
            theOutput = "running";
            status = Wait;
        } else if (status == Wait && workingInput.equals("accepted")) {
            theOutput = playingAs;
        } else if (status == Wait) {
            if (white && workingInput.equals("black")) {
                status = OutputMSG;
            } else if (!white && workingInput.equals(("white"))) {
                status = stillWait;
            }
        } else if (status == stillWait) {
            if (workingInput.equals("ok")) {
                status = stillWait;
            } else if (validateInput(workingInput)) {
                cltMove = workingInput;
                receivedMove = true;
                theOutput = "ok";
                status = OutputMSG;
            }
        } else if (status == OutputMSG && enteredMove) {
            theOutput = svrMove;
            enteredMove = false;
            status = stillWait;
        }

        return theOutput;
    }

    public boolean validateInput(String input) {
        return true;
    }//testing with true

    public void setInputMove(String inputMove) {
        this.svrMove = inputMove;
    }

    public String getCltMove() {
        while (!receivedMove) {
        }
        receivedMove = false;
        return cltMove;
    }

    public void setEnteredMove(boolean enteredMove) {
        this.enteredMove = enteredMove;
    }

    public int getStatus() {
        return status;
    }
}
