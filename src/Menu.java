import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Esta clase define una ventana de interfaz de menu, que permite la entrada de un nombre para conectar al cliente y al
 * servidor
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Menu {
    private JLabel name_label;
    private JTextField name_field;
    private JButton enter_button;
    private JPanel panel;
    private int width;
    private int height;
    // private JLabel bgLabel;
    // private BufferedImage bg;

    /**
     * @param type
     * @throws IOException
     */
    public Menu(String type) throws IOException {
        width = 800;
        height = 500;

        UI menuInterface = new UI(type + " - menu", width, height);

        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);

        name_label = new JLabel("Name:");
        name_label.setSize(100, 100);
        name_label.setLocation(200, 360);

        name_field = new JTextField("Enter your name");
        name_field.setSize(300, 20);
        name_field.setLocation(250, 400);

        enter_button = new JButton("Enter");
        enter_button.setSize(70, 25);
        enter_button.setLocation(560, 400);

        enter_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Game_window game = new Game_window("game");
                    game.board_logic();
                    menuInterface.window.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        /*
        bg = ImageIO.read(new File("imagepath"));   // Path del fondo
        bgLabel = new JLabel(new ImageIcon(bg));    // Label para colocar el fondo
        bgLabel.setSize(width, height);
        panel.add(bgLabel);
         */
        panel.add(name_label);
        panel.add(name_field);
        panel.add(enter_button);
        menuInterface.window.add(panel);
        menuInterface.window.setVisible(true);
    }


    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Menu menuClient = new Menu("Client");
        Menu menuServer = new Menu("Server");
    }
}