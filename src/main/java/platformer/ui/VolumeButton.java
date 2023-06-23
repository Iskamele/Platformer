package platformer.ui;

import static platformer.utilz.Constants.UI.VolumeButtons.SLIDER_DEFAULT_WIDTH;
import static platformer.utilz.Constants.UI.VolumeButtons.VOLUME_DEFAULT_HEIGHT;
import static platformer.utilz.Constants.UI.VolumeButtons.VOLUME_DEFAULT_WIDTH;
import static platformer.utilz.Constants.UI.VolumeButtons.VOLUME_WIDTH;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import platformer.utilz.LoadSave;

public class VolumeButton extends PauseButton implements DrawAndUpdate {
    private BufferedImage[] images;
    private BufferedImage slider;
    private int index = 0;
    private boolean mouseOver;
    private boolean mousePressed;
    private int buttonX;
    private int minX;
    private int maxX;

    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_WIDTH, height);
        bounds.x -= VOLUME_WIDTH / 2;
        buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        minX = x + VOLUME_WIDTH / 2;
        maxX = x + width - VOLUME_WIDTH / 2;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.VOLUME_BUTTONS);
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(
                    i * VOLUME_DEFAULT_WIDTH,
                    0,
                    VOLUME_DEFAULT_WIDTH,
                    VOLUME_DEFAULT_HEIGHT);
        }
        slider = temp.getSubimage(
                3 * VOLUME_DEFAULT_WIDTH,
                0, SLIDER_DEFAULT_WIDTH,
                VOLUME_DEFAULT_HEIGHT);
    }

    @Override
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(images[index], buttonX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null);
    }

    public void changeX(int x) {
        if (x < minX) {
            buttonX = minX;
        } else if (x > maxX) {
            buttonX = maxX;
        } else {
            buttonX = x;
        }
        bounds.x = buttonX - VOLUME_WIDTH / 2;
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
