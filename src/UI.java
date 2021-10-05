import javax.swing.*;
import java.io.IOException;

/**
 * Esta clase define una basica ventana de Swing.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class UI {
    public JFrame window;

    /**
     * Metodo constructor para la ventana
     *
     * @param title
     * @param width
     * @param height
     * @throws IOException
     */
    public UI(String title, int width, int height) throws IOException {
        window = new JFrame(title);
        window.setResizable(false);
        window.setLayout(null);
        window.setSize(width, height);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}