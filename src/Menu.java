import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {
    JLabel test;
    JPanel panel;
    JLabel bgLabel;
    private BufferedImage bg;

    public Menu() throws IOException {
        UI menuInterface = new UI("Menu", 480, 360);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(480, 360);


        test = new JLabel("test");
        test.setSize(100, 100);
        test.setLocation(50, 50);


        panel.add(test);
        menuInterface.window.add(panel);

    }

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
    }
}