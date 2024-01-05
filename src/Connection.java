import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection implements Runnable{

    private static final List<BufferedWriter> clients = new ArrayList<>();

    private final Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clients.add(out);

            while(!socket.isClosed()){
                String msg = in.readLine();
                for(BufferedWriter client : clients){
                    client.write(msg + "\r");
                    client.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
