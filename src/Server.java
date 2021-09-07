import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private DataInputStream input;
    private DataOutputStream output;
    private ServerSocket ss;
    private Socket cs;

    public Server() throws IOException {
        port = 5000;

        ss = new ServerSocket(port);
        System.out.println("Server online");

        cs = ss.accept();
        System.out.println("Client connected");

        input = new DataInputStream(cs.getInputStream());
        output = new DataOutputStream(cs.getOutputStream());
    }
}