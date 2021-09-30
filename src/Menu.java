import javax.swing.*;
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
    private JLabel name_label;
    private JTextField name_field;
    private JButton enter_button;
    private JPanel panel;
    private int width;
    private int height;
    private Server player1;
    private Client player2;
    private boolean connected = false;
    private boolean ready = false;
    private GameList gameList;
    private String currentPlayer;
    private Node temp;
    private UI menuInterface;
    private String type;
    private Server jugador1;
    private Client jugador2;
    private Game_window game;
    private Game_logic current_game;
    // private JLabel bgLabel;
    // private BufferedImage bg;

    /**
     * @param type
     * @throws IOException
     */

    public Menu(String type, Node temp, GameList gameList, String currentPlayer, Game_logic current_game) throws
            IOException {
        width = 800;
        height = 500;

        menuInterface = new UI(type + " - menu", width, height);

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

        this.gameList = gameList;
        this.currentPlayer = currentPlayer;
        this.temp = temp;
        this.type = type;
        this.current_game = current_game;

        enter_button.addActionListener(e -> {
            if (type.equals("Server")) {
                start();
            } else {
                try {
                    Client jugador2 = new Client(5000, gameList);
                    current_game.registerClient(jugador2);
                    current_game.create_game("Client");
                    String player_name = new String(name_field.getText());
                    menuInterface.window.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        panel.add(name_label);
        panel.add(name_field);
        panel.add(enter_button);
        menuInterface.window.add(panel);
        menuInterface.window.setVisible(true);
    }

    public void run() {
        try {
            Server jugador1 = new Server(5000, gameList);
            current_game.registerServer(jugador1);
            current_game.registerGamedata(temp, gameList);
            current_game.create_game("Server");
            String player_name = new String(name_field.getText());
            menuInterface.window.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
        bg = ImageIO.read(new File("imagepath"));   // Path del fondo
        bgLabel = new JLabel(new ImageIcon(bg));    // Label para colocar el fondo
        bgLabel.setSize(width, height);
        panel.add(bgLabel);
        */

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
        Menu menuServer = new Menu("Server", temp, gameData, "player1", game1);
        Menu menuClient = new Menu("Client", temp, gameData, "player2", game1);
    }
}