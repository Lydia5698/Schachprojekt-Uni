package chess.network;
import java.io.*;
import java.net.*;
import java.util.function.Consumer;
public abstract class Netw_Con {
    private ConnectionThread thread_con = new ConnectionThread();
    private Consumer<String> callBackWhenReceived;

    public Netw_Con(Consumer<String> callBackWhenReceived){
        this.callBackWhenReceived = callBackWhenReceived; thread_con.setDaemon(true); }
    public void startConnection() throws Exception{ thread_con.start(); }
    public void closeConnection() throws Exception{ thread_con.socket.close(); }
    public void send(String data) throws Exception{ thread_con.out.println(data); }
    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();
    private class ConnectionThread extends Thread{
        private Socket socket;
        private PrintWriter out;

        @Override
        public void run(){
            try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                    Socket socket = isServer() ? server.accept() : new Socket(getIP(),getPort());
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);
                while (true){
                    String data = in.readLine();
                    callBackWhenReceived.accept(data);
                }
            }catch (Exception e){
                callBackWhenReceived.accept("No Connection");
            }
        }
    }
}
