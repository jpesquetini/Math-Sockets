import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Esta clase define una ventana de interfaz de juego, donde se puede llevar a cabo el juego.
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */

public class Game_window {
    private JPanel panel;
    public JPanel side_panel;
    private JPanel jugadores;
    private int width;
    private int height;
    private JLabel dado0;
    private JLabel dado1;
    private JLabel dado2;
    private JLabel dado3;
    private JLabel dado4;
    public JLabel jugador1;
    private JLabel jugador2;
    public JLabel reto;
    public JLabel answer;
    public JTextField respuesta;
    public JButton enviar_respuesta;
    public String resp_jugador;
    private int aleatorio;
    private JButton Dado;

    // private JLabel bgLabel;
    // private BufferedImage bg;

    /**
     * Est metodo se encarga de crar la ventana de juego.
     * 
     * @param type
     * @throws IOException
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public Game_window(String type, GameList gameList, String currentPlayer, Server gameServer, Client gameClient) throws IOException {
        width = 900;
        height = 720;

        UI menuInterface = new UI(type, width, height);

        dado0 = new JLabel("0");
        dado1 = new JLabel("1");
        dado2 = new JLabel("2");
        dado3 = new JLabel("3");
        dado4 = new JLabel("4");

        dado0.setFont(new Font("serif", Font.BOLD,100));
        dado1.setFont(new Font("serif", Font.BOLD,100));
        dado2.setFont(new Font("serif", Font.BOLD,100));
        dado3.setFont(new Font("serif", Font.BOLD,100));
        dado4.setFont(new Font("serif", Font.BOLD,100));

        dado0.setBounds(130,50,50,150);
        dado1.setBounds(130,50,50,150);
        dado2.setBounds(130,50,50,150);
        dado3.setBounds(130,50,50,150);
        dado4.setBounds(130,50,50,150);

        jugador1 = new JLabel(new ImageIcon("images/player1.1.png"));
        jugador1.setSize(300, 300);
        jugador1.setLocation(-115, -105);
        jugador1.createImage(100,100);

        jugador2 = new JLabel(new ImageIcon("images/player2.1.png"));
        jugador2.setSize(300, 300);
        jugador2.setLocation(-115, -20);
        jugador2.createImage(100,100);

        panel = new JPanel();
        panel.setSize(600, 700);
        panel.setLocation(0, 0);
        panel.setLayout(new GridLayout(4, 4));

        side_panel = new JPanel();
        side_panel.setSize(300, 700);
        side_panel.setLocation(600, 0);
        side_panel.setLayout(null);
        side_panel.setBackground(new Color(60, 139, 175));

        jugadores = new JPanel();
        jugadores.setSize(600, 700);
        jugadores.setLocation(0,0);
        jugadores.setBackground(Color.orange);
        jugadores.setLayout(null);
        jugadores.setOpaque(false);

        reto = new JLabel("Su reto es: ");
        reto.setFont(new Font("serif", Font.BOLD,30));
        reto.setBounds(30,500,300,100);

        answer = new JLabel("Respuesta:");
        answer.setFont(new Font("serif", Font.BOLD,12));
        answer.setBounds(3,558,100,100);

        respuesta = new JTextField();
        respuesta.setSize(200, 20);
        respuesta.setLocation(70, 600);

        enviar_respuesta = new JButton("Revisar");
        enviar_respuesta.setBounds(90,630,100,30);

        Dado = new JButton("Dado");
        Dado.setBounds(90,50,120,30);

        jugadores.add(jugador1);
        jugadores.add(jugador2);

        menuInterface.window.add(jugadores);
        menuInterface.window.add(panel);
        menuInterface.window.add(side_panel);
        menuInterface.window.setVisible(true);

        side_panel.add(Dado);
        side_panel.setVisible(true);

        jugadores.add(jugador1);
        jugadores.setVisible(true);

        enviar_respuesta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resp_jugador = respuesta.getText();
            }
        });

        Dado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                side_panel.remove(dado0);
                side_panel.remove(dado1);
                side_panel.remove(dado2);
                side_panel.remove(dado3);
                side_panel.remove(dado4);
                side_panel.revalidate();
                side_panel.repaint();
                aleatorio = (int)(Math.random()*5);

                //System.out.println(aleatorio);

                if (aleatorio == 0){
                    side_panel.add(dado0);
                    side_panel.setVisible(true);
                }
                if (aleatorio == 1) {
                    side_panel.add(dado1);
                    side_panel.setVisible(true);
                }
                if(aleatorio == 2) {
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

                if (currentPlayer == "player2") {
                    gameList.movePlayer2(aleatorio, true, Game_window.this);
                }
                if (currentPlayer == "player1") {
                    gameList.movePlayer1(aleatorio, true, Game_window.this);
                }

            }
        });
    }


    public void move_jugador1(int coordX, int coordY){
        jugador1.setLocation(coordX, coordY);
        jugador1.setVisible(true);
        //this.sleep();
    }

    public void sleep(){ //HACER QUE SE VEA EL AVANCE POR CADA CASILLA
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void move_jugador2(int coordX, int coordY){
        jugador2.setLocation(coordX, coordY+85);
        jugador2.setVisible(true);
    }

    /**
     * Esta función recibe una copia del nodo head de la lista doblemente enlazada y
     * rellena el grid para acomodar las casillas
     * 
     * @param temp
     * 
     * @author Andres Uriza
     * @author Daniel Castro
     * @author Jose Pablo Esquetini
     */

    public void boardlogic(Node temp){
        while( temp != null){
            String posicion = temp.getType();
            JLabel Label = new JLabel(posicion);
            if (temp.getType()=="Reto"){
                Label.setBackground(Color.green); //ELEGIR COLOR PARA LA CASILLA RETO
                Label.setOpaque(true);
            }else{
                if (temp.getType()=="Trampa"){
                    Label.setBackground(Color.red); //ELEGIR COLOR PARA LA CASILLA TRAMPA
                    Label.setOpaque(true);
                }else {
                    if ((temp.getType() == "Inicio") || (temp.getType() == "Fin")) {
                        Label.setBackground(Color.gray); //ELEGIR COLOR PARA LA CASILLA inicio y fin
                        Label.setOpaque(true);
                    }else {
                        Label.setBackground(Color.cyan); //ELEGIR COLOR PARA LA CASILLA TUNEL
                        Label.setOpaque(true);
                    }
                }
            }
            Label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            Label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(Label);
            temp = temp.getNext();
        }
    }

}

