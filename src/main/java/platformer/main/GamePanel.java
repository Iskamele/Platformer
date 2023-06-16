package platformer.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import platformer.inputs.KeyBoardInputs;
import platformer.inputs.MouseInputs;

public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage image;
    private BufferedImage subImage;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        importImage();
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImage() {
        String imagePathName = "/player_sprites.png";
        InputStream is = getClass().getResourceAsStream(imagePathName);
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("Can't import image: " + imagePathName, e);
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImage = image.getSubimage(1 * 64, 8 * 40, 64, 40);
        g.drawImage(subImage, (int) xDelta, (int) yDelta, 128, 80, null);
    }
}
