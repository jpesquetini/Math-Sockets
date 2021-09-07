import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Esta clase crea un servidor que se acepta la conexion de la clase cliente para iniciar el juego.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Server {
    private int port;
    private DataInputStream input;
    private DataOutputStream output;
    private ServerSocket ss;
    private Socket cs;

    /**
     * @throws IOException
     */
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