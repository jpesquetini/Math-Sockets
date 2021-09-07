import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    final int port;
    DataInputStream input;
    DataOutputStream output;
    String ipAddress = "127.0.0.1";
    Socket cs;

    public Client() throws IOException {
        port = 5000;

        cs = new Socket(ipAddress, port);
        System.out.println("Connected to server");

        input = new DataInputStream(cs.getInputStream());
        output = new DataOutputStream(cs.getOutputStream());
    }
}