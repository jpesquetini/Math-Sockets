import javax.swing.*;
import java.awt.*;
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

        panel = new JPanel();
        panel.setSize(600, 700);
        panel.setLocation(0, 0);
        panel.setLayout(new GridLayout(4, 4));


        side_panel = new JPanel();
        side_panel.setSize(500, 800);
        side_panel.setLocation(500, 0);
        side_panel.setBackground(new Color(60, 139, 175));

        menuInterface.window.add(panel);

        menuInterface.window.add(side_panel);
        menuInterface.window.setVisible(true);
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
