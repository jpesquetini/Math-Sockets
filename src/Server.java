import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

/**
 * Esta clase crea un servidor que se acepta la conexion de la clase cliente para iniciar el juego.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Server {
    private DataInputStream in;
    private DataOutputStream out;
    private ServerSocket server;
    private Socket sc;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
        System.out.println("Server started");
        sc = server.accept();
        System.out.println("Client connected");
        in = new DataInputStream(sc.getInputStream());
        out = new DataOutputStream(sc.getOutputStream());
    }

    public void my_turn() throws IOException {
        out.writeUTF("Hello from the server");
    }

    public void your_turn() {
    }
}