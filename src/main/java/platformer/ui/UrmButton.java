package platformer.ui;

import static platformer.utilz.Constants.UI.UrmButtons.URM_DEFAULT_SIZE;
import static platformer.utilz.Constants.UI.UrmButtons.URM_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import platformer.utilz.LoadSave;

public class UrmButton extends PauseButton implements DrawAndUpdate {
    private BufferedImage[] images;
    private int rowIndex;
    private int index;
    private boolean mouseOver;
    private boolean mousePressed;

    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.URM_BUTTONS);
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(
                    i * URM_DEFAULT_SIZE,
                    rowIndex * URM_DEFAULT_SIZE,
                    URM_DEFAULT_SIZE,
                    URM_DEFAULT_SIZE);
        }
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
        g.drawImage(images[index], x, y, URM_SIZE, URM_SIZE, null);
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
