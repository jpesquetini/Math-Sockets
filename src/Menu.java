import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Esta clase define una ventana de interfaz de menu, que permite la entrada de un nombre para conectar al cliente y al
 * servidor
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Menu extends Thread {
    private final JTextField name_field;
    private final UI menuInterface;
    private final String type;
    private Server jugador1;

    /**
     * @param type
     * @param temp
     * @param gameList
     * @param current_game
     * @throws IOException
     */
    public Menu(String type, Node temp, GameList gameList, Game_logic current_game) throws
            IOException {

        this.type = type;

        int width = 800;
        int height = 500;

        menuInterface = new UI(type + " - menu", width, height);

        JPanel panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);

        JLabel name_label = new JLabel("Name:");
        name_label.setSize(50, 25);
        name_label.setLocation(200, 400);

        name_field = new JTextField("Enter your name");
        name_field.setSize(300, 20);
        name_field.setLocation(250, 400);

        JButton enter_button = new JButton("Enter");
        enter_button.setSize(70, 25);
        enter_button.setLocation(560, 400);


        Thread tServer = new Thread(() -> {
            try {
                jugador1 = new Server(5000);
                jugador1.wait_in();
                current_game.registerServer(jugador1);
                current_game.registerGamedata(temp, gameList);
                String player_name = name_field.getText();
                menuInterface.window.dispose();
                current_game.create_game(type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread tClient = new Thread(() -> {
            Client jugador2;
            try {
                jugador2 = new Client(5000);
                current_game.registerClient(jugador2);
                current_game.registerGamedata(temp, gameList);
                jugador2.confirm_out();
                String player_name = name_field.getText();
                menuInterface.window.dispose();
                current_game.create_game(type);
            } catch (IOException e) {

                e.printStackTrace();
            }
        });

        enter_button.addActionListener(e -> {
            if (this.type.equals("Server")) {
                tServer.start();
            } else {
                tClient.start();
            }
        });

        BufferedImage bg;
        if (this.type.equals("Server")) {
            bg = ImageIO.read(new File("images/bg_server.jpg"));   // Path del fondo
        } else {
            bg = ImageIO.read(new File("images/bg_client1.jpg"));   // Path del fondo
        }

        JLabel bgLabel = new JLabel(new ImageIcon(bg));    // Label para colocar el fondo
        bgLabel.setSize(width, height);

        panel.add(name_label);
        panel.add(name_field);
        panel.add(enter_button);
        panel.add(bgLabel);
        menuInterface.window.add(panel);
        menuInterface.window.setVisible(true);
    }

    /**
     * Este metodo se encarga de correr el codigo, creando la instancia de la lista doblementa enlazada y la interfaz.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        GameList gameData = new GameList();
        gameData.gameListAssignment();
        Node temp = gameData.get_head();    // RECORDAR ELIMINAR ESTO EN CASO DE QUE NO SE OCUPE

        Game_logic game1 = new Game_logic();
        Menu menuServer = new Menu("Server", temp, gameData, game1);
        Menu menuClient = new Menu("Client", temp, gameData, game1);
    }
}