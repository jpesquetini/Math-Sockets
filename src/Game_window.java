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
     * Crea botones con numero aleatorio del 1 al 16 (modificar luego para los tipos de casillas)
     */
    public void board_logic3() {
        int[] possible_numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int rnd;
        int i = 0;

        while (i != 16) {
            rnd = new Random().nextInt(possible_numbers.length);
            JButton grid = new JButton(String.valueOf(possible_numbers[rnd]));
            panel.add(grid);
            i++;
        }
    }

    public void board_logic2(ArrayList<String> casillas) {
        int i=0;
        while (i<=15){
            String posicion = casillas.get(i);
            if (posicion == "Reto"){
                JLabel Label = new JLabel(posicion);
                Label.setForeground(Color.green);
                panel.add(Label);
            }else{
                panel.add(new JLabel(posicion));
            }
            i++;
        }
    }

    public void boardlogic(Node temp){
        while( temp != null){
            String posicion = temp.getType();
            JLabel Label = new JLabel(posicion);
            panel.add(Label);
            temp = temp.getNext();
        }


    }


}

//panel.add (new JButton());
