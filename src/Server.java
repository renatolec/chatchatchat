import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;

    public void open(int port){
        try {
            server = new ServerSocket(port);
            while(!server.isClosed()){
                System.out.println("Waiting for connection...");
                Socket client = server.accept();
                System.out.println("User connected");
                Thread connection = new Thread(new Connection(client));
                connection.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.open(5555);
        server.close();
    }

}
