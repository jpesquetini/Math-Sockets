import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Esta clase crea un cliente que se conecta a un servidor para iniciar el juego.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Client {
    final int port;
    DataInputStream input;
    DataOutputStream output;
    String ipAddress = "127.0.0.1";
    Socket cs;

    /**
     * @throws IOException
     */
    public Client() throws IOException {
        port = 5000;

        cs = new Socket(ipAddress, port);
        System.out.println("Connected to server");

        input = new DataInputStream(cs.getInputStream());
        output = new DataOutputStream(cs.getOutputStream());
    }
}