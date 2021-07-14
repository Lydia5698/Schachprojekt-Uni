package chess.network;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    private int portNo = 56337;
    private boolean black = false;
    private String input = "";
    private String output = "";
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private final NetwChessProtocol netw_cp;

    public Server(int port) {
        this.portNo = port;
        netw_cp = new NetwChessProtocol();
    }

    public void startSocket() {
        System.out.println("starting as soon as client connects...");
        try {
            this.serverSocket = new ServerSocket(this.portNo);
            this.clientSocket = serverSocket.accept();
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.start();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + this.portNo);
        }
    }

    @Override // auto response
    public void run() {
        //initial conversation
        output = netw_cp.processInput(null, black);
        out.println(output);

        while (true) {
            try {
                if (netw_cp.getStatus() == 3) {
                    output = netw_cp.processInput("", black);
                } else {
                    if ((input = in.readLine()) != null) {
                        String workingInput = input.toLowerCase();
                        output = netw_cp.processInput(workingInput, black);
                    }
                }
                out.println(output);
                if (output.contains("Bye.")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWhite(boolean white) {
        this.black = white;
    }

    public boolean isWhite() {
        return black;
    }

    public NetwChessProtocol getNetw_cp() {
        return netw_cp;
    }
}
