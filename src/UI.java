import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class UI {
    public JFrame window;
    private BufferedImage bg;
    JLabel bgLabel;


    public UI(String title, int width, int height) throws IOException {
        window = new JFrame(title);
       //bg = ImageIO.read(new File("imagepath"));
       // bgLabel = new JLabel(new ImageIcon(bg));

        window.setResizable(false);
        window.setSize(width, height);
        //window.add(bgLabel);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

    }
}