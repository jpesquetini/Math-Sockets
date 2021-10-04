import java.io.*;
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
    private final DataInputStream in;
    private final DataOutputStream out;

    /**
     * @param port
     * @throws IOException
     */
    public Server(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started");
        Socket sc = server.accept();
        System.out.println("Client connected");
        in = new DataInputStream(sc.getInputStream());
        out = new DataOutputStream(sc.getOutputStream());
    }

    /**
     * @throws IOException
     */
    public void wait_in() throws IOException {
        in.readUTF();
    }

    /**
     * @throws IOException
     */
    public void confirm_out() throws IOException {
        out.writeUTF("ready");
    }

    /**
     * @param position
     * @throws IOException
     */
    public void my_turn(int position) throws IOException {
        out.writeInt(position);
    }

    /**
     * @return
     * @throws IOException
     */
    public int your_turn() throws IOException {
        return in.readInt();
    }
}