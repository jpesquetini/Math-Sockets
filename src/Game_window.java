import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Esta clase define una ventana de interfaz de juego, donde se puede llevar a cabo el juego.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Game_window {
    private final JPanel panel;
    public JPanel side_panel;
    private final JLabel dado0;
    private final JLabel dado1;
    private final JLabel dado2;
    private final JLabel dado3;
    private final JLabel dado4;
    public JLabel jugador1;
    private final JLabel jugador2;
    public JLabel reto;
    public JLabel answer;
    public JTextField respuesta;
    public JButton enviar_respuesta;
    public String resp_jugador;
    private final GameList gameList;
    private final Server gameServer;
    private final Client gameClient;
    private final String type;
    private String currentPlayer;
    private boolean wait_reto = false;
    private UI menuInterface;

    /**
     * Este metodo se encarga de crar la ventana de juego.
     *
     * @param temp
     * @param type
     * @param gameList
     * @param currentPlayer
     * @param gameServer
     * @param gameClient
     * @throws IOException
     */
    public Game_window(Node temp, String type, GameList gameList, String currentPlayer, Server gameServer, Client
            gameClient) throws IOException {

        this.gameServer = gameServer;
        this.gameClient = gameClient;
        this.type = type;
        this.gameList = gameList;
        this.currentPlayer = currentPlayer;

        gameList.set_server(gameServer);
        gameList.set_client(gameClient);

        int width = 900;
        int height = 720;

        menuInterface = new UI(type, width, height);

        dado0 = new JLabel("0");
        dado1 = new JLabel("1");
        dado2 = new JLabel("2");
        dado3 = new JLabel("3");
        dado4 = new JLabel("4");

        dado0.setFont(new Font("serif", Font.BOLD, 100));
        dado1.setFont(new Font("serif", Font.BOLD, 100));
        dado2.setFont(new Font("serif", Font.BOLD, 100));
        dado3.setFont(new Font("serif", Font.BOLD, 100));
        dado4.setFont(new Font("serif", Font.BOLD, 100));

        dado0.setBounds(130, 50, 50, 150);
        dado1.setBounds(130, 50, 50, 150);
        dado2.setBounds(130, 50, 50, 150);
        dado3.setBounds(130, 50, 50, 150);
        dado4.setBounds(130, 50, 50, 150);

        jugador1 = new JLabel(new ImageIcon("images/player1.1.png"));
        jugador1.setSize(300, 300);
        jugador1.setLocation(-115, -105);
        jugador1.createImage(100, 100);

        jugador2 = new JLabel(new ImageIcon("images/player2.1.png"));
        jugador2.setSize(300, 300);
        jugador2.setLocation(-115, -20);
        jugador2.createImage(100, 100);

        panel = new JPanel();
        panel.setSize(600, 700);
        panel.setLocation(0, 0);
        panel.setLayout(new GridLayout(4, 4));

        side_panel = new JPanel();
        side_panel.setSize(300, 700);
        side_panel.setLocation(600, 0);
        side_panel.setLayout(null);
        side_panel.setBackground(new Color(60, 139, 175));

        JPanel jugadores = new JPanel();
        jugadores.setSize(600, 700);
        jugadores.setLocation(0, 0);
        jugadores.setBackground(Color.orange);
        jugadores.setLayout(null);
        jugadores.setOpaque(false);

        reto = new JLabel("Su reto es: ");
        reto.setFont(new Font("serif", Font.BOLD, 30));
        reto.setBounds(30, 500, 300, 100);

        answer = new JLabel("Respuesta:");
        answer.setFont(new Font("serif", Font.BOLD, 12));
        answer.setBounds(3, 558, 100, 100);

        respuesta = new JTextField();
        respuesta.setSize(200, 20);
        respuesta.setLocation(70, 600);

        enviar_respuesta = new JButton("Revisar");
        enviar_respuesta.setBounds(90, 630, 100, 30);

        JButton dado = new JButton("Dado");
        dado.setBounds(90, 50, 120, 30);

        jugadores.add(jugador1);
        jugadores.add(jugador2);

        menuInterface.window.add(jugadores);
        menuInterface.window.add(panel);
        menuInterface.window.add(side_panel);
        menuInterface.window.setVisible(true);

        side_panel.add(dado);
        side_panel.setVisible(true);

        jugadores.add(jugador1);
        jugadores.setVisible(true);

        enviar_respuesta.addActionListener(e -> resp_jugador = respuesta.getText());

        dado.addActionListener(e -> {
            try {
                dice_logic();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        if (!type.equals(currentPlayer)) {
            not_my_turn();
        }
    }

    /**
     * @throws IOException
     */
    public void dice_logic() throws IOException {
        if (type.equals(currentPlayer)) {
            side_panel.remove(dado0);
            side_panel.remove(dado1);
            side_panel.remove(dado2);
            side_panel.remove(dado3);
            side_panel.remove(dado4);
            side_panel.revalidate();
            side_panel.repaint();
            int aleatorio = (int) (Math.random() * 5);

            if (aleatorio == 0) {
                side_panel.add(dado0);
                side_panel.setVisible(true);
            }
            if (aleatorio == 1) {
                side_panel.add(dado1);
                side_panel.setVisible(true);
            }
            if (aleatorio == 2) {
                side_panel.add(dado2);
                side_panel.setVisible(true);
            }
            if (aleatorio == 3) {
                side_panel.add(dado3);
                side_panel.setVisible(true);
            }
            if (aleatorio == 4) {
                side_panel.add(dado4);
                side_panel.setVisible(true);
            }
            if (type.equals("player1")) {
                if (aleatorio == 0) {
                    gameServer.my_turn(0);
                } else {
                    gameList.movePlayer1(aleatorio, true, false, false, Game_window.this);
                }
                currentPlayer = "player2";
            } else {
                if (aleatorio == 0) {
                    gameClient.my_turn(0);
                } else {
                    gameList.movePlayer2(aleatorio, true, false, false, Game_window.this);
                }
                currentPlayer = "player1";
            }
            not_my_turn();
        }
    }

    /**
     * @param coordX
     * @param coordY
     */
    public void move_jugador1(int coordX, int coordY) {
        jugador1.setLocation(coordX, coordY);
        jugador1.setVisible(true);
        //this.sleep();
    }

    /**
     *
     */
    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param coordX
     * @param coordY
     */
    public void move_jugador2(int coordX, int coordY) {
        jugador2.setLocation(coordX, coordY + 85);
        jugador2.setVisible(true);
    }

    /**
     * Esta función recibe una copia del nodo head de la lista doblemente enlazada y
     * rellena el grid para acomodar las casillas
     *
     * @param temp
     */
    public void boardlogic(Node temp) {
        while (temp != null) {
            String posicion = temp.getType();
            JLabel Label = new JLabel(posicion);
            if (temp.getType().equals("Reto")) {
                Label.setBackground(Color.green);
            } else {
                if (temp.getType().equals("Trampa")) {
                    Label.setBackground(Color.red);
                } else {
                    if ((temp.getType().equals("Inicio")) || (temp.getType().equals("Fin"))) {
                        Label.setBackground(Color.gray);
                    } else {
                        Label.setBackground(Color.cyan);
                    }
                }
            }
            Label.setOpaque(true);
            Label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            Label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(Label);
            temp = temp.getNext();
        }
    }

    /**
     * @throws IOException
     */
    public void turn_player1() throws IOException {
        gameList.movePlayer1(gameClient.your_turn(), false, true, false, Game_window.this);
        if (gameList.get_player1().getType().equals("Fin")) {
            currentPlayer = null;
        } else {
            if (!wait_reto) {
                currentPlayer = "player2";
            }
        }
    }

    /**
     * @throws IOException
     */
    public void turn_player2() throws IOException {
        gameList.movePlayer2(gameServer.your_turn(), false, true, false, Game_window.this);
        if (gameList.get_player2().getType().equals("Fin")) {
            currentPlayer = null;
        } else {
            if (!wait_reto) {
                currentPlayer = "player1";
            }
        }
    }

    /**
     * @param playerChallenged
     */
    public void reto_logic(String playerChallenged) {
        this.wait_reto = true;

        int arg1 = (int) (Math.random() * 50 + 1);
        int arg2 = (int) (Math.random() * 50 + 1);
        String[] operatorArray = {"+", "-", "*", "/"};
        int i = new Random().nextInt(operatorArray.length);
        String operator = operatorArray[i];
        String operation = arg1 + operator + arg2;

        reto.setText("Su reto es: " + operation);
        side_panel.add(enviar_respuesta);
        side_panel.add(answer);
        side_panel.add(respuesta);
        side_panel.add(reto);

        side_panel.setVisible(true);
        menuInterface.window.setVisible(true);

        enviar_respuesta.addActionListener(e -> {
            int retoAnswer = Integer.parseInt(respuesta.getText());
            int retoResult = 0;
            if (operator.equals("+")) {
                retoResult = arg1 + arg2;
            }
            if (operator.equals("-")) {
                retoResult = arg1 - arg2;
            }
            if (operator.equals("*")) {
                retoResult = arg1 * arg2;
            }
            if (operator.equals("/")) {
                retoResult = arg1 / arg2;
            }

            if (retoAnswer == retoResult) {
                System.out.println("CORRECT");
            } else {
                System.out.println(":(");
                if (playerChallenged.equals("player1")) {
                    try {
                        gameList.movePlayer1(-1, false, false, false, Game_window.this);
                        gameServer.my_turn(-1);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (currentPlayer.equals("player2")) {
                    try {
                        gameList.movePlayer2(-1, false, false, false, Game_window.this);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (playerChallenged.equals("player1")) {
                currentPlayer = "player1";
            } else {
                currentPlayer = "player2";
            }
            this.wait_reto = false;
        });
    }

    /**
     *
     */
    public void not_my_turn() {
        thread_the_creator(type);
    }

    /**
     * @param type
     */
    public void thread_the_creator(String type) {
        if (type.equals("player1")) {
            new Thread(() -> {
                try {
                    turn_player2();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            new Thread(() -> {
                try {
                    turn_player1();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}