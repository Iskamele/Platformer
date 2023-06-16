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
import static platformer.utilz.Constants.Directions.DOWN;
import static platformer.utilz.Constants.Directions.LEFT;
import static platformer.utilz.Constants.Directions.RIGHT;
import static platformer.utilz.Constants.Directions.UP;
import static platformer.utilz.Constants.PlayerConstants.GetSpriteAmount;
import static platformer.utilz.Constants.PlayerConstants.IDLE;
import static platformer.utilz.Constants.PlayerConstants.RUNNING;

public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs;
    private float xDelta, yDelta = 100;
    private BufferedImage image;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        importImage();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    private void importImage() {
        String imagePathName = "/player_sprites.png";
        InputStream is = getClass().getResourceAsStream(imagePathName);
        try {
            if (is != null) {
                image = ImageIO.read(is);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't import image: " + imagePathName, e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't close InputStream", e);
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePosition();
        g.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 128, 80, null);
    }

    private void updatePosition() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }
}
