package platformer.ui;

import static platformer.utilz.Constants.UI.Buttons.B_HEIGHT;
import static platformer.utilz.Constants.UI.Buttons.B_HEIGHT_DEFAULT;
import static platformer.utilz.Constants.UI.Buttons.B_WIDTH;
import static platformer.utilz.Constants.UI.Buttons.B_WIDTH_DEFAULT;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import platformer.gamestats.GameState;
import platformer.utilz.LoadSave;

public class MenuButton implements DrawAndUpdate {
    private int xPosition;
    private int yPosition;
    private int rowIndex;
    private int index;
    private int xOffsetCenter = B_WIDTH / 2;
    private GameState state;
    private BufferedImage[] images;
    private boolean mouseOver;
    private boolean mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPosition, int yPosition, int rowIndex, GameState state) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImages();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPosition - xOffsetCenter, yPosition, B_WIDTH, B_HEIGHT);

    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT,
                    rowIndex * B_HEIGHT_DEFAULT,
                    B_WIDTH_DEFAULT,
                    B_HEIGHT_DEFAULT);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images[index],
                xPosition - xOffsetCenter,
                yPosition,
                B_WIDTH,
                B_HEIGHT,
                null);
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGameState() {
        GameState.state = state;
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

}
