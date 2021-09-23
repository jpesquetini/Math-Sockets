import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Esta clase define una ventana de interfaz de menu, que permite la entrada de un nombre para conectar al cliente y al
 * servidor
 *
 * @author Andres Uriza
 * @author Daniel Castro
 * @author Jose Pablo Esquetini
 */
public class Game_window {
    private JPanel panel;
    private JPanel side_panel;
    private int width;
    private int height;
    private JLabel dado1;
    private JLabel dado2;
    private JLabel dado3;
    private JLabel dado4;
    private int aleatorio;
    private JButton Dado;

    // private JLabel bgLabel;
    // private BufferedImage bg;

    /**
     * @param type
     * @throws IOException
     */
    public Game_window(String type) throws IOException {
        width = 900;
        height = 720;

        UI menuInterface = new UI(type, width, height);

        dado1 = new JLabel("1");
        dado2 = new JLabel("2");
        dado3 = new JLabel("3");
        dado4 = new JLabel("4");

        dado1.setFont(new Font("serif", Font.BOLD,100));
        dado2.setFont(new Font("serif", Font.BOLD,100));
        dado3.setFont(new Font("serif", Font.BOLD,100));
        dado4.setFont(new Font("serif", Font.BOLD,100));

        dado1.setBounds(200,500,50,300);
        dado2.setBounds(200,500,50,300);
        dado3.setBounds(200,500,50,300);
        dado4.setBounds(200,500,50,300);

        panel = new JPanel();
        panel.setSize(600, 700);
        panel.setLocation(0, 0);
        panel.setLayout(new GridLayout(4, 4));


        side_panel = new JPanel();
        side_panel.setSize(300, 700);
        side_panel.setLocation(600, 0);
        side_panel.setBackground(new Color(60, 139, 175));

        Dado = new JButton("Dado");
        Dado.setBounds(200,600,30,30);


        menuInterface.window.add(panel);


        menuInterface.window.add(side_panel);
        menuInterface.window.setVisible(true);

        side_panel.add(Dado);
        side_panel.setVisible(true);

        Dado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                side_panel.remove(dado1);
                side_panel.remove(dado2);
                side_panel.remove(dado3);
                side_panel.remove(dado4);
                side_panel.revalidate();
                side_panel.repaint();
                aleatorio = (int)(Math.random()*4+1);
                System.out.println(aleatorio);
                if (aleatorio == 1){
                    side_panel.add(dado1);
                    side_panel.setVisible(true);
                }else{
                    if(aleatorio == 2){
                        side_panel.add(dado2);
                        side_panel.setVisible(true);
                    }else{
                        if (aleatorio == 3){
                            side_panel.add(dado3);
                            side_panel.setVisible(true);
                        }else{
                            side_panel.add(dado4);
                            side_panel.setVisible(true);
                        }
                    }
                }


            }

        });

    }


    /**
     * Esta función recibe una copia del nodo head de la lista doblemente enlazada y
     * rellena el grid para acomodar las casillas
     * @param temp
     *
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
            panel.add(Label);
            temp = temp.getNext();
        }
    }

}

