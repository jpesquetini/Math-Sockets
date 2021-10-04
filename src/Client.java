import java.io.*;
import java.net.Socket;

/**
 * Esta clase crea un cliente que se conecta a un servidor para iniciar el juego.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Client {
    private final DataInputStream in;
    private final DataOutputStream out;

    /**
     * @param port
     * @throws IOException
     */
    public Client(int port) throws IOException {
        Socket sc = new Socket("localhost", port);
        System.out.println("Connected");
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