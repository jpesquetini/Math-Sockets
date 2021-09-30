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
    DataInputStream in;
    DataOutputStream out;

    public Client(int port, GameList gamedata) {
        try {
            Socket sc = new Socket("localhost", port);
            System.out.println("Connected");
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void my_turn(int number) throws IOException {
        out.writeInt(number);
    }

    public void your_turn() throws IOException {
       System.out.println(in.readInt());
    }
}